package br.com.noberto.upswing.services.register;

import br.com.noberto.upswing.dtos.academic.CourseSelect;
import br.com.noberto.upswing.dtos.company.RegisterCompany;
import br.com.noberto.upswing.dtos.company.RegisterJobOffer;
import br.com.noberto.upswing.models.*;
import br.com.noberto.upswing.repositories.*;
import br.com.noberto.upswing.util.verifications.AbstractCheckObject;
import br.com.noberto.upswing.util.verifications.CompanyCheck;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyRegisterService {
    private final CompanyRepository repository;
    private final JobOfferRepository jobOfferRepository;
    private final CourseRepository courseRepository;
    private final VacancyAndCourseRepository vacancyAndCourserepository;
    private final AbstractCheckObject companyCheck;

    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    CompanyRegisterService(CompanyRepository repository, ZipCodeRepository zipCodeRepository, BusinessAreaRepository
            businessAreaRepository, JobOfferRepository jobOfferRepository, CourseRepository courseRepository,
                           VacancyAndCourseRepository vacancyAndCourserepository, StudentRepository studentRepository,
                           ClassRepository classRepository, CompanyRepository companyRepository, EntityManager entityManager){
        this.repository = repository;
        this.jobOfferRepository = jobOfferRepository;
        this.courseRepository = courseRepository;
        this.vacancyAndCourserepository = vacancyAndCourserepository;
        this.companyCheck = new CompanyCheck(zipCodeRepository, businessAreaRepository, studentRepository, classRepository, companyRepository, courseRepository, entityManager);
    }

    @Transactional
    public Company registerCompany(RegisterCompany registerCompany){
        if (repository.findCompanyByEmail(registerCompany.account().getEmail()).isEmpty()){
            Address address = companyCheck.checkZipCode(registerCompany.address());
            Company company = new Company(registerCompany);
            company.getAccount().setPassword(new BCryptPasswordEncoder().encode(registerCompany.account().getPassword()));
            company.setBusinessArea(companyCheck.checkBusinessArea(registerCompany.businessArea().id()));
            company.setAddress(address);
            entityManager.flush();
            return repository.save(company);
        }
        throw new IllegalArgumentException("Email informado já exite no banco!");
    }

    @Transactional
    public JobOffer registerJobOffer(RegisterJobOffer registerJobOffer){
        Company company = companyCheck.checkCompany(registerJobOffer.companyId());
        BusinessArea businessArea = companyCheck.checkBusinessArea(registerJobOffer.businessAreaId());
        JobOffer jobOffer = jobOfferRepository.save(new JobOffer(registerJobOffer, company, businessArea));

        for (CourseSelect courseSelect : registerJobOffer.courses()){
            Course course = courseRepository.findById(courseSelect.courseId())
                            .orElseThrow(() -> new EntityExistsException("ID informado para Curso é invalido!"));

            vacancyAndCourserepository.save(new VacancyAndCourse(jobOffer, course));
        }
        entityManager.flush();
        return jobOffer;
    }
}

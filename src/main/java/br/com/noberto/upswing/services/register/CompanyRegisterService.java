package br.com.noberto.upswing.services.register;

import br.com.noberto.upswing.dtos.academic.CourseSelect;
import br.com.noberto.upswing.dtos.company.RegisterCompany;
import br.com.noberto.upswing.dtos.company.RegisterJobOffer;
import br.com.noberto.upswing.models.*;
import br.com.noberto.upswing.repositories.*;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompanyRegisterService {
    private final CompanyRepository repository;
    private final ZipCodeRepository zipCodeRepository;
    private final BusinessAreaRepository businessAreaRepository;
    private final JobOfferRepository jobOfferRepository;
    private final CourseRepository courseRepository;
    private final VacancyAndCourseRepository vacancyAndCourserepository;

    @Autowired
    CompanyRegisterService(CompanyRepository repository, ZipCodeRepository zipCodeRepository, BusinessAreaRepository
            businessAreaRepository, JobOfferRepository jobOfferRepository, CourseRepository courseRepository,
                           VacancyAndCourseRepository vacancyAndCourserepository, SubjectRepository){
        this.repository = repository;
        this.zipCodeRepository = zipCodeRepository;
        this.businessAreaRepository = businessAreaRepository;
        this.jobOfferRepository = jobOfferRepository;
        this.courseRepository = courseRepository;
        this.vacancyAndCourserepository = vacancyAndCourserepository;
    }

    public Company registerCompany(RegisterCompany registerCompany){
        Address address = checkAddress(registerCompany);
        Company company = new Company(registerCompany);
        company.setBusinessArea(checkBusinessArea(registerCompany.businessArea().id()));
        company.setAddress(address);

        return repository.save(company);
    }

    public JobOffer registerJobOffer(RegisterJobOffer registerJobOffer){
        Company company = checkCompany(registerJobOffer.companyId());
        BusinessArea businessArea = checkBusinessArea(registerJobOffer.businessAreaId());
        JobOffer jobOffer = jobOfferRepository.save(new JobOffer(registerJobOffer, company, businessArea));

        for (CourseSelect courseSelect : registerJobOffer.courses()){
            Course course = courseRepository.getReferenceById(courseSelect.courseId());
            vacancyAndCourserepository.save(new VacancyAndCourse(jobOffer, course));
        }

        return jobOffer;
    }



    //METHODS
    private Address checkAddress(RegisterCompany data){
        ZipCode zipCode = null;

        if (zipCodeRepository.existsById(data.address().zipCode().zipCode())){
            zipCode = zipCodeRepository.getReferenceById(data.address().zipCode().zipCode());
        } else {
            zipCode = zipCodeRepository.save(new ZipCode(data));
        }

        return new Address(data, zipCode);
    }

    private BusinessArea checkBusinessArea(UUID id){
        if (businessAreaRepository.existsById(id)){
            return businessAreaRepository.getReferenceById(id);
        }

        throw new ValidationException("ID informado para Área de Atuação é invalido!");
    }

    private Company checkCompany(UUID id){
        if (repository.existsById(id)){
            return repository.getReferenceById(id);
        }

        throw new ValidationException("ID informado para Empresa é invalido!");
    }
}

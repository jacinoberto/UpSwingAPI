package br.com.noberto.upswing.services.register;

import br.com.noberto.upswing.dtos.academic.CourseSelect;
import br.com.noberto.upswing.dtos.company.RegisterCompany;
import br.com.noberto.upswing.dtos.company.RegisterJobOffer;
import br.com.noberto.upswing.models.*;
import br.com.noberto.upswing.repositories.*;
import br.com.noberto.upswing.util.verifications.CompanyCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyRegisterService {
    private final CompanyRepository repository;
    private final ZipCodeRepository zipCodeRepository;
    private final BusinessAreaRepository businessAreaRepository;
    private final JobOfferRepository jobOfferRepository;
    private final CourseRepository courseRepository;
    private final VacancyAndCourseRepository vacancyAndCourserepository;
    private final CompanyCheck check;

    @Autowired
    CompanyRegisterService(CompanyRepository repository, ZipCodeRepository zipCodeRepository, BusinessAreaRepository
            businessAreaRepository, JobOfferRepository jobOfferRepository, CourseRepository courseRepository,
                           VacancyAndCourseRepository vacancyAndCourserepository, CompanyCheck check){
        this.repository = repository;
        this.zipCodeRepository = zipCodeRepository;
        this.businessAreaRepository = businessAreaRepository;
        this.jobOfferRepository = jobOfferRepository;
        this.courseRepository = courseRepository;
        this.vacancyAndCourserepository = vacancyAndCourserepository;
        this.check = check;
    }

    public Company registerCompany(RegisterCompany registerCompany){
        Address address = check.checkZipCode(registerCompany.address());
        Company company = new Company(registerCompany);
        company.setBusinessArea(check.checkBusinessArea(registerCompany.businessArea().id()));
        company.setAddress(address);

        return repository.save(company);
    }

    public JobOffer registerJobOffer(RegisterJobOffer registerJobOffer){
        Company company = check.checkCompany(registerJobOffer.companyId());
        BusinessArea businessArea = check.checkBusinessArea(registerJobOffer.businessAreaId());
        JobOffer jobOffer = jobOfferRepository.save(new JobOffer(registerJobOffer, company, businessArea));

        for (CourseSelect courseSelect : registerJobOffer.courses()){
            Course course = courseRepository.getReferenceById(courseSelect.courseId());
            vacancyAndCourserepository.save(new VacancyAndCourse(jobOffer, course));
        }

        return jobOffer;
    }



    //METHODS
//    private Address checkAddress(RegisterCompany data){
//        ZipCode zipCode = null;
//
//        if (zipCodeRepository.existsById(data.address().zipCode().zipCode())){
//            zipCode = zipCodeRepository.getReferenceById(data.address().zipCode().zipCode());
//        } else {
//            zipCode = zipCodeRepository.save(new ZipCode(data));
//        }
//
//        return new Address(data, zipCode);
//    }
//
//    private BusinessArea checkBusinessArea(UUID id){
//        if (businessAreaRepository.existsById(id)){
//            return businessAreaRepository.getReferenceById(id);
//        }
//
//        throw new ValidationException("ID informado para Área de Atuação é invalido!");
//    }
//
//    private Company checkCompany(UUID id){
//        if (repository.existsById(id)){
//            return repository.getReferenceById(id);
//        }
//
//        throw new ValidationException("ID informado para Empresa é invalido!");
//    }
}

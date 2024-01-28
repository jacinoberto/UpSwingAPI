package br.com.noberto.upswing.services.register;

import br.com.noberto.upswing.dtos.academic.CourseRequest;
import br.com.noberto.upswing.dtos.academic.SubjectRequest;
import br.com.noberto.upswing.dtos.company.RegisterCompany;
import br.com.noberto.upswing.dtos.company.RegisterJobOffer;
import br.com.noberto.upswing.dtos.student.RegisterStudent;
import br.com.noberto.upswing.models.*;
import br.com.noberto.upswing.repositories.*;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyRegisterService {
    private final CompanyRepository repository;
    private final ZipCodeRepository zipCodeRepository;
    private final BusinessAreaRepository businessAreaRepository;
    private final JobOfferRepository jobOfferRepository;
    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    CompanyRegisterService(CompanyRepository repository, ZipCodeRepository zipCodeRepository, BusinessAreaRepository
            businessAreaRepository, JobOfferRepository jobOfferRepository, CourseRepository courseRepository, SubjectRepository
            subjectRepository){
        this.repository = repository;
        this.zipCodeRepository = zipCodeRepository;
        this.businessAreaRepository = businessAreaRepository;
        this.jobOfferRepository = jobOfferRepository;
        this.courseRepository = courseRepository;
        this.subjectRepository = subjectRepository;
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
        JobOffer jobOffer = new JobOffer(registerJobOffer);
        jobOffer.setCompany(company);
        jobOffer.setBusinessArea(businessArea);

        return jobOfferRepository.save(jobOffer);
    }

    public Course registerCourse(CourseRequest courseRequest){
        BusinessArea area = checkBusinessArea(courseRequest.businessAreaId());
        Course course = new Course(courseRequest);
        course.setBusinessArea(area);

        return courseRepository.save(course);
    }

    public Subject registerSubject(SubjectRequest subjectRequest){
        Course course = checkCourse(subjectRequest.courseId());
        return subjectRepository.save(new Subject(subjectRequest, course));
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

    private Course checkCourse(UUID id){
        if (courseRepository.existsById(id)){
            return courseRepository.getReferenceById(id);
        }
        throw new ValidationException("ID informado para Disciplina é invalido");
    }
}

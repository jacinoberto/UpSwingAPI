package br.com.noberto.upswing.util.filters;

import br.com.noberto.upswing.models.*;
import br.com.noberto.upswing.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class FilterStudentByAddressStrategy implements IFilterStudentStrategy {
    private static AutoApplyRepository autoApplyRepository;
    private static CompanyRepository companyRepository;
    private static StudentRepository studentRepository;
    private final FilterStudentsWithCompatibilityStrategy filterStudentsWithCompatibility;

    @Autowired
    public FilterStudentByAddressStrategy(AutoApplyRepository autoApplyRepository, CompanyRepository companyRepository, StudentRepository
            studentRepository, FilterStudentsWithCompatibilityStrategy filterStudentsWithCompatibility) {
        FilterStudentByAddressStrategy.autoApplyRepository = autoApplyRepository;
        FilterStudentByAddressStrategy.companyRepository = companyRepository;
        FilterStudentByAddressStrategy.studentRepository = studentRepository;
        this.filterStudentsWithCompatibility = filterStudentsWithCompatibility;
    }

    public List<Student> filterStudents(JobOffer jobOffer) {
        Company company = companyRepository.getReferenceById(jobOffer.getCompany().getId());

        /*Percorre toda lista 'students' mantendo em 'studentsByAddress' apenas os alunos que retornam verdadeiro para as
        condicionais feitas*/
        List<Student> studentsByAddress = new ArrayList<>();
        for (Student student : filterStudentsWithCompatibility.filterStudents(jobOffer)) {
            AutoApply autoApply = autoApplyRepository.findByStudentPresentAutoApply(student.getId());

            switch (autoApply.getOfferLocation()){
                case CITY -> {
                    if (Objects.equals(student.getAddress().getZipCode().getCity(), company.getAddress().getZipCode()
                            .getCity())) studentsByAddress.add(student);
                }
                case AREA -> {
                    if (Objects.equals(student.getAddress().getZipCode().getArea(), company.getAddress().getZipCode()
                            .getArea())) studentsByAddress.add(student);
                }
                default -> studentsByAddress.add(student);
            }
        }

        return  studentsByAddress;
    }


}

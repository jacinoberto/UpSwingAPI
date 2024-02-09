package br.com.noberto.upswing.util.filters;

import br.com.noberto.upswing.dtos.academic.CourseSelect;
import br.com.noberto.upswing.models.*;
import br.com.noberto.upswing.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class FilterStudentByAddress{
    private static AutoApplyRepository autoApplyRepository;
    private static CompanyRepository companyRepository;
    private static StudentRepository studentRepository;
    private static VacancyAndCourseRepository vacancyAndCourseRepository;

    @Autowired
    public FilterStudentByAddress(AutoApplyRepository autoApplyRepository, CompanyRepository companyRepository,
                                  StudentRepository studentRepository, VacancyAndCourseRepository vacancyAndCourseRepository) {
        FilterStudentByAddress.autoApplyRepository = autoApplyRepository;
        FilterStudentByAddress.companyRepository = companyRepository;
        FilterStudentByAddress.studentRepository = studentRepository;
        FilterStudentByAddress.vacancyAndCourseRepository = vacancyAndCourseRepository;
    }

    public static List<Student> filterStudentsByAddressAndContract(JobOffer jobOffer) {
        Company company = companyRepository.getReferenceById(jobOffer.getCompany().getId());

        //Retorna uma lista dos cursos selecionados pela empresa durante a oferta da vaga e a converte em uma lista de CourseSelect
        List<CourseSelect> courseSelects = vacancyAndCourseRepository.findByCoursesExistsInVacancyAndCourse(jobOffer.getId())
                .stream().map(CourseSelect::new)
                .toList();

        /*Filtra as listas 'studentsAutoApply', 'studentState' e 'studentsInCourse' adicionando a lista 'students' apenas
         uma instancia de um objeto do tipo aluno*/
        List<Student> students = FilterExistingStudentsOnCourse.returnStudentsExistsInCourse(courseSelects).stream()
                .filter(studentA -> studentRepository.findByStateTrue(jobOffer.getCompany().getId()).stream()
                        .anyMatch(studentA::equals) || studentRepository.findByAutoApplyTrue().stream().anyMatch(studentA::equals))
                .distinct()
                .toList();

        /*Percorre toda lista 'students' mantendo em 'studentsByAddress' apenas os alunos que retornam verdadeiro para as
        condicionais feitas*/
        List<Student> studentsByAddress = new ArrayList<>();
        for (Student student : students) {
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

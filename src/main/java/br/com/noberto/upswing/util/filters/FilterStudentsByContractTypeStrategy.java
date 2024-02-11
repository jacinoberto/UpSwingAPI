package br.com.noberto.upswing.util.filters;

import br.com.noberto.upswing.models.AutoApply;
import br.com.noberto.upswing.models.JobOffer;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.AutoApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class FilterStudentsByContractTypeStrategy implements IFilterStudentStrategy {

    private final AutoApplyRepository autoApplyRepository;
    private final FilterStudentByAddressStrategy filterStudentByAddress;

    @Autowired
    public FilterStudentsByContractTypeStrategy(AutoApplyRepository autoApplyRepository, FilterStudentByAddressStrategy filterStudentByAddress) {
        this.autoApplyRepository = autoApplyRepository;
        this.filterStudentByAddress = filterStudentByAddress;
    }

    public List<Student> filterStudents(JobOffer jobOffer){
        List<Student> qualifiedStudents = new ArrayList<>();
        List<Student> students = filterStudentByAddress.filterStudents(jobOffer);

        for (Student student : students){
            AutoApply autoApply = autoApplyRepository.findByStudentPresentAutoApply(student.getId());

            switch (autoApply.getContract()){
                case INTERNSHIP, FIXED -> {
                    if (Objects.equals(autoApply.getContract(), jobOffer.getContract()))
                        qualifiedStudents.add(student);
                }
                default -> qualifiedStudents.add(student);
            }
        }
        return qualifiedStudents;
    }
}

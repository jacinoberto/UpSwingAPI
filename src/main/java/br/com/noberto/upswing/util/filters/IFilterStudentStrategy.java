package br.com.noberto.upswing.util.filters;

import br.com.noberto.upswing.models.JobOffer;
import br.com.noberto.upswing.models.Student;

import java.util.List;

public interface IFilterStudentStrategy {

    List<Student> filterStudents(JobOffer jobOffer);
}

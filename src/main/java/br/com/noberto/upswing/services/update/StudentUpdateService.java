package br.com.noberto.upswing.services.update;

import br.com.noberto.upswing.dtos.company.UnapplyVacancy;
import br.com.noberto.upswing.dtos.student.SocialNetworksUpdate;
import br.com.noberto.upswing.models.SocialNetworks;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.models.VacancyOffer;
import br.com.noberto.upswing.repositories.StudentRepository;
import br.com.noberto.upswing.repositories.VacancyOfferRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentUpdateService{
    private final StudentRepository studentRepository;
    private final VacancyOfferRepository vacancyOfferRepository;

    public StudentUpdateService(StudentRepository studentRepository, VacancyOfferRepository vacancyOfferRepository) {
        this.studentRepository = studentRepository;
        this.vacancyOfferRepository = vacancyOfferRepository;
    }

    public Student studentUpdateSocialNetworks(String studentId, SocialNetworksUpdate socialNetworksUpdate){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityExistsException("Aluno não encontrado!"));
        SocialNetworks socialNetworks = student.getSocialNetworks();

        socialNetworks.setSocialOne(socialNetworksUpdate.socialOne());
        socialNetworks.setSocialTwo(socialNetworksUpdate.socialTwo());
        socialNetworks.setSocialThree(socialNetworksUpdate.socialThree());
        socialNetworks.setSocialFour(socialNetworksUpdate.socialFour());

        student.setSocialNetworks(socialNetworks);
        return student;
    }

    public VacancyOffer studentAlterApplicationVacancy(UnapplyVacancy unapplyVacancy){
        VacancyOffer vacancyOffer = vacancyOfferRepository.findByJobOffer(unapplyVacancy.jobOfferId(), unapplyVacancy.studentId());
        vacancyOffer.setStatus(false);
        return vacancyOffer;
    }
}

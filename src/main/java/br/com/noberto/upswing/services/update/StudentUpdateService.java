package br.com.noberto.upswing.services.update;

import br.com.noberto.upswing.dtos.student.SocialNetworksUpdate;
import br.com.noberto.upswing.models.SocialNetworks;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.StudentRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentUpdateService{
    private final StudentRepository studentRepository;

    public StudentUpdateService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student studentUpdateSocialNetworks(UUID studentId, SocialNetworksUpdate socialNetworksUpdate){
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
}

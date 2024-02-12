package br.com.noberto.upswing.services.update;

import br.com.noberto.upswing.dtos.admin.AdminUpdate;
import br.com.noberto.upswing.models.Account;
import br.com.noberto.upswing.models.Admin;
import br.com.noberto.upswing.repositories.AdminRepository;
import br.com.noberto.upswing.repositories.ClassRepository;
import br.com.noberto.upswing.repositories.CourseRepository;
import br.com.noberto.upswing.repositories.StudentRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminUpdateService {
    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final CourseRepository courseRepository;

    public AdminUpdateService(AdminRepository adminRepository, StudentRepository studentRepository, ClassRepository
            classRepository, CourseRepository courseRepository) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
        this.classRepository = classRepository;
        this.courseRepository = courseRepository;
    }

    public Admin updateAdmin(UUID adminId, AdminUpdate adminUpdate){
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new EntityExistsException("Administrador não encontrado!"));
        Account account = admin.getAccount();
        
        admin.setPosition(adminUpdate.position());
        account.setName(adminUpdate.account().getName());
        account.setEmail((adminUpdate.account().getEmail().equalsIgnoreCase(admin.getAccount().getEmail()))
                ? account.getEmail() : adminUpdate.account().getEmail());
        account.setMainPhone(adminUpdate.account().getMainPhone());
        account.setOptionalPhone(adminUpdate.account().getOptionalPhone());
        admin.setAccount(account);
        return admin;
    }
}

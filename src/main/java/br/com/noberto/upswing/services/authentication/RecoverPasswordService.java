package br.com.noberto.upswing.services.authentication;

import br.com.noberto.upswing.configurations.security.TokenService;
import br.com.noberto.upswing.models.Admin;
import br.com.noberto.upswing.models.Company;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.AdminRepository;
import br.com.noberto.upswing.repositories.CompanyRepository;
import br.com.noberto.upswing.repositories.StudentRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecoverPasswordService {
    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final TokenService tokenService;

    @Autowired
    public RecoverPasswordService(AdminRepository adminRepository, StudentRepository studentRepository, CompanyRepository
            companyRepository, TokenService tokenService) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
        this.tokenService = tokenService;
    }

    public String tokenForPasswordRecovery(String email){
        String token = null;

        if (adminRepository.findAdminByEmail(email).isPresent()){
            Admin admin = adminRepository.findAdminByEmail(email).
                    orElseThrow(() -> new EntityExistsException("Administrador não encontrado!"));
            token = tokenService.generateToken(admin);
        }
        if (studentRepository.findStudentByEmail(email).isPresent()){
            Student student = studentRepository.findStudentByEmail(email)
                    .orElseThrow(() -> new EntityExistsException("Aluno não encontrado!"));
            token = tokenService.generateTokenStudent(student);
        }
        if (companyRepository.findCompanyByEmail(email).isPresent()){
            Company company = companyRepository.findCompanyByEmail(email)
                    .orElseThrow(() -> new EntityExistsException("Empresa não encontrada!"));
            token = tokenService.generateTokenCompany(company);
        }

        return token;
    }
}

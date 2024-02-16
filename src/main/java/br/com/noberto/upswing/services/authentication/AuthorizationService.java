package br.com.noberto.upswing.services.authentication;

import br.com.noberto.upswing.repositories.AdminRepository;
import br.com.noberto.upswing.repositories.CompanyRepository;
import br.com.noberto.upswing.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    AdminRepository adminRepository;

    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public AuthorizationService(StudentRepository studentRepository, CompanyRepository companyRepository) {
        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        if (adminRepository.findByEmail(email) != null) return userDetails = adminRepository.findByEmail(email);
        if (studentRepository.findByEmail(email) != null) return userDetails = studentRepository.findByEmail(email);
        if (companyRepository.findByEmail(email) != null) return userDetails = companyRepository.findByEmail(email);

        return userDetails;
    }
}

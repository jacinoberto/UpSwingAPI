package br.com.noberto.upswing.configurations.security;

import br.com.noberto.upswing.models.Admin;
import br.com.noberto.upswing.models.Company;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.AdminRepository;
import br.com.noberto.upswing.repositories.CompanyRepository;
import br.com.noberto.upswing.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    AdminRepository adminRepository;

    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;

    public SecurityFilter(StudentRepository studentRepository, CompanyRepository companyRepository) {
        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if(token != null){
            UserDetails user = null;
            var userId = tokenService.validateToken(token);

            if (adminRepository.existsById(userId)) {
                Admin admin = adminRepository.findById(userId)
                        .orElseThrow(() -> new EntityNotFoundException("Admin não encontrado!"));
                user = adminRepository.findByEmail(admin.getAccount().getEmail());
            }
            if (studentRepository.existsById(userId)) {
                Student student = studentRepository.findById(userId)
                        .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado!"));
                user = studentRepository.findByEmail(student.getAccount().getEmail());
            }
            if (companyRepository.existsById(userId)) {
                Company company = companyRepository.findById(userId)
                        .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrado!"));
                user = companyRepository.findByEmail(company.getAccount().getEmail());
            }

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}

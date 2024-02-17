package br.com.noberto.upswing.controllers.authentication;

import br.com.noberto.upswing.configurations.security.TokenService;
import br.com.noberto.upswing.dtos.authorization.Authentication;
import br.com.noberto.upswing.dtos.authorization.TokenResponse;
import br.com.noberto.upswing.models.Admin;
import br.com.noberto.upswing.models.Company;
import br.com.noberto.upswing.models.Student;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/admin")
    public ResponseEntity login(@RequestBody @Valid Authentication data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Admin) auth.getPrincipal());

        return ResponseEntity.ok(new TokenResponse(token));
    }

    @PostMapping("/student")
    public ResponseEntity student(@RequestBody @Valid Authentication data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateTokenStudent((Student) auth.getPrincipal());

        return ResponseEntity.ok(new TokenResponse(token));
    }

    @PostMapping("/company")
    public ResponseEntity company(@RequestBody @Valid Authentication data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateTokenCompany((Company) auth.getPrincipal());

        return ResponseEntity.ok(new TokenResponse(token));
    }
}

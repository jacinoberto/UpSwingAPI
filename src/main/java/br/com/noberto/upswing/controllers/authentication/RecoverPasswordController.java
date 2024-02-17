package br.com.noberto.upswing.controllers.authentication;

import br.com.noberto.upswing.dtos.authorization.RecoverPassword;
import br.com.noberto.upswing.dtos.authorization.TokenResponse;
import br.com.noberto.upswing.services.authentication.RecoverPasswordService;
import br.com.noberto.upswing.services.mail.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/recover-password")
public class RecoverPasswordController {
    private final RecoverPasswordService service;
    private final EmailService emailService;

    public RecoverPasswordController(RecoverPasswordService service, EmailService emailService) {
        this.service = service;
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<TokenResponse> tokenForPasswordRecovery(@RequestParam String email) throws MessagingException {
        String token = service.tokenForPasswordRecovery(email);
        emailService.emailRecoverPassword(token, email);
        return ResponseEntity.ok(new TokenResponse(token));
    }

    @PatchMapping("/{userId}")
    @Transactional
    public ResponseEntity<Void> newPassword(@PathVariable String userId, @RequestBody RecoverPassword recoverPassword){
        service.newPassword(userId, recoverPassword);
        return ResponseEntity.ok().build();
    }
}

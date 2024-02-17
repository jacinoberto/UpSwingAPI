package br.com.noberto.upswing.util.emails;

import org.springframework.stereotype.Component;

@Component
public class EmailRecoverPassword {
    public String emailRecoverPassword(String token){
        return String.format("""
                <!DOCTYPE html>
                <html lang="pt-br">
                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <title>Recuração de Senha</title>
                    </head>
                                
                    <body>
                        <p>Olá,</p>
                        <p>Você está recebendo este e-mail porque solicitou a recuperação de senha para sua conta.</p>
                        <p>Por favor, clique no link abaixo para redefinir sua senha:</p>
                        <p><a href="upswing.com/recover-password?token=%s">upswing.com</a><br></p>
                        <p>Se você não solicitou esta alteração, ignore este e-mail. Nenhuma alteração será feita em sua conta.</p>
                        <p>Atenciosamente,</p>
                        <p>Upswing</p>
                    </body>
                </html>
                """, token);
    }
}

package br.com.noberto.upswing.util.emails;

import br.com.noberto.upswing.email.EmailRequest;
import br.com.noberto.upswing.models.Company;
import br.com.noberto.upswing.models.JobOffer;
import br.com.noberto.upswing.models.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentEmailToSend {

//    public EmailRequest getWelcomeEmail(Student student) {
//        EmailRequest emailRequest = new EmailRequest();
//
//        emailRequest.setEmailTo(student.getAccount().getEmail());
//        emailRequest.setSubject("Upswing te dá as boas vindas!");
//        emailRequest.setMessage("Olá " + student.getAccount().getName() + ",\n" +
//                "Seja bem-vindo(a) ao Upswing.\n\n" +
//                "Agora você tem uma ampla gama de recursos e funcionalidades que torna " +
//                "a sua entrada no mercado de trabalho em algo mais acertivo. O ambiente é intuitivo e de facil acesso " +
//                "para que todas as suas necessidades sejam atendidas. Siga as dicas a seguir para poder acessar o sistema" +
//                " e realizar configurações em seu perfil:\n\n" +
//                "Login no Sistema: acesse o sistema com o seu e-mail que foi passado a instituição no momento em que realizou" +
//                " a sua matricula, e use como senha uma hashtag(#) seguida das duas primeiras letras do seu nome, respeitando" +
//                " a primeira letra maiúscula, junto dos três primeiros números do seu CPF.\n\n" +
//                "Configurações de Auto Aplicações: no seu primeiro login você será levado a uma pagína onde poderá personalizar" +
//                " a as suas preferências para a sua notificação de vagas quando postadas.\n\n" +
//                "Personalise Seu Perfil: atualize as suas informações de contato e compartilhe conosco o que faz no momento." +
//                " Quanto mais completo o seu perfil melhor será a sua experiência.\n\n" +
//                "Explorar o Sistema: você tem passe livre para ver outros cursos ofertados pela sua Intituição de Ensino, " +
//                "assim como também pode buscar por vagas manualmente.\n\n" +
//                "Se tiver alguma dificuldade pode entrar em contato com nosso suporte ou procurar o setor da sua Instituição" +
//                " de Ensino com acesso administrativo ao sistema.\n\n" +
//                "Atenciosamento, Upswing.");
//        return emailRequest;
//    }

    public String getWelcomeEmail(Student student) {
        return """
                <html lang="en">
                                
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Exemplo de E-mail</title>
                    <style>
                        /* Estilos inline para melhor compatibilidade */
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #fff;
                            margin: 0;
                            padding: 0;
                        }
                                
                        .container {
                            max-width: 600px;
                            margin: 0 auto;
                            padding: 20px;
                            background-color: hsl(226, 96%, 98%);
                        }
                                
                        .header {
                            text-align: center;
                            padding: 20px 0px;
                        }
                                
                        .content {
                            padding: 20px 0;
                            color: hsl(228, 30%, 30%);
                            font-weight: medium;
                            font-size: 1rem
                        }
                                
                        .footer {
                            text-align: center;
                            padding-top: 20px;
                            border-top: 1px solid #ccc;
                        }
                        .logo{
                                width: 40px;
                                height: 40px;              \s
                        }
                        .wordmark{
                            width: 162px;
                            height: 37px;
                        }
                        .salutation {
                            font-weight: bold;
                            padding: 0rem 0.5rem;
                        }
                        .text-message {
                            padding: 0rem 0.5rem;
                        }
                        .access-link {
                            padding: 0.5rem 1rem;
                            display: block;
                            width: fit-content;
                            margin-top: 1rem;
                            font-size: 1rem;
                            color: white;
                            background-color: hsl(214, 96%, 50%);
                            border: 1px solid #f5f7ff;
                            border-radius: 0.2rem;
                            cursor: pointer;
                            text-decoration: none;
                            margin-bottom: 2rem;
                        }
                        .footer a{
                                padding: 0.5rem;
                                background-color: white;
                                border-radius: 50%;
                                display: inline-block;
                                position: relative;
                            }
                            .social-links img {
                                position: absolute;
                                top: 50%;
                                left: 50%;
                                transform: translate(-50%, -50%);
                            }
                            .links{
                                width: 100%;
                                display: inline-block;
                                flex-direction: column;
                                align-items: center;
                                justify-content: center;
                                gap: 1rem;
                            }
                            .end {
                                color: hsl(228, 30%, 50%);
                                font-weight: medium; font-size:
                                1rem
                            }
                           \s
                    </style>
                </head>
                                
                <body>
                    <div class="container">
                        <div class="header">
                            <img src="https://lh3.googleusercontent.com/pw/ABLVV86K0k3ihMB-pnv4ZtIsqpfVmz2OteBLdYwTFcrOq5i3uaQOEbSDIsx7jXSH6W-FMGNCMzZrLSqpaSrMKmq8LdatJh6DceUFSCyqtL1d_eB0woZYTa1gYpjRE5qcm_M2QSDmas5ljk0WwQuM43ZrAyk=w163-h163-s-no-gm?authuser=0" alt="" class="logo">
                            <img src="https://lh3.googleusercontent.com/pw/ABLVV85FW95deOme-9vKiVwQ0H-gWMJxF3JORxclLYzvvZlq_rKUo9O5FK8D8GjYB3jeCAnMnp6WDTHBXedpv_pn1RnfIK5ICTM9tvq_wUMZpD8ayzbMSH1d6L3HPtHhmR_zgoiz0D5nbqx0LmiKcN6N6t0=w725-h163-s-no-gm?authuser=0" alt="" class="wordmark">
                        </div>
                        <div class="content">
                            <p class="salutation">Olá usuario</p>
                            <p class="text-message">Seja bem-vindo(a) ao Upswing.</p>
                            <p class="text-message">Agora você tem uma ampla gama de recursos e
                            funcionalidades que torna a sua entrada no mercado de trabalho em
                            algo mais assertivo. O ambiente é intuitivo e de fácil acesso
                            para que todas as suas necessidades sejam atendidas. Siga as
                            dicas a seguir para poder acessar o sistema e realizar configurações
                            em seu perfil:</p>
                            <p class="text-message">
                            <b>Login no Sistema:</b> acesse o sistema com o seu e-mail que
                            foi passado a instituição no momento em que realizou a sua matricula,
                            e use como senha uma hashtag(#) seguida das duas primeiras letras do
                            seu nome, respeitando a primeira letra maiúscula, junto dos três primeiros
                            números do seu CPF. <br>
                                
                            <b>Configurações de Auto Aplicações:</b> no seu primeiro login você será
                            levado a uma pagina onde poderá personalizar a as suas preferências para
                            a sua notificação de vagas quando postadas.<br>
                                
                            <b>Personalize Seu Perfil:</b> atualize as suas informações de contato e
                            compartilhe conosco o que faz no momento. Quanto mais completo o seu perfil
                            melhor será a sua experiência.<br>
                                
                            <b>Explorar o Sistema:</b> você tem passe livre para ver outros cursos ofertados
                            pela sua Instituição de Ensino, assim como também pode buscar por vagas
                            manualmente.<br>
                            </p>
                            <p class="text-message">Se tiver alguma dificuldade pode entrar em contato com nosso
                            suporte ou procurar o setor da sua Instituição de Ensino com acesso administrativo ao
                            sistema.</p>
                            <p class="text-message">Atenciosamente,<br> Upswing</p>
                        </div>
                        <div>
                            <a href="" class="access-link">Ir para o site</a>
                        </div>
                        <div class="footer">
                            <div class="links">
                                <a href="https://www.instagram.com/"><img src="https://lh3.googleusercontent.com/pw/ABLVV877C20_dkc4iwjW8mrL4FlZB1Fz3yfqaxedrUA0wn5ohS-SSVLc0OXYpCskTaG4pB_h4DuQI9VWq4xCG_N7cJ0jjjYj4JFs-TIN-n3vE1uX_HZ5Rrvb2SU2rMrbj6DCyjbIWYL5w1UWjzb1Rn56In0=w24-h24-s-no-gm?authuser=0" alt=""></a>
                                <a href="https://twitter.com/?lang=pt-br"><img src="https://lh3.googleusercontent.com/pw/ABLVV86Qm9OmO6vgT9AhPrP5G9FRbGbFnJKlhNWsh7zQroBKVfylCbMqLrq-s40LhKZbyie7gp9Bv7mJtLkFlNkCpRpgw1CA5ch_OlJGZn7TR70hT8FuXRF4kl4TMpmgzM_2jC9bIJm8OUZGPFFRNIdESdU=w24-h24-s-no-gm?authuser=0" alt=""></a>
                                <a href="https://br.linkedin.com"><img src="https://lh3.googleusercontent.com/pw/ABLVV85uEZOETnBr_ynbJ3LEOVdM83mQpDk_DUCq3Az2R9rjg6xCtspoDn09ia-isufNChGBhkaQiNEo0UgLvC4VgA8GPtSWJzUyg2FHUzk_1jpdQb_T67eqBuQBj1OcIUo88HWylBAG4z6qlLp089CfImw=w24-h24-s-no-gm?authuser=0" alt=""></a>               \s
                            </div>
                            <p class="end">Upswing 2024</p>
                        </div>
                    </div>
                </body>
                                
                </html>                                
                """;
    }

    public EmailRequest getAppliedVacancyEmail(Student student, Company company, JobOffer jobOffer) {
        EmailRequest emailRequest = new EmailRequest();

        emailRequest.setEmailTo(student.getAccount().getEmail());
        emailRequest.setSubject("Temos uma nova oportunidade compatível com seu perfil!");
        emailRequest.setMessage("Olá " + student.getAccount().getName() + ",\n\n" +
                "Passando aqui para informar que a empresa " + company.getAccount().getName() + " acaba de postar uma" +
                " nova vaga para " + jobOffer.getPosition() + " que é compatível com seu perfil.\n\n" +
                "Sabemos o quão importante é pra você encontrar oportunidades relevantes que complementam" +
                " sua formação acadêmica, e é por isso que o nosso maior comprometimento é te manter informado sobre todas" +
                " as vagas que melhor se adequam a você.\n\n" +
                "Para poder se candidatar e ter mais detalhes sobre essa vaga acesse o Upswing em upswing.com.\n" +
                "Aproveite esta oportunidade para explorar novas possibilidades e continuar a desenvolver suas habilidades " +
                "enquanto avança em sua jornada acadêmica.\n\n" +
                "Atenciosamente,\n" +
                "Upswing.");
        return emailRequest;
    }
}

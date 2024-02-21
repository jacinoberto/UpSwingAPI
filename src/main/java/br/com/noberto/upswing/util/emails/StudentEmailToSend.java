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
        return "<html lang=\"pt-br\">\n" +
               "\n" +
               "    <head>\n" +
               "        <meta charset=\"UTF-8\">\n" +
               "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
               "        <title>Exemplo de E-mail</title>\n" +
               "        <style>\n" +
               "            /* Estilos inline para melhor compatibilidade */\n" +
               "            body {\n" +
               "                font-family: Arial, sans-serif;\n" +
               "                background-color: #fff;\n" +
               "                margin: 0;\n" +
               "                padding: 0;\n" +
               "            }\n" +
               "\n" +
               "            .container {\n" +
               "                max-width: 600px;\n" +
               "                margin: 0 auto;\n" +
               "                padding: 20px;\n" +
               "                background-color: hsl(226, 96%, 98%);\n" +
               "            }\n" +
               "\n" +
               "            .header {\n" +
               "                text-align: center;\n" +
               "                padding: 20px 0px;\n" +
               "            }\n" +
               "\n" +
               "            .content {\n" +
               "                padding: 20px 0;\n" +
               "                color: hsl(228, 30%, 30%);\n" +
               "                font-weight: medium;\n" +
               "                font-size: 1rem\n" +
               "            }\n" +
               "\n" +
               "            .footer {\n" +
               "                text-align: center;\n" +
               "                padding-top: 20px;\n" +
               "                border-top: 1px solid #ccc;\n" +
               "            }\n" +
               "            .logo{\n" +
               "                    width: 40px;\n" +
               "                    height: 40px;               \n" +
               "            }\n" +
               "            .wordmark{\n" +
               "                width: 162px;\n" +
               "                height: 37px;\n" +
               "            }\n" +
               "            .salutation {\n" +
               "                font-weight: bold;\n" +
               "                padding: 0rem 0.5rem;\n" +
               "            }\n" +
               "            .text-message {\n" +
               "                padding: 0rem 0.5rem;\n" +
               "            }\n" +
               "            .access-link {\n" +
               "                padding: 0.5rem 1rem;\n" +
               "                display: block;\n" +
               "                width: fit-content;\n" +
               "                margin-top: 1rem;\n" +
               "                font-size: 1rem;\n" +
               "                color: white;\n" +
               "                background-color: hsl(214, 96%, 50%);\n" +
               "                border: 1px solid #f5f7ff;\n" +
               "                border-radius: 0.2rem;\n" +
               "                cursor: pointer;\n" +
               "                text-decoration: none;\n" +
               "                margin-bottom: 2rem;\n" +
               "            }\n" +
               "            .footer a{\n" +
               "                    padding: 0.5rem;\n" +
               "                    background-color: white;\n" +
               "                    border-radius: 50%;\n" +
               "                    display: inline-block;\n" +
               "                    position: relative;\n" +
               "                }\n" +
               "                .social-links img {\n" +
               "                    position: absolute;\n" +
               "                    top: 50%;\n" +
               "                    left: 50%;\n" +
               "                    transform: translate(-50%, -50%);\n" +
               "                }\n" +
               "                .links{\n" +
               "                    width: 100%;\n" +
               "                    display: inline-block;\n" +
               "                    flex-direction: column;\n" +
               "                    align-items: center;\n" +
               "                    justify-content: center;\n" +
               "                    gap: 1rem;\n" +
               "                }\n" +
               "                .end {\n" +
               "                    color: hsl(228, 30%, 50%);\n" +
               "                    font-weight: medium; font-size:\n" +
               "                    1rem\n" +
               "                }\n" +
               "                \n" +
               "        </style>\n" +
               "    </head>\n" +
               "\n" +
               "    <body>\n" +
               "        <div class=\"container\">\n" +
               "            <div class=\"header\">\n" +
               "                <img src=\"https://lh3.googleusercontent.com/pw/ABLVV86K0k3ihMB-pnv4ZtIsqpfVmz2OteBLdYwTFcrOq5i3uaQOEbSDIsx7jXSH6W-FMGNCMzZrLSqpaSrMKmq8LdatJh6DceUFSCyqtL1d_eB0woZYTa1gYpjRE5qcm_M2QSDmas5ljk0WwQuM43ZrAyk=w163-h163-s-no-gm?authuser=0\" alt=\"\" class=\"logo\">\n" +
               "                <img src=\"https://lh3.googleusercontent.com/pw/ABLVV85FW95deOme-9vKiVwQ0H-gWMJxF3JORxclLYzvvZlq_rKUo9O5FK8D8GjYB3jeCAnMnp6WDTHBXedpv_pn1RnfIK5ICTM9tvq_wUMZpD8ayzbMSH1d6L3HPtHhmR_zgoiz0D5nbqx0LmiKcN6N6t0=w725-h163-s-no-gm?authuser=0\" alt=\"\" class=\"wordmark\">\n" +
               "            </div>\n" +
               "            <div class=\"content\">\n" +
               "                <p class=\"salutation\">Olá " + student.getAccount().getName() +"</p>\n" +
               "\n" +
               "                <p class=\"text-message\">Seja bem-vindo(a) ao Upswing.</p>\n" +
               "                <p class=\"text-message\">Agora você tem uma ampla gama de recursos e\n" +
               "                funcionalidades que torna a sua entrada no mercado de trabalho em\n" +
               "                algo mais assertivo. O ambiente é intuitivo e de fácil acesso\n" +
               "                para que todas as suas necessidades sejam atendidas. Siga as\n" +
               "                dicas a seguir para poder acessar o sistema e realizar configurações\n" +
               "                em seu perfil:</p>\n" +
               "\n" +
               "                <p class=\"text-message\">\n" +
               "                    <b>Login no Sistema:</b> acesse o sistema com o seu e-mail que\n" +
               "                    foi passado a instituição no momento em que realizou a sua matricula,\n" +
               "                    e use como senha uma hashtag(#) seguida das duas primeiras letras do\n" +
               "                    seu nome, respeitando a primeira letra maiúscula, junto dos três primeiros\n" +
               "                    números do seu CPF. <br>\n" +
               "\n" +
               "                    <b>Configurações de Auto Aplicações:</b> no seu primeiro login você será\n" +
               "                    levado a uma pagina onde poderá personalizar a as suas preferências para\n" +
               "                    a sua notificação de vagas quando postadas.<br>\n" +
               "\n" +
               "                    <b>Personalize Seu Perfil:</b> atualize as suas informações de contato e\n" +
               "                    compartilhe conosco o que faz no momento. Quanto mais completo o seu perfil\n" +
               "                    melhor será a sua experiência.<br>\n" +
               "\n" +
               "                    <b>Explorar o Sistema:</b> você tem passe livre para ver outros cursos ofertados\n" +
               "                    pela sua Instituição de Ensino, assim como também pode buscar por vagas\n" +
               "                    manualmente.<br>\n" +
               "                </p>\n" +
               "\n" +
               "                <p class=\"text-message\">Se tiver alguma dificuldade pode entrar em contato com nosso\n" +
               "                suporte ou procurar o setor da sua Instituição de Ensino com acesso administrativo ao\n" +
               "                sistema.</p>\n" +
               "                \n" +
               "                <p class=\"text-message\">Atenciosamente,<br> Upswing</p>\n" +
               "            </div>\n" +
               "            <div>\n" +
               "                <a href=\"\" class=\"access-link\">Ir para o site</a>\n" +
               "            </div>\n" +
               "            <div class=\"footer\">\n" +
               "                <div class=\"links\">\n" +
               "                    <a href=\"https://www.instagram.com/\"><img src=\"https://lh3.googleusercontent.com/pw/ABLVV877C20_dkc4iwjW8mrL4FlZB1Fz3yfqaxedrUA0wn5ohS-SSVLc0OXYpCskTaG4pB_h4DuQI9VWq4xCG_N7cJ0jjjYj4JFs-TIN-n3vE1uX_HZ5Rrvb2SU2rMrbj6DCyjbIWYL5w1UWjzb1Rn56In0=w24-h24-s-no-gm?authuser=0\" alt=\"\"></a>\n" +
               "                    <a href=\"https://twitter.com/?lang=pt-br\"><img src=\"https://lh3.googleusercontent.com/pw/ABLVV86Qm9OmO6vgT9AhPrP5G9FRbGbFnJKlhNWsh7zQroBKVfylCbMqLrq-s40LhKZbyie7gp9Bv7mJtLkFlNkCpRpgw1CA5ch_OlJGZn7TR70hT8FuXRF4kl4TMpmgzM_2jC9bIJm8OUZGPFFRNIdESdU=w24-h24-s-no-gm?authuser=0\" alt=\"\"></a>\n" +
               "                    <a href=\"https://br.linkedin.com\"><img src=\"https://lh3.googleusercontent.com/pw/ABLVV85uEZOETnBr_ynbJ3LEOVdM83mQpDk_DUCq3Az2R9rjg6xCtspoDn09ia-isufNChGBhkaQiNEo0UgLvC4VgA8GPtSWJzUyg2FHUzk_1jpdQb_T67eqBuQBj1OcIUo88HWylBAG4z6qlLp089CfImw=w24-h24-s-no-gm?authuser=0\" alt=\"\"></a>                \n" +
               "                </div>\n" +
               "                <p class=\"end\">Upswing 2024</p>\n" +
               "            </div>\n" +
               "        </div>\n" +
               "    </body>\n" +
               "\n" +
               "</html>";
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

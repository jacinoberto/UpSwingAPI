package br.com.noberto.upswing.util.emails;

import br.com.noberto.upswing.email.EmailRequest;
import br.com.noberto.upswing.models.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyEmailToSend implements IEmailToSendStrategy {
    @Override
    public String emailPending(Object object) {
        Company company = (Company) object;

        return "<html lang=\"en\">\n" +
               "\n" +
               "<head>\n" +
               "    <meta charset=\"UTF-8\">\n" +
               "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
               "    <title>Exemplo de E-mail</title>\n" +
               "    <style>\n" +
               "        /* Estilos inline para melhor compatibilidade */\n" +
               "        body {\n" +
               "            font-family: Arial, sans-serif;\n" +
               "            background-color: #fff;\n" +
               "            margin: 0;\n" +
               "            padding: 0;\n" +
               "        }\n" +
               "\n" +
               "        .container {\n" +
               "            max-width: 600px;\n" +
               "            margin: 0 auto;\n" +
               "            padding: 20px;\n" +
               "            background-color: hsl(226, 96%, 98%);\n" +
               "        }\n" +
               "\n" +
               "        .header {\n" +
               "            text-align: center;\n" +
               "            padding: 20px 0px;\n" +
               "        }\n" +
               "\n" +
               "        .content {\n" +
               "            padding: 20px 0;\n" +
               "            color: hsl(228, 30%, 30%);\n" +
               "            font-weight: medium;\n" +
               "            font-size: 1rem\n" +
               "        }\n" +
               "\n" +
               "        .footer {\n" +
               "            text-align: center;\n" +
               "            padding-top: 20px;\n" +
               "            border-top: 1px solid #ccc;\n" +
               "        }\n" +
               "        .logo{\n" +
               "                width: 40px;\n" +
               "                height: 40px;               \n" +
               "        }\n" +
               "        .wordmark{\n" +
               "            width: 162px;\n" +
               "            height: 37px;\n" +
               "        }\n" +
               "        .salutation {\n" +
               "            font-weight: bold;\n" +
               "            padding: 0rem 0.5rem;\n" +
               "        }\n" +
               "        .text-message {\n" +
               "            padding: 0rem 0.5rem;\n" +
               "        }\n" +
               "        .access-link {\n" +
               "            padding: 0.5rem 1rem;\n" +
               "            display: block;\n" +
               "            width: fit-content;\n" +
               "            margin-top: 1rem;\n" +
               "            font-size: 1rem;\n" +
               "            color: white;\n" +
               "            background-color: hsl(214, 96%, 50%);\n" +
               "            border: 1px solid #f5f7ff;\n" +
               "            border-radius: 0.2rem;\n" +
               "            cursor: pointer;\n" +
               "            text-decoration: none;\n" +
               "            margin-bottom: 2rem;\n" +
               "        }\n" +
               "        .footer a{\n" +
               "                padding: 0.5rem;\n" +
               "                background-color: white;\n" +
               "                border-radius: 50%;\n" +
               "                display: inline-block;\n" +
               "                position: relative;\n" +
               "            }\n" +
               "            .social-links img {\n" +
               "                position: absolute;\n" +
               "                top: 50%;\n" +
               "                left: 50%;\n" +
               "                transform: translate(-50%, -50%);\n" +
               "            }\n" +
               "            .links{\n" +
               "                width: 100%;\n" +
               "                display: inline-block;\n" +
               "                flex-direction: column;\n" +
               "                align-items: center;\n" +
               "                justify-content: center;\n" +
               "                gap: 1rem;\n" +
               "            }\n" +
               "            .end {\n" +
               "                color: hsl(228, 30%, 50%);\n" +
               "                font-weight: medium; font-size:\n" +
               "                1rem\n" +
               "            }\n" +
               "            \n" +
               "    </style>\n" +
               "</head>\n" +
               "\n" +
               "<body>\n" +
               "    <div class=\"container\">\n" +
               "        <div class=\"header\">\n" +
               "            <img src=\"https://lh3.googleusercontent.com/pw/ABLVV86K0k3ihMB-pnv4ZtIsqpfVmz2OteBLdYwTFcrOq5i3uaQOEbSDIsx7jXSH6W-FMGNCMzZrLSqpaSrMKmq8LdatJh6DceUFSCyqtL1d_eB0woZYTa1gYpjRE5qcm_M2QSDmas5ljk0WwQuM43ZrAyk=w163-h163-s-no-gm?authuser=0\" alt=\"\" class=\"logo\">\n" +
               "            <img src=\"https://lh3.googleusercontent.com/pw/ABLVV85FW95deOme-9vKiVwQ0H-gWMJxF3JORxclLYzvvZlq_rKUo9O5FK8D8GjYB3jeCAnMnp6WDTHBXedpv_pn1RnfIK5ICTM9tvq_wUMZpD8ayzbMSH1d6L3HPtHhmR_zgoiz0D5nbqx0LmiKcN6N6t0=w725-h163-s-no-gm?authuser=0\" alt=\"\" class=\"wordmark\">\n" +
               "        </div>\n" +
               "        <div class=\"content\">\n" +
               "            <p class=\"salutation\">Olá "+ company.getAccount().getName() +"</p>\n" +
               "\n" +
               "            <p class=\"text-message\">Gostaríamos de informar que recebemos a sua solicitação de\n" +
               "            cadastro no nosso sistema e estamos revisando as suas informações.</p>\n" +
               "\n" +
               "            <p class=\"text-message\">No momento seu perfil está pendente de aprovação. Estamos\n" +
               "            trabalhando para processar sua solicitação o mais rápido possível.</p>\n" +
               "\n" +
               "            <p class=\"text-message\">Entendemos que a agilidade na aprovação é crucial para que\n" +
               "            você possa começar a usufruir de todos os benefícios da nossa plataforma. Por isso,\n" +
               "            gostaríamos de te pedir um pouco mais de paciência enquanto finalizamos essa etapa.</p>\n" +
               "\n" +
               "            <p class=\"text-message\">Assim que seu perfil for aprovado você receberá outra notificação\n" +
               "            por e-mail com todas as informações necessárias para acessar e utilizar a nossa plataforma.</p>\n" +
               "\n" +
               "            <p class=\"text-message\">Agradecemos sua compreensão e estamos à disposição para quaisquer dúvidas\n" +
               "            ou informações adicionais.</p>  \n" +
               "\n" +
               "            <p class=\"text-message\">Atenciosamente,<br> Upswing</p> \n" +
               "        </div>\n" +
               "        <div class=\"footer\">\n" +
               "            <div class=\"links\">\n" +
               "                <a href=\"https://www.instagram.com/\"><img src=\"https://lh3.googleusercontent.com/pw/ABLVV877C20_dkc4iwjW8mrL4FlZB1Fz3yfqaxedrUA0wn5ohS-SSVLc0OXYpCskTaG4pB_h4DuQI9VWq4xCG_N7cJ0jjjYj4JFs-TIN-n3vE1uX_HZ5Rrvb2SU2rMrbj6DCyjbIWYL5w1UWjzb1Rn56In0=w24-h24-s-no-gm?authuser=0\" alt=\"\"></a>\n" +
               "                <a href=\"https://twitter.com/?lang=pt-br\"><img src=\"https://lh3.googleusercontent.com/pw/ABLVV85vcBZr_orstCNdhKo8pAMRzj30KNMIpxYwI2n59DKX6D6USWGCSqmskToiFrbUMvQ-ALY_8BHBNflMgzz5qJ6Fj3MkPhUUG60yzydGUgEHBAbwMZZhrAi7ghNP1grZgfCjxdGQpPmkWBlRu1eABVo=w20-h20-s-no-gm?authuser=0\" alt=\"\"></a>\n" +
               "                <a href=\"https://br.linkedin.com\"><img src=\"https://lh3.googleusercontent.com/pw/ABLVV85uEZOETnBr_ynbJ3LEOVdM83mQpDk_DUCq3Az2R9rjg6xCtspoDn09ia-isufNChGBhkaQiNEo0UgLvC4VgA8GPtSWJzUyg2FHUzk_1jpdQb_T67eqBuQBj1OcIUo88HWylBAG4z6qlLp089CfImw=w24-h24-s-no-gm?authuser=0\" alt=\"\"></a>                \n" +
               "            </div>\n" +
               "            <p class=\"end\">Upswing 2024</p>\n" +
               "        </div>\n" +
               "    </div>\n" +
               "</body>\n" +
               "\n" +
               "</html>";
    }

    @Override
    public String emailApproved(Object object) {
        Company company = (Company) object;

        return "<html lang=\"pt-br\">\n" +
               "\n" +
               "    <head>\n" +
               "        <meta charset=\"UTF-8\">\n" +
               "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
               "        <title>Exemplo de E-mail</title>\n" +
               "        <style>\n" +
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
               "                <p class=\"salutation\">Olá " + company.getAccount().getName() + ",</p>\n" +
               "\n" +
               "                <p class=\"text-message\">passando para te informar que seu perfil foi aprovado\n" +
               "                com sucesso!</p>\n" +
               "\n" +
               "                <p class=\"text-message\">Após uma revisão cuidadosa, concluímos que todas as\n" +
               "                informações fornecidas atendem aos nossos critérios de aprovação. Agora, você\n" +
               "                pode desfrutar de todos os recursos e benefícios disponíveis em nossa plataforma.</p>\n" +
               "\n" +
               "                <p class=\"text-message\">Seu acesso completo ao sistema está liberado, permitindo\n" +
               "                que você explore todas as funcionalidades e recursos disponíveis. Caso tenha alguma\n" +
               "                dúvida sobre como começar ou precise de assistência, não hesite em entrar em contato\n" +
               "                conosco. Estamos aqui para ajudar.</p>\n" +
               "\n" +
               "                <p class=\"text-message\">Agradecemos por escolher fazer parte de nossa comunidade e\n" +
               "                esperamos que sua experiência em nosso sistema seja produtiva e satisfatória.</p>\n" +
               "                \n" +
               "                <p class=\"text-message\">Atenciosamente,<br> Upswing</p>\n" +
               "            </div>\n" +
               "            <div>\n" +
               "                <a href=\"https://upswing-8bc8b.web.app/co/login\" class=\"access-link\">Ir para o site</a>\n" +
               "            </div>\n" +
               "            <div class=\"footer\">\n" +
               "                <div class=\"links\">\n" +
               "                    <a href=\"https://www.instagram.com/\"><img src=\"https://lh3.googleusercontent.com/pw/ABLVV877C20_dkc4iwjW8mrL4FlZB1Fz3yfqaxedrUA0wn5ohS-SSVLc0OXYpCskTaG4pB_h4DuQI9VWq4xCG_N7cJ0jjjYj4JFs-TIN-n3vE1uX_HZ5Rrvb2SU2rMrbj6DCyjbIWYL5w1UWjzb1Rn56In0=w24-h24-s-no-gm?authuser=0\" alt=\"\"></a>\n" +
               "                    <a href=\"https://twitter.com/?lang=pt-br\"><img src=\"https://lh3.googleusercontent.com/pw/ABLVV85vcBZr_orstCNdhKo8pAMRzj30KNMIpxYwI2n59DKX6D6USWGCSqmskToiFrbUMvQ-ALY_8BHBNflMgzz5qJ6Fj3MkPhUUG60yzydGUgEHBAbwMZZhrAi7ghNP1grZgfCjxdGQpPmkWBlRu1eABVo=w20-h20-s-no-gm?authuser=0\" alt=\"\"></a>\n" +
               "                    <a href=\"https://br.linkedin.com\"><img src=\"https://lh3.googleusercontent.com/pw/ABLVV85uEZOETnBr_ynbJ3LEOVdM83mQpDk_DUCq3Az2R9rjg6xCtspoDn09ia-isufNChGBhkaQiNEo0UgLvC4VgA8GPtSWJzUyg2FHUzk_1jpdQb_T67eqBuQBj1OcIUo88HWylBAG4z6qlLp089CfImw=w24-h24-s-no-gm?authuser=0\" alt=\"\"></a>                \n" +
               "                </div>\n" +
               "                <p class=\"end\">Upswing 2024</p>\n" +
               "            </div>\n" +
               "        </div>\n" +
               "    </body>\n" +
               "\n" +
               "</html>";
    }

    @Override
    public String emailNotApproved(Object object) {
        Company company = (Company) object;

        return "<html lang=\"pt-br\">\n" +
               "\n" +
               "    <head>\n" +
               "        <meta charset=\"UTF-8\">\n" +
               "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
               "        <title>Exemplo de E-mail</title>\n" +
               "        <style>\n" +
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
               "                <p class=\"salutation\">Olá "+ company.getAccount().getName() +",</p>\n" +
               "\n" +
               "                <p class=\"text-message\">Passando para te informar que, após uma análise cuidadosa,\n" +
               "                seu perfil em nossa plataforma não foi aprovado.</p>\n" +
               "\n" +
               "                <p class=\"text-message\">Identificamos algumas questões que não estão em conformidade\n" +
               "                com nossos critérios de qualidade ou políticas da empresa. Entendemos que isso pode\n" +
               "                ser decepcionante, mas estamos aqui para ajudá-lo a entender melhor os motivos dessa\n" +
               "                decisão e ajudá-lo a corrigir quaisquer problemas.</p>\n" +
               "\n" +
               "                <p class=\"text-message\">Alguns dos motivos comuns para a reprovação do perfil podem incluir:\n" +
               "                    <ul>\n" +
               "                        <li>Informações incompletas ou imprecisas sobre a empresa.</li>\n" +
               "                        <li>Falta de detalhes sobre os produtos ou serviços oferecidos.</li>\n" +
               "                        <li>Não atendimento aos requisitos específicos da nossa plataforma.</li>\n" +
               "                    </ul>\n" +
               "                </p>  \n" +
               "\n" +
               "                <p class=\"text-message\">Se você deseja mais informações sobre os motivos específicos\n" +
               "                da reprovação ou precisa de assistência para ajustar seu perfil, não hesite em entrar\n" +
               "                em contato conosco. Estamos aqui para ajudar e orientar você durante este processo.</p>\n" +
               "\n" +
               "                <p class=\"text-message\">Agradecemos pela sua compreensão e estamos à disposição para\n" +
               "                qualquer dúvida ou esclarecimento adicional que possa surgir.</p>\n" +
               "                \n" +
               "                <p class=\"text-message\">Atenciosamente,<br> Upswing</p>\n" +
               "            </div>\n" +
               "            <div class=\"footer\">\n" +
               "                <div class=\"links\">\n" +
               "                    <a href=\"https://www.instagram.com/\"><img src=\"https://lh3.googleusercontent.com/pw/ABLVV877C20_dkc4iwjW8mrL4FlZB1Fz3yfqaxedrUA0wn5ohS-SSVLc0OXYpCskTaG4pB_h4DuQI9VWq4xCG_N7cJ0jjjYj4JFs-TIN-n3vE1uX_HZ5Rrvb2SU2rMrbj6DCyjbIWYL5w1UWjzb1Rn56In0=w24-h24-s-no-gm?authuser=0\" alt=\"\"></a>\n" +
               "                    <a href=\"https://twitter.com/?lang=pt-br\"><img src=\"https://lh3.googleusercontent.com/pw/ABLVV85vcBZr_orstCNdhKo8pAMRzj30KNMIpxYwI2n59DKX6D6USWGCSqmskToiFrbUMvQ-ALY_8BHBNflMgzz5qJ6Fj3MkPhUUG60yzydGUgEHBAbwMZZhrAi7ghNP1grZgfCjxdGQpPmkWBlRu1eABVo=w20-h20-s-no-gm?authuser=0\" alt=\"\"></a>\n" +
               "                    <a href=\"https://br.linkedin.com\"><img src=\"https://lh3.googleusercontent.com/pw/ABLVV85uEZOETnBr_ynbJ3LEOVdM83mQpDk_DUCq3Az2R9rjg6xCtspoDn09ia-isufNChGBhkaQiNEo0UgLvC4VgA8GPtSWJzUyg2FHUzk_1jpdQb_T67eqBuQBj1OcIUo88HWylBAG4z6qlLp089CfImw=w24-h24-s-no-gm?authuser=0\" alt=\"\"></a>                \n" +
               "                </div>\n" +
               "                <p class=\"end\">Upswing 2024</p>\n" +
               "            </div>\n" +
               "        </div>\n" +
               "    </body>\n" +
               "\n" +
               "</html>";
    }
}

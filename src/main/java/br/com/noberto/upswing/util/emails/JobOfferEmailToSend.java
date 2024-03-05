package br.com.noberto.upswing.util.emails;

import br.com.noberto.upswing.email.EmailRequest;
import br.com.noberto.upswing.models.Company;
import br.com.noberto.upswing.models.JobOffer;
import br.com.noberto.upswing.repositories.CompanyRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class JobOfferEmailToSend implements IEmailToSendStrategy {
    private final CompanyRepository companyRepository;
    @PersistenceContext
    private final EntityManager entityManager;

    public JobOfferEmailToSend(CompanyRepository companyRepository, EntityManager entityManager) {
        this.companyRepository = companyRepository;
        this.entityManager = entityManager;
    }

    @Override
    public String emailPending(Object object) {
        JobOffer jobOffer = (JobOffer) object;
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
               "                <p class=\"salutation\">Olá " + jobOffer.getCompany().getAccount().getName() + ",</p>\n" +
               "\n" +
               "                <p class=\"text-message\">Gostaríamos de informar que recebemos a sua vaga para " + jobOffer.getPosition() + " e estamos\n" +
               "                revisando as informações fornecidas.</p>\n" +
               "\n" +
               "                <p class=\"text-message\">No momento sua vaga está pendente de aprovação. Estamos trabalhando\n" +
               "                para processar sua solicitação o mais rápido possível.</p>\n" +
               "\n" +
               "                <p class=\"text-message\">Te notificaremos assim que a vaga for aprovada.</p>  \n" +
               "\n" +
               "                <p class=\"text-message\">Agradecemos sua compreensão e estamos à disposição para quaisquer\n" +
               "                dúvidas ou informações adicionais.</p>\n" +
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

    @Override
    public String emailApproved(Object object) {
        JobOffer jobOffer = (JobOffer) object;

        Company company = companyRepository.findById(jobOffer.getCompany().getId())
                        .orElseThrow(() -> new EntityExistsException("Empresa informada é invalida!"));
        entityManager.flush();

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
               "                <p class=\"text-message\">Passando para informar que sua vaga para " + jobOffer.getPosition() + "\n" +
               "                foi aprovada e está agora disponível para visualização na nossa plataforma.</p>\n" +
               "\n" +
               "                <p class=\"text-message\">Sua vaga já foi destinada aos candidatos mais qualificados de\n" +
               "                nosso sistema. Estamos confiantes de que receberá um excelente grupo de candidatos para\n" +
               "                preencher a posição anunciada.</p>\n" +
               "\n" +
               "                <p class=\"text-message\">Se tiver alguma dúvida adicional ou precisar de assistência, não\n" +
               "                hesite em entrar em contato conosco. Estamos aqui para ajudar em qualquer etapa do processo.</p>\n" +
               "\n" +
               "                <p class=\"text-message\">Agradecemos por escolher nossa plataforma para divulgar suas oportunidades\n" +
               "                de emprego e desejamos muito sucesso na busca pelo candidato ideal.</p>\n" +
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
        JobOffer jobOffer = (JobOffer) object;
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
               "                <p class=\"salutation\">Olá " + jobOffer.getCompany().getAccount().getName() + ",</p>\n" +
               "\n" +
               "                <p class=\"text-message\">Passando para informar que sua vaga para " + jobOffer.getPosition() + " não foi aprovada\n" +
               "                para ser divulgada em nosso sistema.</p>\n" +
               "\n" +
               "                <p class=\"text-message\">Após uma análise cuidadosa, identificamos alguns aspectos que não\n" +
               "                estão em conformidade com nossos critérios de qualidade e diretrizes de publicação de vagas.\n" +
               "                Entendemos que pode ser decepcionante receber essa notícia, mas estamos aqui para ajudá-lo a\n" +
               "                entender melhor os motivos dessa decisão.</p>\n" +
               "\n" +
               "                <p class=\"text-message\">Alguns dos possíveis motivos para a reprovação podem incluir:\n" +
               "                    <ul>                        \n" +
               "                        <li>Descrição da vaga incompleta ou pouco clara.</li>\n" +
               "                        <li>Requisitos inconsistentes ou pouco claros.</li>\n" +
               "                        <li>Violação de nossos termos de serviço ou políticas da empresa.</li>\n" +
               "                    </ul>    \n" +
               "                </p>  \n" +
               "\n" +
               "                <p class=\"text-message\">Caso deseje mais detalhes sobre os motivos específicos da\n" +
               "                reprovação ou precise de assistência para fazer as alterações necessárias, por favor,\n" +
               "                entre em contato conosco. Estamos aqui para ajudar e orientar você durante este processo.</p>\n" +
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

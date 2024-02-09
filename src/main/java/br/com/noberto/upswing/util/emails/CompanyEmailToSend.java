package br.com.noberto.upswing.util.emails;

import br.com.noberto.upswing.email.EmailRequest;
import br.com.noberto.upswing.models.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyEmailToSend implements IEmailToSend{
    @Override
    public <T> EmailRequest emailPending(T object) {
        Company company = (Company) object;
        EmailRequest emailRequest = new EmailRequest();

        emailRequest.setEmailTo(company.getAccount().getEmail());
        emailRequest.setSubject("Seu perfil está em analíse");
        emailRequest.setMessage("Olá " + company.getAccount().getName() + ", \n" +
                "Gostariamos de informar que recebemos a sua solicitação de cadastro no nosso sistema e estamos revisando" +
                " as suas informações.\n\n" +
                "No momento seu perfil está pendente de aprovação. Estamos trabalhando para processar sua solicitação o " +
                "mais rápído possível.\n\n" +
                "Entendemos que a agilidade na aprovação é crucial para que você possa começar a usurfruir de todos os beneficios" +
                " da nossa plataforma. Por isso, gostariamos de te pedir um pouco mais de paciência enquanto finalizamos" +
                " essa etapa.\n\n" +
                "Assim que seu perfil for aprovado você receberá outra notificação por email com todas as informações " +
                "necessárias para acessar e utilizar a nossa plataforma.\n\n" +
                "Agradecemos sua compreensão e estamos à disposição para quaisquer dúvidas ou informações adicionais.\n\n" +
                "Atenciosamente,\n" +
                "Upswing.");

        return  emailRequest;
    }

    @Override
    public <T> EmailRequest emailApproved(T object) {
        Company company = (Company) object;
        EmailRequest emailRequest = new EmailRequest();

        emailRequest.setEmailTo(company.getAccount().getEmail());
        emailRequest.setSubject("Seu perfil foi aprovado!");
        emailRequest.setMessage("Olá " + company.getAccount().getName() + ", \n\n" +
                "Passando para te informar que seu perfil foi aprovado com sucesso!\n\n" +
                "Após uma revisão cuidadosa, concluímos que todas as informações fornecidas atendem aos nossos critérios" +
                " de aprovação. Agora, você pode desfrutar de todos os recursos e benefícios disponíveis em nossa plataforma.\n\n" +
                "Seu acesso completo ao sistema está liberado, permitindo que você explore todas as funcionalidades e " +
                "recursos disponíveis. Caso tenha alguma dúvida sobre como começar ou precise de assistência, não hesite " +
                "em entrar em contato conosco. Estamos aqui para ajudar.\n\n" +
                "Agradecemos por escolher fazer parte de nossa comunidade e esperamos que sua experiência em nosso sistema " +
                "seja produtiva e satisfatória.\n\n" +
                "Atenciosamente,\n" +
                "Upswing.");

        return  emailRequest;
    }

    @Override
    public <T> EmailRequest emailNotApproved(T object) {
        Company company = (Company) object;
        EmailRequest emailRequest = new EmailRequest();

        emailRequest.setEmailTo(company.getAccount().getEmail());
        emailRequest.setSubject("Infelizmente, seu perfil não foi aprovado");
        emailRequest.setMessage("Olá " + company.getAccount().getName() + ", \n\n" +
                "Passando para te informar que, após uma análise cuidadosa, seu perfil em nossa plataforma não foi aprovado.\n\n" +
                "Identificamos algumas questões que não estão em conformidade com nossos critérios de qualidade ou políticas " +
                "da empresa. Entendemos que isso pode ser decepcionante, mas estamos aqui para ajudá-lo a entender melhor " +
                "os motivos dessa decisão e ajudá-lo a corrigir quaisquer problemas.\n\n" +
                "Alguns dos motivos comuns para a reprovação do perfil podem incluir:\n" +
                "Informações incompletas ou imprecisas sobre a empresa.\n" +
                "Falta de detalhes sobre os produtos ou serviços oferecidos.\n" +
                "Não atendimento aos requisitos específicos da nossa plataforma.\n\n" +
                "Se você deseja mais informações sobre os motivos específicos da reprovação ou precisa de assistência para " +
                "ajustar seu perfil, não hesite em entrar em contato conosco. Estamos aqui para ajudar e orientar você " +
                "durante este processo.\n\n" +
                "Agradecemos pela sua compreensão e estamos à disposição para qualquer dúvida ou esclarecimento adicional " +
                "que possa surgir.\n\n" +
                "Atenciosamente,\n" +
                "Upswing.");

        return  emailRequest;
    }
}

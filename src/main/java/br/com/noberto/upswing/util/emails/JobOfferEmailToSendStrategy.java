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
public class JobOfferEmailToSendStrategy implements IEmailToSendStrategy {
    private final CompanyRepository companyRepository;
    @PersistenceContext
    private final EntityManager entityManager;

    public JobOfferEmailToSendStrategy(CompanyRepository companyRepository, EntityManager entityManager) {
        this.companyRepository = companyRepository;
        this.entityManager = entityManager;
    }

    @Override
    public <T> EmailRequest emailPending(T object) {
        JobOffer jobOffer = (JobOffer) object;
        EmailRequest emailRequest = new EmailRequest();

        emailRequest.setEmailTo(jobOffer.getCompany().getAccount().getEmail());
        emailRequest.setSubject("Sua Vaga está em analíse");
        emailRequest.setMessage("Olá " + jobOffer.getCompany().getAccount().getName() + ", \n" +
                "Gostariamos de informar que recebemos a sua vaga ofertada para " + jobOffer.getPosition() + " e estamos revisando as suas informações fornecidas.\n\n" +
                "No momento sua vaga está pendente de aprovação. Estamos trabalhando para processar sua solicitação o " +
                "mais rápído possível.\n\n" +
                "Entendemos a agilidade na aprovação é crucial para que você possa preencher essa vaga o quanto antes." +
                " Por isso, gostariamos de te pedir um pouco mais de paciência enquanto finalizamos" +
                " essa etapa.\n\n" +
                "Te notificaremos assim que sua vaga for aprovada.\n\n " +
                "Agradecemos sua compreensão e estamos à disposição para quaisquer dúvidas ou informações adicionais.\n\n" +
                "Atenciosamente,\n" +
                "Upswing.");

        return  emailRequest;
    }

    @Override
    public <T> EmailRequest emailApproved(T object) {
        JobOffer jobOffer = (JobOffer) object;
        EmailRequest emailRequest = new EmailRequest();

        Company company = companyRepository.findById(jobOffer.getCompany().getId())
                        .orElseThrow(() -> new EntityExistsException("Empresa informada é invalida!"));
        entityManager.flush();

        emailRequest.setEmailTo(company.getAccount().getEmail());
        emailRequest.setSubject("Sua Vaga foi aprovada!");
        emailRequest.setMessage("Olá " + jobOffer.getCompany().getAccount().getName() + ", \n\n" +
                "Pasando para informar que sua vaga para " + jobOffer.getPosition() + " foi aprovada e está agora disponível" +
                " para visualização na nossa plataforma.\n\n" +
                "Sua vaga já foi destinada aos candidatos mais qualificados de nosso sistema. Estamos confiantes de que " +
                "receberá um axcelente grupo de candidatos para preencher a posição anunciada.\n\n" +
                "Se tiver alguma dúvida adicional ou precisar de assistência, não hesite em entrar em contato conosco. " +
                "Estamos aqui para ajudar em qualquer etapa do processo.\n\n" +
                "Agradecemos por escolher nossa plataforma para divulgar suas oportunidades de emprego e desejamos muito " +
                "sucesso na busca pelo candidato ideal.\n\n" +
                "Atenciosamente,\n" +
                "Upswing.");

        return  emailRequest;
    }

    @Override
    public <T> EmailRequest emailNotApproved(T object) {
        JobOffer jobOffer = (JobOffer) object;
        EmailRequest emailRequest = new EmailRequest();

        emailRequest.setEmailTo(jobOffer.getCompany().getAccount().getEmail());
        emailRequest.setSubject("Infelizmente, sua vaga não foi aprovada");
        emailRequest.setMessage("Olá " + jobOffer.getCompany().getAccount().getName() + ", \n" +
                "Pasando para informar que sua vaga para " + jobOffer.getPosition() + " não foi aprovada para ser divulgada" +
                " em nosso sistema.\n\n" +
                "Após uma análise cuidadosa, identificamos alguns aspectos que não estão em conformidade com nossos critérios " +
                "de qualidade e diretrizes de publicação de vagas. Entendemos que pode ser decepcionante receber essa notícia, " +
                "mas estamos aqui para ajudá-lo a entender melhor os motivos dessa decisão.\n\n" +
                "Alguns dos possíveis motivos para a reprovação podem incluir:\n" +
                "Descrição da vaga incompleta ou pouco clara.\n" +
                "Requisitos inconsistentes ou pouco claros.\n" +
                "Violação de nossos termos de serviço ou políticas da empresa.\n\n" +
                "Caso deseje mais detalhes sobre os motivos específicos da reprovação ou precise de assistência para fazer " +
                "as alterações necessárias, por favor, entre em contato conosco. Estamos aqui para ajudar e orientar você " +
                "durante este processo.\n\n" +
                "Atenciosamente,\n" +
                "Upswing.");

        return  emailRequest;
    }
}

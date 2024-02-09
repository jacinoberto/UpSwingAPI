package br.com.noberto.upswing.services.mail;

import br.com.noberto.upswing.email.EmailRequest;
import br.com.noberto.upswing.email.EmailSender;
import br.com.noberto.upswing.models.*;
import br.com.noberto.upswing.repositories.*;
import br.com.noberto.upswing.util.filters.FilterStudentsByContractType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final EmailRepository repository;
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public EmailService(EmailRepository repository, StudentRepository studentRepository, JavaMailSender mailSender,
                        CompanyRepository companyRepository) {
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.mailSender = mailSender;
        this.companyRepository = companyRepository;
    }

    public void welcomeEmail(Student studentData){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        Student student = studentRepository.getReferenceById(studentData.getId());
        EmailRequest emailRequest = getWelcomeEmail(student);

        mailMessage.setFrom(emailRequest.getEmailFrom());
        mailMessage.setTo(student.getAccount().getEmail());
        mailMessage.setSubject(emailRequest.getSubject());
        mailMessage.setText(emailRequest.getMessage());

        mailSender.send(mailMessage);
        repository.save(new EmailSender(emailRequest));
    }

    public void emailForJobApplication(JobOffer jobOffer){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        Company company = companyRepository.getReferenceById(jobOffer.getCompany().getId());
        List<Student> students = FilterStudentsByContractType.filterStudentByContractType(jobOffer);

        for (Student studentData : students){
            Student student = studentRepository.getReferenceById(studentData.getId());
            EmailRequest emailRequest = getAppliedVacancyEmail(student, company, jobOffer);

            mailMessage.setFrom(emailRequest.getEmailFrom());
            mailMessage.setTo(student.getAccount().getEmail());
            mailMessage.setSubject(emailRequest.getSubject());
            mailMessage.setText(emailRequest.getMessage());

            mailSender.send(mailMessage);
            repository.save(new EmailSender(emailRequest));
        }
    }

    public void emailForPendingProfile(Company company){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        EmailRequest emailRequest = getEmailForPendingProfile(company);

        mailMessage.setFrom(emailRequest.getEmailFrom());
        mailMessage.setTo(company.getAccount().getEmail());
        mailMessage.setSubject(emailRequest.getSubject());
        mailMessage.setText(emailRequest.getMessage());

        mailSender.send(mailMessage);
        repository.save(new EmailSender(emailRequest));
    }

    public void emailForApprovedProfile(Company company){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        EmailRequest emailRequest = getEmailForApprovedProfile(company);

        mailMessage.setFrom(emailRequest.getEmailFrom());
        mailMessage.setTo(company.getAccount().getEmail());
        mailMessage.setSubject(emailRequest.getSubject());
        mailMessage.setText(emailRequest.getMessage());

        mailSender.send(mailMessage);
        repository.save(new EmailSender(emailRequest));
    }

    public void emailForNotApprovedProfile(Company company){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        EmailRequest emailRequest = getEmailForNotApprovedProfile(company);

        mailMessage.setFrom(emailRequest.getEmailFrom());
        mailMessage.setTo(company.getAccount().getEmail());
        mailMessage.setSubject(emailRequest.getSubject());
        mailMessage.setText(emailRequest.getMessage());

        mailSender.send(mailMessage);
        repository.save(new EmailSender(emailRequest));
    }

    public void emailForPendingVacancy(JobOffer jobOffer){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        Company company = companyRepository.getReferenceById(jobOffer.getCompany().getId());
        EmailRequest emailRequest = getEmailForPendingVacancy(jobOffer);

        mailMessage.setFrom(emailRequest.getEmailFrom());
        mailMessage.setTo(company.getAccount().getEmail());
        mailMessage.setSubject(emailRequest.getSubject());
        mailMessage.setText(emailRequest.getMessage());

        mailSender.send(mailMessage);
        repository.save(new EmailSender(emailRequest));
    }

    public void emailForApprovedVacancy(JobOffer jobOffer){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        Company company = companyRepository.getReferenceById(jobOffer.getCompany().getId());
        EmailRequest emailRequest = getEmailForApprovedVacancy(jobOffer);

        mailMessage.setFrom(emailRequest.getEmailFrom());
        mailMessage.setTo(company.getAccount().getEmail());
        mailMessage.setSubject(emailRequest.getSubject());
        mailMessage.setText(emailRequest.getMessage());

        mailSender.send(mailMessage);
        repository.save(new EmailSender(emailRequest));
    }

    public void emailForNotApprovedVacancy(JobOffer jobOffer){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        Company company = companyRepository.getReferenceById(jobOffer.getCompany().getId());
        EmailRequest emailRequest = getEmailForNotApprovedVacancy(jobOffer);

        mailMessage.setFrom(emailRequest.getEmailFrom());
        mailMessage.setTo(company.getAccount().getEmail());
        mailMessage.setSubject(emailRequest.getSubject());
        mailMessage.setText(emailRequest.getMessage());

        mailSender.send(mailMessage);
        repository.save(new EmailSender(emailRequest));
    }

    private static EmailRequest getWelcomeEmail(Student student) {
        EmailRequest emailRequest = new EmailRequest();

        emailRequest.setEmailTo(student.getAccount().getEmail());
        emailRequest.setSubject("Upswing te dá as boas vindas!");
        emailRequest.setMessage("Olá " + student.getAccount().getName() + ",\n" +
                "Seja bem-vindo(a) ao Upswing.\n\n" +
                "Agora você tem uma ampla gama de recursos e funcionalidades que torna " +
                "a sua entrada no mercado de trabalho em algo mais acertivo. O ambiente é intuitivo e de facil acesso " +
                "para que todas as suas necessidades sejam atendidas. Siga as dicas a seguir para poder acessar o sistema" +
                " e realizar configurações em seu perfil:\n\n" +
                "Login no Sistema: acesse o sistema com o seu e-mail que foi passado a instituição no momento em que realizou" +
                " a sua matricula, e use como senha uma hashtag(#) seguida das duas primeiras letras do seu nome, respeitando" +
                " a primeira letra maiúscula, junto dos três primeiros números do seu CPF.\n\n" +
                "Configurações de Auto Aplicações: no seu primeiro login você será levado a uma pagína onde poderá personalizar" +
                " a as suas preferências para a sua notificação de vagas quando postadas.\n\n" +
                "Personalise Seu Perfil: atualize as suas informações de contato e compartilhe conosco o que faz no momento." +
                " Quanto mais completo o seu perfil melhor será a sua experiência.\n\n" +
                "Explorar o Sistema: você tem passe livre para ver outros cursos ofertados pela sua Intituição de Ensino, " +
                "assim como também pode buscar por vagas manualmente.\n\n" +
                "Se tiver alguma dificuldade pode entrar em contato com nosso suporte ou procurar o setor da sua Instituição" +
                " de Ensino com acesso administrativo ao sistema.\n\n" +
                "Atenciosamento, Upswing.");
        return emailRequest;
    }

    private static EmailRequest getAppliedVacancyEmail(Student student, Company company, JobOffer jobOffer) {
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

    private static EmailRequest getEmailForPendingProfile(Company company){
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

    private static EmailRequest getEmailForApprovedProfile(Company company){
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

    private static EmailRequest getEmailForNotApprovedProfile(Company company){
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

    private static EmailRequest getEmailForPendingVacancy(JobOffer jobOffer){
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

    private static EmailRequest getEmailForApprovedVacancy(JobOffer jobOffer){
        EmailRequest emailRequest = new EmailRequest();

        emailRequest.setEmailTo(jobOffer.getCompany().getAccount().getEmail());
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

    private static EmailRequest getEmailForNotApprovedVacancy(JobOffer jobOffer){
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

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
                <!DOCTYPE html>
                                         <html lang="pt-br">
                                             <head>
                                                 <meta charset="UTF-8">
                                                 <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                                 <title>Exemplo de E-mail</title>
                                                 <script src="https://kit.fontawesome.com/b8ecc655ad.js" crossorigin="anonymous"></script>
                                                 <style> \s
                                                     .content {
                                                         margin: 4rem;
                                                         padding: 2rem 6rem;
                                                         display: flex;
                                                         flex-direction: column;
                                                         align-items: center;
                                                         justify-content: center;
                                                         gap: 4rem;
                                                         background-color: hsl(226, 96%, 98%);
                                                         border-radius: 1rem;
                                                     }
                                                     .teste{
                                                         display: flex;
                                                         flex-direction: column;
                                                         align-items: center;
                                                         justify-content: center;
                                                     }
                                                     .header{
                                                         width: fit-content
                                                     }
                                                     .logo{
                                                         width: 40px;
                                                         height: 40px;
                                                     }
                                                     .wordmark{
                                                         width: 162px;
                                                         height: 37px;
                                                     }
                                                     .message{
                                                         color: hsl(228, 30%, 30%);
                                                         font-weight: medium;
                                                         font-size: 1rem;
                                                         padding: 2rem 0rem;
                                                     }
                                                     .title {
                                                         padding: 0.5rem;
                                                         font-weight: bold
                                                     }
                                                    .message-body{
                                                         padding: 0.5rem
                                                     }
                                                     .redirect{
                                                         display: block;
                                                         width: fit-content;
                                                         margin-top: 1rem;
                                                         font-size: 1rem;
                                                         color: white;
                                                         padding: 0.5rem 1rem;
                                                         background-image: linear-gradient(
                                                         to right,
                                                         hsl(172, 96%, 50%) 0%,
                                                         hsl(214, 96%, 50%) 60%,
                                                         hsl(226, 96%, 60%) 120%
                                                         );
                                                         background-position: right center;
                                                         border: 1px solid #f5f7ff;
                                                         border-radius: 0.2rem;
                                                         cursor: pointer;
                                                     }
                                                     .footer-table{
                                                         width: 100%;
                                                         display: flex;
                                                         flex-direction: column;
                                                         align-items: center;
                                                         justify-content: center;\s
                                                     }
                                                     .footer{
                                                         width: 100%;
                                                         display: flex;
                                                         flex-direction: column;
                                                         align-items: center;
                                                         justify-content: center;
                                                         gap: 1rem;
                                                         padding: 1rem;
                                                         border-top: 1px solid hsl(226, 60%, 90%);
                                                     }
                                                     .social-links{
                                                         display: flex; gap: 0.5rem; color: aqua
                                                     }
                                                     .social-links a{
                                                         width: 40px;
                                                         height: 40px;
                                                         aspect-ratio: 1/1;               \s
                                                         padding: 0.5rem;
                                                         background-color: white;
                                                         border-radius: 50%;
                                                     }
                                                     .end{
                                                         color: hsl(228, 30%, 50%);
                                                         font-weight: medium;
                                                         font-size: 1rem
                                                     }
                                                 </style>
                                             </head>
                                         
                                             <body>\s
                                                 <div class="content">
                                                     <table>
                                                         <tbody>
                                                             <tr>
                                                                 <td>
                                                                     <table class="teste">
                                                                         <tbody>
                                                                             <tr>
                                                                                 <td>
                                                                                     <div class="header">
                                                                                         <img src="https://lh3.googleusercontent.com/pw/ABLVV849WGzD2-EPG1U-pQQvUkbhN7ZTTiN57qu8eqCcRtIOgmsbpYhFKRyFHWuiFaNJy3PKJRTzUGihADenwo3ceg0Hq-EI5fRHe5EXeuy2Zng0oxECJjFk68-6cxJDJgoka6Q0PlwpXLsPNynun4bESqFSkti7iZDPLHJqWx_S_Uvp4H7T-RC8BP5UUWSz3twHjwlxpiBDkSY364LmmN404qqwjBwegAIXL7K8Ec2fIsd77jd-JKnyyd9gGzjJfpjXcRCjZfnmwMkHPDKczkay6c2cpdArsQGQYgEVA7RHsUbfA_2hbD4YTgEcApLCo0Ytp4LYTVMBr-9CNC7Zhu7Zhu8Np_1RyEAOn2qWXjw_qxIjYD-HFohTP901Dw3omqweQGeRm7HqdOfAZ1yXFT_htwLQjZyW2cP7g3H49tJMGFarZVAVUhhO8vgooFh36qRAWkYN7NhbxcOtrRN3KsASnsJnYE9T1pZQssxdHIdwpsAWb7moJXSv3NZ_hahjhJTTfRJC-gUk0UFSNCrHb3wDoWVHs06H7akjB6v7ot3P-bJVv2xjnO0GvmpL_9KsTxv5JXGOHbIyIyaJAhr-sG1BLSQ-bF6L7J7xJrr6c3rcM-I7QuEXgeZ2ZUdZfssG_A4GELg7z-XCr5xJ_j5iS4k_PE_zCs852hTMXXglLV81r6GDfGvleNmS0pcz08P1HmqDzdzGUup099y1cyK8hUcpDPTZXfHFJFQgLn6gNVm5vAaCsV93mUzSb66zDoGw1KGBouX_jaxbmrbkJFeVJ-ZK7p9UNvK_T5r-Q3lgdf_6-uSDEWcnakrgLyWqwJIOF6JXb06QlI8PuycuQwZQJ4TfUgAmf-1tujFu6m5McavYSq8YZxsbc6W-X4YT_x7QZJMIk6-c2PWPGxXLyIWtKmHMqHTxqoAVNhkTIgxGGdJvRvTNQ-oIRp0DURqm1hg684PgOOTorC50VoyOQyCNZT1KYaqRuTvS-bg4n-3UYYNG6Z-tJ46O7stryhrJ3lCwwWeQdBPucT0mPGX5cGRXNr15aBH3SwIecItsD98TFqjDix9yvKTvWIMB1qEQhNzZ7G9K_0hKvKmY70U5UYxQTh4YAb63ldpOa-JwqkfFWRQi8ehMPXaCfb2tC0uSKOnSuSSONmeyYw1PdRMSBOSavgQHcnGhbV5JPhfhCRKSjDBMxz7fiu2nfahZken8wUbgItMK9cH5JwaeGZT7f7uG4d3I=w163-h163-s-no-gm?authuser=0" alt="" class="logo">
                                                                                         <img src="https://lh3.googleusercontent.com/pw/ABLVV87uF9AkwKk-GYb0PBbtx6NKsE8nDZhienj69EEA-UdILGBQpaV2Z6Dx8C0MRf-BPAbjA22tk2t54C3RD8K25_00_Q56AZAejCQz_uSCtDFM3m4giu0oM37RllZPY2REJnthTIi8p_HL6aPkzgpnLMNcb2Ltv4CHupWerGRvIfIdFSrkpCXGZl11mgbqKxz31j-FgHK8x3hFiUsR7wwRQ0BvKMKUBUYR7s1EWgLUEs2CPoQ9JsoFniw1QHwDpJlFUck2BmjIi0M4H6WSf3zfCewb94-tutz6w4dSwfieGF-Y-ODtV_8cCf_1GbZZPjNxyoEUor0kk8HZDvVQo2kE28RQcwyTk0n3D1ISr83w28pKg0tXcry-JZ6L9Ao6WloB-2ol33Ua1BDQ9jprg3aV2EE4cD80cWR-vblIzMmZWxlDUFNyBCutaugQ0KJJHEJrWBNB5Yo6bHZSkPQZnaLdVY52YLzGulmzdONoTtB75XQ8-z1zFrBrN6bFDyk55tSDQXmqPX037UiLCBar25wimK9sozfptBmqtPCMlg2o2_p9M_ViJ8XFAZ508N96zx12g2D3yigX6Z6goDXmP72X5opM3RTRjXW-lLYl3crlA-pHyLDZ0scPPbldDhIOnseGJW3BuhPNn8-BcfBYAjmTfhpGqq4W3UTXyAaaZw1opD5z5TIxe_YqyfnpmGiD5BaslOXDCWovNMAJnydnp1WQD_oAqSXetmVZTNWPCKRSQtYRWauFgnxmrEzOBNAidbU01q2u1RfeswHHKTolXBQdx05kIyfoI5AZFj1yKSs6Nxyt0pM3gKYicM-hX_kgurvPKZcWNq-ou3UmG7sjZGX-T98gxdUiyVR15AEcSqtBwR6j1tmR2wYD2jKDLfdZZsBk21j_5K6lL54O5qcythTK-nMq7EZicIsm_Us6jMzwdW38Lm-NRRq0we_TT75735eyz1NGnGJlBPhqVIWE-YjP81t1gU1Oo5c-8unCi9GqnGlZbIdsT-4L6clW90EUg9KtOIgC8iDLSxsM8b-K7jUeeabyBLBigRBnviAeGACzP7J1whx43-tp_9qJUDA2TkkaexmqMWIn9D2jW6McPDKhvRjGtmQ-3aXsZOZEdEJZjKhSLGGv__MarT8sftvr33WqhMTLNiUumAKRDzTH0eXjZg34xGLqBktvHGpxXf1Q1k2K9CoGmGdREgrHQaaAm3SuTT3A8uOhAtkFSeQJnTvyhg=w725-h163-s-no-gm?authuser=0" alt="" class="wordmark">
                                                                                     </div>
                                                                                 </td>
                                                                             </tr>
                                                                         </tbody>
                                                                     </table>
                                                                     <table class="teste">
                                                                         <tbody>
                                                                             <tr>
                                                                                 <td>
                                                                                     <div class="message">
                                                                                         <p class="title">Olá usuario</p>
                                                                                         <p class="message-body">
                                                                                             Lorem ipsum dolor sit amet consectetur, adipisicing elit. Dolores dolorem
                                                                                             corrupti cupiditate distinctio iusto recusandae, cumque incidunt beatae
                                                                                             sit necessitatibus officia commodi nisi nesciunt quae nobis ab hic nihil
                                                                                             unde! Lorem ipsum dolor sit amet consectetur, adipisicing elit. Dolores
                                                                                             dolorem corrupti cupiditate distinctio iusto recusandae, cumque incidunt
                                                                                             beatae sit necessitatibus officia commodi nisi nesciunt quae nobis ab hic
                                                                                             nihil unde! Lorem ipsum dolor sit amet consectetur, adipisicing elit.
                                                                                             Dolores dolorem corrupti cupiditate distinctio iusto recusandae, cumque
                                                                                             incidunt beatae sit necessitatibus officia commodi nisi nesciunt quae
                                                                                             nobis ab hic nihil unde! Lorem ipsum dolor sit amet consectetur,
                                                                                             adipisicing elit. Dolores dolorem corrupti cupiditate distinctio iusto
                                                                                             recusandae, cumque incidunt beatae sit necessitatibus officia commodi nisi
                                                                                             nesciunt quae nobis ab hic nihil unde!
                                                                                         </p>   \s
                                                                                         <a href="#" class="redirect">Ir para o site</a>
                                                                                     </div>
                                                                                 </td>
                                                                             </tr>
                                                                         </tbody>
                                                                     </table>
                                                                     <table class="footer-table">
                                                                         <tbody>
                                                                             <tr>
                                                                                 <td>
                                                                                     <div class="footer">
                                                                                         <div class="social-links">
                                                                                             <a href="#"><img src="https://lh3.googleusercontent.com/pw/ABLVV84VN42a5FdJyG2FhuLUdre14fLi6PvG319kw0hzeObCE48wydHcmn8S40fm3k2zv8qaQ3wUftUIm1bg5SntY6xWWOzBzXqRVTFhCGHui8RVs8DVkuvglmNVZhHfFawutAiHWuiMvKHag5TknNSQPfH-vo3WUZS9VZBEvmrnlZ2at2qt-x9bTE9Y-UMuYAoGMfL3CxDxJysBNkLfrj2R8fifxZuHkBOzEDyrhFAESb8mOAAFL6--IyKTX9BRZ1Q3f1Yh4SocNx8Fc78rLAOKFtnXOpA8IrxqSH9dBFWB-1dvakzKI3XZMwiDwc3E7_iASdT0gJJi4GhY-du3pwBdOzzyYO1I-QPEk8iPErs8D41pvxqP_zgUf7yBxc21UEhEbtvOvhpGTY70KSHuP0G_jUnbI83ElNcBnIqvrhZbpHMKPr0ysoszzDgq9iy231Ztx-oT1Gb5Xo6bm1jvksjlFC94HjVYxsWW4zOknzzXbDDH5Ad00D-25df8vfPUiz3V6Ol1U6xQ0fDlpMRnlHbH4d1jrQIASsEms-Y12rNsSp9nnttG0slUTWa38k3Ly8LwDH6oETOtv964alBAaeG3Lezk0DTJCv9Cmom1I0UWnlT1EDdgqJz-Ou5KYilacr-QjskzreEH2hPA9Zz2HaHw8qQeEEEJS1WIHF-_wo4PT6HPCcfOVSgmXxkLZHWxmxweDbb00pFegltJfmFNyKsOJQm3Onp2i3Lm4MAOQIoV5ucTw-n72wyVuAqF9sVMF1_p_s8o-xzvC9uRbAbaJne5Hj1V7xMJNAEJCrvSaTNxu3BrOSJ4WIpG3Vo8cKqAkyCzF2noHR6e6TdNxNRrqcXNVGcfE-rJiH5bd9z2vKxoX8qH2XUwtFdWkQpZpwr5BMXwZNiU6rLIXjd-mwaINJma8_8pFQId7hnUBph8LgTEbWEYajQULr7gAf8gnaH_WLJ08ZjT4I35lrBV9DjTDos2R2bC_7FtzvCQfnyS7QH-Jq3Mgik-Y-WYsH0muUuOzGlQi54bAiG6aqmHXlg6FgFfVII_OUPiFbkYgE2uFPULhYm_7eIjsX-vj38vwjxvNtuf6ViiVfG4tOup2uF-ggELxjaejDoscxvjN-UJx1GdUMOqQqN9kGCNeOQ1qAGYY-4OHb_OMfy52WUXjfcH2-LHCB3EIgVET9GORO4iPqWlmTMIqITj-aOODy2fIzHXjXzxyiU3jqK7s57cx71UW4T3=w24-h24-s-no-gm?authuser=0" alt=""></a>
                                                                                             <a href="#"><img src="https://lh3.googleusercontent.com/pw/ABLVV87gAXe9pV4GiuTvvaqaN6SPpzF20AM0xlvCOYb4Gxo6spyPKwl-KpX5NyHrazh-w4j34-q7lzHo_qKySioOSMo1TNH6JBIyUznL9SoQly81l84enj07cfz65SjwuM-HNaSRa5reeLJg1d5Oa3eIMwp1SImHBfIknVPwufQcVVJn-Fn5njP54zCytY0tlJp9-A03BK2UKRKMEAGdEFXZ95lt1Fn5RxgBMiGIlUgQ_gvVVSgquQINBliTs09xcet8eZ5pD6AS1KnI88wZ9GZ0SUIgSHXAC8WW3QigFru8Rt3-mT5gHYnqufCWA855DBG-liLFYRGMXviznIgWu9PfstUr-YbskuEuKTJiKqyNtG6KT3m9vgerae-sreMGCiL3h63e-x79_3unJPIVhG5uQeh0my4IpUyORZIzUstQOT9R1gQZfaB3B9Y8WkQyjmL9luel_huiF_oOIEKhp9HULtidjvNofI-rV1j4dX8ZNbsa_SnQHWLJMGO9sQvToH3m72rpekmXfmcXHXNV-GwbMyTNYzEV4RMQKC38Z0x8zsxwJIRPRt-exa8MEnVj5JI1VjzeMcwJSaeCw6t_xJGly_4kpC49mz6K-cExTxtIDoYDg87F4g1gV_LcIvaD2YSUytFnyp4sVfRW7eZ_cgycYNDFZt-t2HG6FNS2txfke4p-4ryHyre8m21uVR7FM3WdhaRo7_13k6omsIqxmWELeXg5RifFhPbXZVgB_eEj0WcSJuZlhgULw9cHaZjTG3cCh6HJFNHTErHu7OHBHBUtf8LUssZd9t8Ob6uci-FEEnsqDmjRnzmf7mXLJeBr7IugQcAnkdbtIGtMA0wIdjQbe4TmZjKG0SFB6X20x06c6MJvtHqDmcAzPLwXreXJ8FFr50GOilP9HS2UBCE92pLrkVaTg2lRMZeZl0ixXE3NGTNQGvZGPmcHM_g7M0un5kYjcBTQ2eGo_tmEt4cB3EcdgaYnVRo97kbMBMTVmHgrO6YhUjs66GP1YwGm9bhL_n94Z2oitUXnlnfGjugg8xQ6ZV4lBmHVjsJqMIatVEoR6IERLlI7U3nvH0hXBcrnULl4bBv9GuW-_iKvxjWeRF5g79L0_Jjn6y0XWxLeOZPUyAPpVivjaDrBKmOiwNOoMkqH6dYXhkPlpK2xP3m4uOp2O-iaTrNpfhXSKKKxEzx1k88VZpC5YlW304ShxOc64IoVb1NSZRWQQje8DbeXNtrq=w24-h24-s-no-gm?authuser=0" alt=""></a>
                                                                                             <a href="#"><img src="https://lh3.googleusercontent.com/pw/ABLVV86uFCUsWPZVz05nhj7fOeEOuz-u7sIRNKszPgPMqWbM_3v0t6cMHmn_f1HAg08MS9luQP2JI24yEkEm0H9vfJgR9Sr6HnsADIIuEWClbw88gfCZRVKbTHQ6liOfK2q2XErdjW4TwTARjraoyW7bnRB7fUDorCpbnc3ksL1lJSxgaBALvlyx_LEX_60kKiMTyEUeX3RDaxSVLFcTbmAbjaEeE8zf0fnSICS6lbfxakhGj0f2wLed_SJfaU1pbeXJoPJbUXtf4vr0wfni4xsXXHdaKfpO0ovp0Se96UPxqUAfiS9_n32W8TqClUneFu96HyRaAvihJFrNhg7p8gB_R8Z5qctdeRl5VwiUoNIeChZuFrwS2ZsQFv4EcpBiUG8AP_632tGl1ncg-P9cu6wzvjmTAB3WHCJPJygjh6gzAvO5STKySXx3EwEnR0iE2YmQAeXtL74dzmhHAqVSgLWxFGu73UpAFI59aRrfXRrMcrOCtNnW_clRFOUkRfgvU37QYjih9d2vXiOby9xtnMqtJmXtOHlM8X3CgCgQVkYNbFcLrtsX0a2c6vpC8-ydOXcz1GKNtxUiXAgEcDZkg86NaE9XgDVwKHMKjYsY3PamX_HCQG760oNkas_9l0ZIlt3vA-_rSoFWbseA5smgi5xrVMTbtAcaTmn9ZUPagKStZNTb8zTRmICjJsRx3nUZcKwRVWmS6H82-v1qfCunIdKQN_-om51JQR2YUHnwEBrX3In1rO7z_rUU48aXr6F75X4S9Qv8K4DM2UG_ueA947XoT_os1ulSYeqY_Mp631E-4r3XJOq4BLx2RVzcrGkNquPqCEUQDJWboFhsTPWFoOX5wtRWPjkpIGEkT3z_X3P9hXUJaSZljXe5be1uGag8rVP_5MUq2Kt8YMBXC7tz_QvbY726iQT8KPoBAUdO5B7hmYAuPmx6tkD5rUP96LCr15HEqQ0hEmx7CwkqrSScMZ4g2yYdikYGOkwLKMMVt_nBh6Puh0NSrOTPResVTQhA-bS7P5x4FBTC-4QIui6qtcVS1sooogbCoSvR0PfQG7h1gU_EGExc8LJk_KuaxtMKvs8Ze9LR0N7-k_VOMuSAuCLj4gkGsEkBO0j-18YfeMNifRzhmCxCth9dxqN6DSOl3G5kPnS1SL6jOYBC0uruiji6-2Eauh_xq5ylTmpaCEiDqkK4WM4mMigQgLQiXWHcJt2xleYV_VFPDBeFa5MKJMybLQ=w24-h24-s-no-gm?authuser=0" alt=""></a>
                                                                                         </div>
                                                                                         <div class="end">Upswing 2024</div>
                                                                                     </div>
                                                                                 </td>
                                                                             </tr>
                                                                         </tbody>
                                                                     </table>\s
                                                                 </td>
                                                             </tr>
                                                         </tbody>
                                                     </table>
                                                              \s
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

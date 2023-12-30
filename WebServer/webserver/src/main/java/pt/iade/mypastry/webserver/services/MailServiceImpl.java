package pt.iade.mypastry.webserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pt.iade.mypastry.webserver.models.mails.MailDetail;
import pt.iade.mypastry.webserver.results.ConfirmKeyResponse;

import java.util.Random;

@Service
public class MailServiceImpl implements MailService{
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public ConfirmKeyResponse sendMail(MailDetail mailDetail) {

        int key = new Random().nextInt(99999);
        String subject = "Confirme o seu endereço de email";
        String msgBody = "Para confirmar o seu email, introduza, na app MyPastry, o seguinte código:\t";
        msgBody+= String.format("%06d", key);
        msgBody+="\n\n\nO seu código de confirmação expira em 5 minutos.";

        mailDetail.setSubject(subject);
        mailDetail.setMsgBody(msgBody);

        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(mailDetail.getTo());
            mailMessage.setSubject(mailDetail.getSubject());
            mailMessage.setText(mailDetail.getMsgBody());

            mailSender.send(mailMessage);
            return new ConfirmKeyResponse("Confirmation key", null, String.format("%06d", key), true);

        } catch (Exception e){
            return new ConfirmKeyResponse("Confirmation key", null, e.toString(), false);
        }
    }
}

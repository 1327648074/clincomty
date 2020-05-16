package cl.web.community.Service;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    final
    JavaMailSenderImpl mailSender;


    public MailService(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }
    @Value("${spring.mail.username}")
    private String sender;

    @Async
    public void sendEmailVerCode(String receiver, String verCode) throws MailSendException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("验证码");	//设置邮件标题
        message.setText("尊敬的用户,您好:\n"
                + "\n本次请求的邮件验证码为:" + verCode + ",本验证码5分钟内有效，请及时输入。（请勿泄露此验证码）\n"
                + "\n如非本人操作，请忽略该邮件。\n(这是一封自动发送的邮件，请不要直接回复）");	//设置邮件正文
        message.setTo(receiver);	//设置收件人
        message.setFrom(sender);	//设置发件人
        mailSender.send(message);	//发送邮件
    }

}
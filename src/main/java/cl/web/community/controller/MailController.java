package cl.web.community.controller;

import cl.web.community.MyResult;
import cl.web.community.Service.MailService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.SecureRandom;
import java.util.Random;

@Controller
public class MailController {
    final
    MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    private static Random random = new SecureRandom();

    private String getRandomCode(){
        char[] nonceChars = new char[6];
        String base = "0123456789";
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = base.charAt(random.nextInt(base.length()));
        }
        return new String(nonceChars);

    }
    @CrossOrigin
    @PostMapping("/api/mail")
    @ResponseBody
    public MyResult sendMail(@RequestParam(value = "email" )String mail){
        String code = getRandomCode();
        mailService.sendEmailVerCode(mail,code);
        Subject subject = SecurityUtils.getSubject();
        Session session =  subject.getSession(true);
        session.setAttribute(mail,code);
        session.setTimeout(60000);
        return new MyResult(200,"发送成功");
    }



}

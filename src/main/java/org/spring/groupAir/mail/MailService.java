package org.spring.groupAir.mail;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.member.dto.MemberDto;
import org.spring.groupAir.member.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import javax.naming.*;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {

/*

    private final JavaMailSender javaMailSender;
    private static final String senderEmail= "1thsdpdms1@gmail.com";
    private static int number;

    public static void createNumber(){
        number = (int)(Math.random() * (90000)) + 100000;
    }


    public MimeMessage CreateMail(String mail){
        createNumber();

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "귀하의 초기 비밀번호 입니다" + "</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "로그인후 변경하여 사용하시길 바랍니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException | javax.mail.MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }

    public int sendMail(String mail){

        MimeMessage message = CreateMail(mail);

        javaMailSender.send(message);

        return number;
    }

}
*/
private final JavaMailSender javaMailSender;
    private static final String senderEmail = "1thsdpdms1@gmail.com";
    private static int number;


    public static void createNumber() {
        number = (int) (Math.random() * 900000) + 100000;
    }

    public MimeMessage createMail(String mail) {
        createNumber();

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(senderEmail);
            helper.setTo(mail);
            helper.setSubject("(주)groupAir 초기 비빌번호");
            String body = "<h3>귀하의 초기 비밀번호 입니다</h3>"
                + "<h1>" + number + "</h1>"
                + "<h3>로그인 후 변경하여 사용하시길 바랍니다.</h3>";
            helper.setText(body, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (javax.mail.MessagingException e) {
            throw new RuntimeException(e);
        }

        return message;
    }

    public int sendMail(String mail) {
        MimeMessage message = createMail(mail);
        javaMailSender.send(message);
        return number;
    }
}
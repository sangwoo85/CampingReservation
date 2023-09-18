package com.campingreservationbackend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailSendUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(MailSendUtil.class);

    private JavaMailSender javaMailSender;

    @Autowired
    private void setJavaMailSender(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    /**
     * @title 메일 전송
     * @param receiveList : 받은 사람 이메일 주소들
     * @param msg : 메일 내용
     * @param title : 메일 제목
     * */
    public void sendMail(String[] receiveList, String msg, String title)throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        for(String mailAddress : receiveList) {
            message.addRecipients(Message.RecipientType.TO,mailAddress);
        }
        message.setSubject(title,"UTF-8");
        message.setText(msg,"UTF-8");

        javaMailSender.send(message);
    }

}

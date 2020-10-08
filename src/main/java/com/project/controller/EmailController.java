package com.project.controller;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.EmailDto;

@RestController
@RequestMapping(value = "/email")
public class EmailController {
	@PostMapping(value = "/send-email", consumes = "application/json")
	public String sendEmail(@RequestBody EmailDto dto)  {
		return sendMailWithoutAttachement(dto);
	}

	public String sendMailWithoutAttachement(EmailDto dto) {
		try {

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("prashanth.pasham15@gmail.com", "Mouni@2019");
				}
			});
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("prashanth.pasham15@gmail.com", false));

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dto.getEmail()));
			msg.setSubject(dto.getSubject());
			msg.setContent(dto.getBody(), "text/html");
			msg.setSentDate(new Date());
			Transport.send(msg);

		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "success";

	}
}

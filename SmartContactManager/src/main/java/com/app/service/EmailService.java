package com.app.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	public boolean sendEmail(String message, String subject, String to)
	{
		boolean f=false;
		
		
		String host="smtp.gmail.com";
		final String from="contactmanagersmart@gmail.com";
		final String pwd="pnogaqjfjneljdja";
		
		//get the system properties
				Properties properties = System.getProperties();
				System.out.println(properties);
				
				//setting important info to properties object
				
				
				//host
				properties.put("mail.smtp.host", host);
				properties.put("mail.smtp.port", "465");
				properties.put("mail.smtp.ssl.enable", true);
				properties.put("mail.smtp.auth", true);
				
				//step 1. to get the session object
				Session session = Session.getInstance(properties, new Authenticator() {
					
					protected PasswordAuthentication getPasswordAuthentication() {
					
						return new PasswordAuthentication(from , pwd);
					}
				});
				
				session.setDebug(true);
				
				// step 2. compose the message
				
				MimeMessage mimeMessage = new MimeMessage(session);
				
				try {
					
				//from email
				mimeMessage.setFrom(from);
				
				//adding recipient
				
				mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				
				//adding sub to msg
				
				mimeMessage.setSubject(subject);
				
				//adding text to message
				
				mimeMessage.setText(message);
				mimeMessage.setContent(message, "text/html");
				
				//send
				
				
				//step 3. send the msg using transport class
				
				Transport.send(mimeMessage);
				
				System.out.println("##################Sent Successfully#################");
				System.out.println(message);
				f=true;
				
				}catch (Exception e) {
					e.printStackTrace();
				}
				return f;
	}
	

}

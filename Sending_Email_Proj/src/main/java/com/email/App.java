package com.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Sendig Message through Email........" );
        
        String message="this is msg for security check. This is my First Mail through gmail service";
        String subject="CoderArea: Confirmation";
        String from="emailinspringboot@gmail.com"; //emailinspringboot@gmail.com
        String pwd="vgdnowlugqilpapm"; //"vgdnowlugqilpapm"
        String to="rp201291@gmail.com";
        
        sendEmail(message,subject,to,from,pwd);
    }

    //this method is responsible to send email.
	private static void sendEmail(String message, String subject, String to, final String from, final String pwd) {
		
		//variable for gmail host
		String host="smtp.gmail.com";
		
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
			
				return new PasswordAuthentication(from, pwd);
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
		
		//send
		
		
		//step 3. send the msg using transport class
		
		Transport.send(mimeMessage);
		
		System.out.println("##################Sent Successfully#################");
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}




















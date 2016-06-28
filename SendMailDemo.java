package sendEmail;

/**
 * this demo shows how to use JavaMail API to shoot an email.
 *
 */

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class SendMailDemo {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// step 1 initailization of all parameters and JavaMail configurations
		// four parameters are concerned: SMTP server, user, password, and protocol
		System.out.println("Phase 1: ==> prepare for the initlization of parameters...");
		final String hostserver = "smtp.gmail.com";
		final String user		= "your email";   // sender information
		final String password	= "password";   // sender information
		final String protocol	= "smtps";
		
		// JavaMail API configuration needs Property instance, MimeMessage object, Transport object, and a Session object
		Properties props    = new Properties();
		Session session	 	= Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		Transport transport = null;  // since we haven't decide to use which transport protocol
		System.out.println("Phase 1: ==> done!");
		
		// step 2 get Address information
		// we need both sender and recipients address information 
		System.out.println("Phase 2: ==> get Email address information...");
		Address sender 	  = new InternetAddress("sender email", "sender name"); // sender
		Address receiver = new InternetAddress("reciever email", "reciever name");   // receiver
		System.out.println("Phase 2: ==> done!...");

		// step 3 get Email body details and send email
		// we need subject, body, sender and receiver (from and to)
		System.out.println("Phase 3: ==> composing email body and sending email...");
		try {
			// fill in the message information
			message.setSubject("This is a test");
			message.setText("Whatever you feel like writing");
			message.setFrom(sender);
			message.setRecipient(Message.RecipientType.TO, receiver);
			
			// setting Transport details. There are three steps for transport object
			// find correct protocol, connect server, and sendMessage to the recipient
			transport = session.getTransport(protocol); 
			transport.connect(hostserver, user, password);
			transport.sendMessage(message, message.getAllRecipients());
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		System.out.println("Phase 3: ==> email sent!");
	}

}

package sendEmail;

/**
 * this demo is used to show how to receive email with JavaMail API
 * @author siyaofu
 */
import javax.mail.*;

import com.sun.mail.imap.protocol.MessageSet;
import com.sun.mail.pop3.POP3SSLStore;
import java.io.*;
import java.util.*;

public class ReceiveMaildemo {

	public static void main(String[] args) throws IOException {

		// step 1 initlization
		String host 	= "pop.gmail.com";  // if you use other email server remember to change this
		String user 	= "your email name";
		String password = "your email password";
		String protocol = "pop3";
		int port 		= 995;
		
		System.out.println("Trying to connect to a POP server ...");
		try {
			// connect to a POP3 server
	        String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";   
	        // Applies SSL encryption to the communication channel.
	        
	        // create a Properties instance and settle down configuration
	        Properties pop3Props = new Properties();  
	        pop3Props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
	        pop3Props.setProperty("mail.pop3.socketFactory.fallback", "false");
	        pop3Props.setProperty("mail.pop3.port",  "995");
	        pop3Props.setProperty("mail.pop3.socketFactory.port", "995");
	        
	        // Creates a URLName object from the specified protocol, host, port number, file, username, and password.
	        URLName url = new URLName(protocol, host, port, "", user, password);
	        Session session = Session.getInstance(pop3Props, null);
	        Store store = new POP3SSLStore(session, url);
	        store.connect();
	        System.out.println("Connected!");
	        
	        
			// open a INBOX folder
	        System.out.println("Open INBOX now...");
			Folder inbox = store.getFolder("INBOX");
			if (inbox == null) {
				System.out.println("No inbox!");
				System.exit(0);
			}
			// if it opens
			inbox.open(Folder.READ_ONLY);
			
			// get message from the POP3 server
			Message[] message = inbox.getMessages();
			System.out.println("Total messages = " + inbox.getMessageCount());
            System.out.println("New messages = " + inbox.getNewMessageCount());
            System.out.println("-------------------------------");
            
            // read message
			for (int i= 0; i < message.length; i++) {
				System.out.println("-------- Message" + (i+1) + "-----------");
				message[i].writeTo(System.out);
			}
			
			// close connection but don't remove the message
			inbox.close(false);
			store.close();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

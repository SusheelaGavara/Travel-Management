package proj.main.user;

import java.util.Date;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import com.sun.mail.smtp.*;




public class Email {

    public void emailSend(){
    	try{
        Properties props = System.getProperties();
        props.put("mail.smtps.host","smtp.gmail.com");
        props.put("mail.smtps.auth","true");
        Session session = Session.getInstance(props, null);
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("hgvcjghv@gmail.com"));;
        msg.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse("tov.are.jacobsen@iss.no", false));
        msg.setSubject("SUBJECT "+System.currentTimeMillis());
        msg.setText("TICKET BOOKED");
        msg.setHeader("Acknowledgement", "Tov Are's program");
        msg.setSentDate(new Date());
        SMTPTransport t =
            (SMTPTransport)session.getTransport("smtps");
        t.connect("smtp.gmail.com", "yfiugkjhbkj@gmail.com", "passwrd");
        t.sendMessage(msg, msg.getAllRecipients());
        System.out.println("Response: " + t.getLastServerResponse());
        t.close();
    
    	}catch(Exception e){System.out.println(e);}
    }
}

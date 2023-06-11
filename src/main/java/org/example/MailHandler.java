package org.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static org.example.MailMetaData.*;

public class MailHandler {
    void  sendMail()
    {
        Properties sysProperties = System.getProperties(); // this give me a hash-table
        System.out.println(sysProperties);

        sysProperties.put("mail.smtp.host",MailMetaData.HostServer);
        sysProperties.put("mail.smtp.port",MailMetaData.port);
        sysProperties.put(MailMetaData.sslProperty,"true");
        sysProperties.put(MailMetaData.authPerms,"true");


        //create a session using sender email and password
        Authenticator mailAuthenticator = new CustomizedMailAuthentication();
        Session mailSession = Session.getInstance(sysProperties,mailAuthenticator);

        //mime message build
        MimeMessage mailMessage = new MimeMessage(mailSession);

        try {
            mailMessage.setFrom(MailMetaData.myUserMail);
            mailMessage.setSubject("This is my java code testing");
            mailMessage.setText("Hey this is John who is trying to send mail using java");

            //Set the Receiver
            Address receiverEmail = new InternetAddress(MailMetaData.ReceiverMail);
            mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(MailMetaData.ReceiverMail));

            Transport.send(mailMessage);
        }
        catch (Exception mailException){
            System.out.println(mailException.toString());
        }
    }
}
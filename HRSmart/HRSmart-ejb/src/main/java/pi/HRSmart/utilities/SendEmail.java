package pi.HRSmart.utilities;

/**
 * Created by hadhe on 10/31/2016.
 */
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail {


    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    public static boolean SendEmail(String to, String subject, String msg)

    {

        final String username = "hadhemilaouini@gmail.com";

        final String password = "laouinihassene";

        boolean flag = false;

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");

        props.put("mail.smtp.auth", "true");

        props.put("mail.debug", "true");

        props.put("mail.smtp.port", "587");

        props.put("mail.smtp.socketFactory.port", "587");

        props.put("mail.smtp.starttls.enable", "true");

        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);

        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getInstance(props, new javax.mail.Authenticator()

        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("hadhemilaouini@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            message.setSubject(subject);
            message.setContent(msg, "text/html; charset=utf-8");

            Transport.send(message);
            flag = true;
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }
}

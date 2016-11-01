package pi.HRSmart.utilities;

/**
 * Created by hadhe on 10/31/2016.
 */
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendWelcomeMail {


    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    public static boolean SendEmail(String to, String subject, String msg)

    {

        final String username = "hadhemilaouini@gmail.com";

        final String password = "laouinihassene";

        boolean flag = false;
        String content;

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
            content = " <!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                    " \n" +
                    "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                    "<head>\n" +
                    "    <title>HRSmart</title>\n" +
                    "    <style media=\"screen\" type=\"text/css\">\n" +
                    "\n" +
                    " /* Global Styles */\n" +
                    " \n" +
                    "*{\n" +
                    "    padding: 0; /* Reset all padding to 0 */\n" +
                    "    margin: 0; /* Reset all margin to 0 */\n" +
                    "}\n" +
                    " \n" +
                    "body{\n" +
                    "    background: #F9F9F9; /* Set HTML background color */\n" +
                    "    font: 14px \"Lucida Grande\";  /* Set global font size & family */\n" +
                    "    color: #464646; /* Set global text color */\n" +
                    "}\n" +
                    " \n" +
                    "p{\n" +
                    "    margin: 10px 0px 10px 0px; /* Add some padding to the top and bottom of the <p> tags */\n" +
                    "}\n" +
                    " \n" +
                    "/* Header */\n" +
                    " \n" +
                    "#header{\n" +
                    "    height: 45px; /* Set header height */\n" +
                    "    background: #464646; /* Set header background color */\n" +
                    "}\n" +
                    " \n" +
                    "#header h3{\n" +
                    "    color: #FFFFF3; /* Set header heading(top left title ) color */\n" +
                    "    padding: 10px; /* Set padding, to center it within the header */\n" +
                    "    font-weight: normal; /* Set font weight to normal, default it was set to bold */\n" +
                    "}\n" +
                    " \n" +
                    "/* Wrap */\n" +
                    " \n" +
                    "#wrap{\n" +
                    "    background: #FFFFFF; /* Set content background to white */\n" +
                    "    width: 615px; /* Set the width of our content area */\n" +
                    "    margin: 0 auto; /* Center our content in our browser */\n" +
                    "    margin-top: 50px; /* Margin top to make some space between the header and the content */\n" +
                    "    padding: 10px; /* Padding to make some more space for our text */\n" +
                    "    border: 1px solid #DFDFDF; /* Small border for the finishing touch */\n" +
                    "    text-align: center; /* Center our content text */\n" +
                    "}\n" +
                    " \n" +
                    "#wrap h3{\n" +
                    "    font: italic 22px Georgia; /* Set font for our heading 2 that will be displayed in our wrap */\n" +
                    "}\n" +
                    " \n" +
                    "/* Form & Input field styles */\n" +
                    " \n" +
                    "form{\n" +
                    "    margin-top: 10px; /* Make some more distance away from the description text */\n" +
                    "}\n" +
                    " \n" +
                    "form .submit_button{\n" +
                    "    background: #F9F9F9; /* Set button background */\n" +
                    "    border: 1px solid #DFDFDF; /* Small border around our submit button */\n" +
                    "    padding: 8px; /* Add some more space around our button text */\n" +
                    "}\n" +
                    " \n" +
                    "input{\n" +
                    "    font: normal 16px Georgia; /* Set font for our input fields */\n" +
                    "    border: 1px solid #DFDFDF; /* Small border around our input field */\n" +
                    "    padding: 8px; /* Add some more space around our text */\n" +
                    "} \n" +
                    "\n" +
                    "</style> \n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <!-- start header div -->\n" +
                    "    <div id=\"header\">\n" +
                    "        <h3>NETTUTS > Sign up</h3>\n" +
                    "    </div>\n" +
                    "    <!-- end header div -->  \n" +
                    "     \n" +
                    "    <!-- start wrap div -->  \n" +
                    "    <div id=\"wrap\">\n" +
                    "         \n" +
                    "        <!-- start php code -->\n" +
                    "         \n" +
                    "        <!-- stop php code -->\n" +
                    "     \n" +
                    "        <!-- title and description -->   \n" +
                    "        <h3>Welcome Email : </h3>\n" +
                    "        <p>"+msg+"</p>\n" +
                    "         \n" +
                    "    </div>\n" +
                    "    <!-- end wrap div -->\n" +
                    "</body>\n" +
                    "</html>";
            message.setContent(content, "text/html; charset=utf-8");

            Transport.send(message);
            flag = true;
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }
}

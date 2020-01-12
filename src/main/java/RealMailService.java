import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class RealMailService {


    public void sendMail(String userName, String password, String senderEmailAddress, String msg, String subject) {

        String recipiantEmailAddress = null;

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.ionos.de");
        prop.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });

        session.setDebug(true);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmailAddress));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(recipiantEmailAddress));

            message.setSubject(subject);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Mail für " + recipiantEmailAddress + " erfolgreich gesendet!");

        } catch (MessagingException e) {

            System.out.println("Mail für " + recipiantEmailAddress + " kaputt!");

            e.printStackTrace();
        }
    }
}

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.*;
import java.util.ArrayList;
import java.util.List;

public class TestMailService {

    List<EMail> sentEMails = new ArrayList<>();

    public void sendMail(Pairing pairing) {
        createNewEMail(pairing);
    }

    public EMail createNewEMail(Pairing pairing) {
        EMail eMail = new EMail(pairing.getDonee().getEmailAdress(), pairing.getDonee().getName(), pairing.getGiftee().getName());
        sentEMails.add(eMail);
        return eMail;
    }

    public int getNumberOfSentEMails() {
        return sentEMails.size();
    }

    public List<EMail> getSentEmails() {
        return sentEMails;
    }
}



import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MailServiceTest {

    @Test
    public void SecretSantaRound_can_be_configured_with_a_mailservice() {
        TestMailService testMailService = new TestMailService();
        SecretSantaRound secretSantaRound = new SecretSantaRound(testMailService);

        List<Pairing> pairings = new ArrayList<>();
        pairings.add(new Pairing(
                new Participant("Steve", "@steve"), new Participant("Karl", "@steve")));

        secretSantaRound.sendMailToDonors(pairings);
        assertEquals(testMailService.getNumberOfSentEMails(), 1);

    }

    @Test
    public void An_Email_is_send_to_the_correct_Mailaddress() {
        TestMailService testMailService = new TestMailService();
        SecretSantaRound secretSantaRound = new SecretSantaRound(testMailService);
        List<Pairing> pairings = new ArrayList<>();
        pairings.add(new Pairing(
                new Participant("Steve", "@steve"), new Participant("Karl", "@karl")));

        secretSantaRound.sendMailToDonors(pairings);

        assertTrue(testMailService.sentEMails.get(testMailService.getSentEmails().size()-1).geteMailadress().equals("@steve"));
    }

    @Test
    public void Multiple_emails_are_send_for_each_donor() {
        TestMailService testMailService = new TestMailService();
        SecretSantaRound secretSantaRound = new SecretSantaRound(testMailService);
        List<Pairing> pairings = new ArrayList<>();
        pairings.add(
                new Pairing(new Participant("Steve", "@steve"),
                        new Participant("Karl", "@karl"))
        );
        pairings.add(
                new Pairing(new Participant("Alice", "@alice"),
                        new Participant("Bob", "@Bob"))
        );

        secretSantaRound.sendMailToDonors(pairings);

        List<EMail> sentMails = testMailService.getSentEmails();

        assertEquals(testMailService.getNumberOfSentEMails(), 2);
        assert(sentMails.stream().anyMatch(eMail -> eMail.getDonorName().equals("Steve")));
        assert(sentMails.stream().anyMatch(eMail -> eMail.getDonorName().equals("Alice")));
    }

    @Test
    public void An_Email_contains_the_right_giftee() {
        TestMailService testMailService = new TestMailService();
        List<Pairing> pairings = new ArrayList<>();
        pairings.add(new Pairing(
                new Participant("Steve", "@steve"), new Participant("Karl", "@karl")));

        pairings.add(new Pairing(
                new Participant("Karl", "@karl"), new Participant("Steve", "@steve")));

        testMailService.createNewEMail(pairings.get(0));
        assertTrue(testMailService.getSentEmails().stream().anyMatch(eMail -> eMail.getGifteeName().equals("Karl") && eMail.getDonorName().equals("Steve")));
        assertFalse(testMailService.getSentEmails().stream().anyMatch(eMail -> eMail.getGifteeName().equals("Steve") && eMail.getDonorName().equals("Karl")));

        testMailService.createNewEMail(pairings.get(1));
        assertTrue(testMailService.getSentEmails().stream().anyMatch(eMail -> eMail.getGifteeName().equals("Steve") && eMail.getDonorName().equals("Karl")));
    }
}
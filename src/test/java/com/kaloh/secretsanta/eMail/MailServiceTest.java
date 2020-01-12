package com.kaloh.secretsanta.eMail;

import com.kaloh.secretsanta.domain.EMail;
import com.kaloh.secretsanta.domain.Pairing;
import com.kaloh.secretsanta.domain.Participant;
import com.kaloh.secretsanta.domain.SecretSantaRound;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MailServiceTest {

    @Test
    public void Multiple_emails_are_send_for_each_donor() {
        TestMailService testMailService = new TestMailService();
        SecretSantaRound secretSantaRound = new SecretSantaRound("2020",testMailService);
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

        testMailService.createNewEMail(pairings.get(0),"2020");
        assertTrue(testMailService.getSentEmails().stream().anyMatch(eMail -> eMail.getGifteeName().equals("Karl") && eMail.getDonorName().equals("Steve")));
        assertFalse(testMailService.getSentEmails().stream().anyMatch(eMail -> eMail.getGifteeName().equals("Steve") && eMail.getDonorName().equals("Karl")));

        testMailService.createNewEMail(pairings.get(1),"2020");
        assertTrue(testMailService.getSentEmails().stream().anyMatch(eMail -> eMail.getGifteeName().equals("Steve") && eMail.getDonorName().equals("Karl")));
    }

}

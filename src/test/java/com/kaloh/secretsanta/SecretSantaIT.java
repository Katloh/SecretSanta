package com.kaloh.secretsanta;

import com.kaloh.secretsanta.domain.Pairing;
import com.kaloh.secretsanta.domain.Participant;
import com.kaloh.secretsanta.domain.SecretSantaRound;
import com.kaloh.secretsanta.exception.DuplicateParticipantException;
import com.kaloh.secretsanta.eMail.TestMailService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SecretSantaIT {

    @Test
    public void a_SecretSantaRound_can_be_created_with_given_Year() {
        //given a Testmailservice and a SecretSantaRound
        TestMailService testMailService = new TestMailService();
        List<Pairing> pairings = new ArrayList<>();
        pairings.add(new Pairing(
                new Participant("Steve", "@steve"), new Participant("Karl", "@steve")));

        SecretSantaRound secretSantaRound = new SecretSantaRound("2020", testMailService);
        secretSantaRound.sendMailToDonors(pairings);
        assertTrue(testMailService.getSentEmails().get(0).getSubjectText().contains("2020"));
    }

    @Test(expected = DuplicateParticipantException.class)
    public void throws_exception_if_participant_is_added_twice() throws Exception {
        //given a Testmailservice and a SecretSantaRound
        TestMailService testMailService = new TestMailService();
        SecretSantaRound secretSantaRound = new SecretSantaRound("2020", testMailService);

        secretSantaRound.addParticipantToSecretSantaRound(new Participant("Karl", "@karl"));
        secretSantaRound.addParticipantToSecretSantaRound(new Participant("Karl", "@karl"));
    }

    @Test
    public void a_SecretSantaRound_can_be_run(){
        //given a Testmailservice and a SecretSantaRound
        TestMailService testMailService = new TestMailService();
        ArrayList<Participant> participants = new ArrayList<>();

        Participant participantOne = new Participant("Karl","@karl");
        Participant participantTwo = new Participant("Steve","@steve");
        Participant participantThree = new Participant("Alice","@alice");

        participants.add(participantOne);
        participants.add(participantTwo);
        participants.add(participantThree);

        SecretSantaRound secretSantaRound = new SecretSantaRound("2020", testMailService);
        secretSantaRound.runSecretSantaRound(participants);
        assertTrue(testMailService.getSentEmails().size() == 3);
    }
}

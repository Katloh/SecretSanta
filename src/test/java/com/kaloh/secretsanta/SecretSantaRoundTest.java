package com.kaloh.secretsanta;

import com.kaloh.secretsanta.domain.Pairing;
import com.kaloh.secretsanta.domain.Participant;
import com.kaloh.secretsanta.domain.SecretSantaRound;
import com.kaloh.secretsanta.exception.DuplicateParticipantException;
import com.kaloh.secretsanta.eMail.TestMailService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SecretSantaRoundTest {


    @Test
    public void One_Participant_Can_Be_Added_To_The_SecretSantaRound() throws DuplicateParticipantException {
        SecretSantaRound secretSantaRound = new SecretSantaRound();
        secretSantaRound.addParticipantToSecretSantaRound(new Participant("Steve", "@mail.com"));
        assertEquals(secretSantaRound.getListOfParticipants().get(0).getName(), "Steve");
        assertEquals(secretSantaRound.getListOfParticipants().get(0).getEmailAdress(), "@mail.com");
    }

    @Test
    public void several_Participants_Can_Be_Added_To_One_SecretSantaRound() throws DuplicateParticipantException {
        SecretSantaRound secretSantaRound = new SecretSantaRound();
        Participant one = new Participant("Steve", "@steve");
        Participant two = new Participant("Karl", "@Karl");
        Participant three = new Participant("Luisa", "@Luisa");

        secretSantaRound.addParticipantToSecretSantaRound(two);
        secretSantaRound.addParticipantToSecretSantaRound(one);

        assertTrue(secretSantaRound.hasParticipant(two));
        assertTrue(secretSantaRound.hasParticipant(one));
        assertFalse(secretSantaRound.hasParticipant(three));
    }

    @Test
    public void all_Participants_can_be_paired() throws DuplicateParticipantException {
        SecretSantaRound secretSantaRound = new SecretSantaRound();
        Participant one = new Participant("Steve", "@steve");
        Participant two = new Participant("Karl", "@Karl");

        secretSantaRound.addParticipantToSecretSantaRound(one);
        secretSantaRound.addParticipantToSecretSantaRound(two);

        ArrayList<Pairing> listOfNewPairings = secretSantaRound.createPairings();

        assertEquals(listOfNewPairings.get(0).getDonor().getName(), "Steve");
        assertEquals(listOfNewPairings.get(1).getDonor().getName(), "Karl");
        assertEquals(listOfNewPairings.get(0).getGiftee().getName(), "Karl");
        assertEquals(listOfNewPairings.get(1).getGiftee().getName(), "Steve");

        assertNotEquals(listOfNewPairings.get(0).getDonor(), "Karl");
        assertNotEquals(listOfNewPairings.get(1).getDonor(), "Steve");
    }

    @Test
    public void For_Each_Pairing_Donor_And_Giftee_are_not_the_same_Participant() throws DuplicateParticipantException {
        SecretSantaRound secretSantaRound = new SecretSantaRound();
        Participant one = new Participant("Steve", "@steve");
        Participant two = new Participant("Karl", "@Karl");

        secretSantaRound.addParticipantToSecretSantaRound(one);
        secretSantaRound.addParticipantToSecretSantaRound(two);

        ArrayList<Pairing> listOfNewPairings = secretSantaRound.createPairings();

        assertEquals(listOfNewPairings.get(0).getDonor(), listOfNewPairings.get(1).getGiftee());
        assertEquals(listOfNewPairings.get(1).getDonor(), listOfNewPairings.get(0).getGiftee());
        assertNotEquals(listOfNewPairings.get(0).getDonor(), listOfNewPairings.get(0).getGiftee());
        assertNotEquals(listOfNewPairings.get(1).getDonor(), listOfNewPairings.get(1).getGiftee());
    }

    @Test
    public void the_result_of_New_Pairings_does_not_equals_the_result_of_the_previous_Pairings() {
        SecretSantaRound secretSantaRound = new SecretSantaRound();

        Participant participantOne = new Participant("Steve", "foo@bar.com");
        Participant participantTwo = new Participant("Alice", "foo@bar.com");
        Participant participantThree = new Participant("Magda", "foo@bar.com");

        ArrayList<Pairing> newSecretSantaRound = new ArrayList<>();

        newSecretSantaRound.add(new Pairing(
                participantOne, participantTwo)
        );

        newSecretSantaRound.add(new Pairing(
                participantTwo, participantThree)
        );

        newSecretSantaRound.add(new Pairing(
                participantThree, participantOne)
        );

        ArrayList<Pairing> previousSecretSantaRound = new ArrayList<>();

        previousSecretSantaRound.add(new Pairing(
                participantTwo, participantOne)
        );

        previousSecretSantaRound.add(new Pairing(
                participantThree, participantTwo)
        );

        previousSecretSantaRound.add(new Pairing(
                participantThree, participantOne)
        );

        assertTrue(secretSantaRound
                .haveNewPairingsMatchedOldPairings(newSecretSantaRound, newSecretSantaRound));

        assertFalse(secretSantaRound
                .haveNewPairingsMatchedOldPairings(newSecretSantaRound, previousSecretSantaRound));
    }
}

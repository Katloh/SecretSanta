import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SecretSantaRoundTest {


    @Test
    public void One_Participant_Can_Be_Added_To_The_SecretSantaRound() {
        SecretSantaRound secretSantaRound = new SecretSantaRound();
        secretSantaRound.addParticipantToSecretSantaRound(new Participant("Steve", "@mail.com"));
        assertEquals(secretSantaRound.getListOfParticipants().get(0).getName(), "Steve");
        assertEquals(secretSantaRound.getListOfParticipants().get(0).getEmailAdress(), "@mail.com");
    }

    @Test
    public void several_Participants_Can_Be_Added_To_One_SecretSantaRound() {
        SecretSantaRound secretSantaRound = new SecretSantaRound();
        Participant one = new Participant("Steve", "@steve");
        Participant two = new Participant("Karl", "@Karl");
        Participant three = new Participant("Luisa", "@Luisa");

        secretSantaRound.addParticipantToSecretSantaRound(two);
        secretSantaRound.addParticipantToSecretSantaRound(one);

        assertTrue(secretSantaRound.GivenParticipantIsPartOfTheSecretSantaRound(two));
        assertTrue(secretSantaRound.GivenParticipantIsPartOfTheSecretSantaRound(one));
        assertFalse(secretSantaRound.GivenParticipantIsPartOfTheSecretSantaRound(three));
    }

    @Test
    public void Participants_Of_One_SecretSantaRound_CanBeShuffled() {
        SecretSantaRound secretSantaRound = new SecretSantaRound();
        Participant one = new Participant("Steve", "@steve");
        Participant two = new Participant("Karl", "@Karl");
        secretSantaRound.addParticipantToSecretSantaRound(two);
        secretSantaRound.addParticipantToSecretSantaRound(one);

        assertEquals(secretSantaRound, secretSantaRound);
        assertNotEquals(secretSantaRound, secretSantaRound.shuffle());
    }

    @Test
    public void Participants_Can_Be_Paired_And_The_Pairing_Can_Be_Added_To_A_PairingList() {
        SecretSantaRound secretSantaRound = new SecretSantaRound();
        Participant one = new Participant("Steve", "@steve");
        Participant two = new Participant("Karl", "@Karl");

        secretSantaRound.addParticipantToSecretSantaRound(one);
        secretSantaRound.addParticipantToSecretSantaRound(two);

        ArrayList<Pairing> listOfNewPairings = secretSantaRound.createPairings();

        assertEquals(listOfNewPairings.get(0).getDonee().getName(), "Steve");
        assertEquals(listOfNewPairings.get(1).getDonee().getName(), "Karl");
        assertEquals(listOfNewPairings.get(0).getGiftee().getName(), "Karl");
        assertEquals(listOfNewPairings.get(1).getGiftee().getName(), "Steve");

        assertNotEquals(listOfNewPairings.get(0).getDonee(), "Karl");
        assertNotEquals(listOfNewPairings.get(1).getDonee(), "Steve");
    }


    @Test
    public void For_Each_Pairing_Donor_And_Giftee_are_not_the_same_Participant(){
        SecretSantaRound secretSantaRound = new SecretSantaRound();
        Participant one = new Participant("Steve", "@steve");
        Participant two = new Participant("Karl", "@Karl");

        secretSantaRound.addParticipantToSecretSantaRound(two);
        secretSantaRound.addParticipantToSecretSantaRound(one);

        ArrayList<Pairing> listOfNewPairings = secretSantaRound.createPairings();

        assertEquals(listOfNewPairings.get(0).getDonee(), listOfNewPairings.get(1).getGiftee());
        assertEquals(listOfNewPairings.get(1).getDonee(), listOfNewPairings.get(0).getGiftee());
        assertNotEquals(listOfNewPairings.get(0).getDonee(), listOfNewPairings.get(0).getGiftee());
        assertNotEquals(listOfNewPairings.get(1).getDonee(), listOfNewPairings.get(1).getGiftee());
    }

}

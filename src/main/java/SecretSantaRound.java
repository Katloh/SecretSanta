import java.util.ArrayList;
import java.util.Collections;

public class SecretSantaRound {

    private ArrayList<Participant> listOfParticipants;
    private ArrayList<Pairing> listOfPairings;

    public SecretSantaRound() {
        this.listOfParticipants = new ArrayList<>();
        this.listOfPairings = new ArrayList<>();
    }

    public ArrayList<Participant> getListOfParticipants() {
        return listOfParticipants;
    }

    public void addParticipantToSecretSantaRound(Participant participant) {
        listOfParticipants.add(participant);
    }

    public boolean GivenParticipantIsPartOfTheSecretSantaRound(Participant one) {
        return listOfParticipants
                .stream()
                .filter(Participant -> Participant.getName().equals(one.getName()) &&
                        Participant.getEmailAdress().equals(one.getEmailAdress())).count() == 1;
    }

    public ArrayList<Participant> shuffle() {
        Collections.shuffle(listOfParticipants);
        return listOfParticipants;
    }

    public ArrayList<Pairing> createPairings() {

        for (int i = 0; i < listOfParticipants.size(); i++) {
            Pairing pairing;

            if (i == listOfParticipants.size() - 1) {
                pairing = new Pairing(listOfParticipants.get(i), listOfParticipants.get(0));
            } else {
                pairing = new Pairing(listOfParticipants.get(i), listOfParticipants.get(i + 1));
            }
            listOfPairings.add(pairing);
        }
        return listOfPairings;
    }
}



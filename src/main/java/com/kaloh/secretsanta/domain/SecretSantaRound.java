package com.kaloh.secretsanta.domain;

import com.kaloh.secretsanta.exception.DuplicateParticipantException;
import com.kaloh.secretsanta.eMail.TestMailService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

public class SecretSantaRound {

    private ArrayList<Participant> listOfParticipants;
    private ArrayList<Pairing> listOfPairings;
    private TestMailService mailService;
    private String year;

    public SecretSantaRound(String year, TestMailService mailService) {
        this.listOfParticipants = new ArrayList<>();
        this.listOfPairings = new ArrayList<>();
        this.mailService = mailService;
        this.year = year;
    }

    public SecretSantaRound() {
        this.listOfParticipants = new ArrayList<>();
        this.listOfPairings = new ArrayList<>();
        this.mailService = new TestMailService();
    }

    public void runSecretSantaRound(List<Participant> participants) {

        ArrayList<Pairing> oldPairings = new ArrayList<>();

        participants.forEach(participant -> {
            try {
                addParticipantToSecretSantaRound(participant);
            } catch (DuplicateParticipantException e) {
                e.printStackTrace();
            }
        });
        shuffle();
        do{
            createPairings();
        } while(haveNewPairingsMatchedOldPairings(listOfPairings, oldPairings));
        sendMailToDonors(listOfPairings);
    }

    public ArrayList<Participant> getListOfParticipants() {
        return listOfParticipants;
    }

    public void addParticipantToSecretSantaRound(Participant newParticipant) throws DuplicateParticipantException {

        boolean noMatch = listOfParticipants
                    .stream()
                    .filter(participant ->
                            participant.getName().equals(newParticipant.getName()) &&
                                    participant.getEmailAdress().equals(newParticipant.getEmailAdress()))
                    .count() == 0;

        if(noMatch == true) {
            listOfParticipants.add(newParticipant);
        } else {
            throw new DuplicateParticipantException("Der Teilnehmer " + newParticipant.getName() + "steht mehrmals in der List");
        }
    }


    public boolean givenParticipantIsPartOfTheSecretSantaRound(Participant one) {
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

    public boolean haveNewPairingsMatchedOldPairings(ArrayList<Pairing> newPairings, ArrayList<Pairing> oldPairings) {

        List<Boolean> matchList = newPairings.stream()
                .map(newPairing -> oldPairings
                        .stream()
                        .anyMatch(previousPairing -> previousPairing.equals(newPairing)))
                .collect(Collectors.toList());

        for (boolean match : matchList) {
            if (match == true)
                return true;
        }
        return false;
    }

    public void sendMailToDonors(List<Pairing> pairings) {

        pairings.forEach(pairing -> {
            mailService.sendMail(pairing, year);
        });
    }

}



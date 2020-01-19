package com.kaloh.secretsanta.domain;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SecretSantaRoundLogger {

    public void documentSecretSantaRound(ArrayList<Pairing> secretSantaRound, String year, BufferedWriter bufferedWriter) {

        String pairinglog = secretSantaRound.stream()
                .map(pairing -> pairing.getDonee().hashCode() + " : " + pairing.getGiftee().hashCode() + "\n")
                .reduce("", this::reduceToString);
        try {
            bufferedWriter.write(pairinglog);
        } catch (
                IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String reduceToString(String currentString, String accumulator) {
        accumulator = accumulator + currentString;
        return accumulator;
    }
}

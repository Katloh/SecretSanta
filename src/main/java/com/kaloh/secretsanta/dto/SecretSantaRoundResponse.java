package com.kaloh.secretsanta.dto;

public class SecretSantaRoundResponse {

    private int numberOfSentEMails;

    public SecretSantaRoundResponse(int numberOfSentEMails){
        this.numberOfSentEMails = numberOfSentEMails;
    }

    public SecretSantaRoundResponse(){
    }

    public int getNumberOfSentEMails() {
        return numberOfSentEMails;
    }

    public void setNumberOfSentEMails(int numberOfSentEMails) {
        this.numberOfSentEMails = numberOfSentEMails;
    }
}

package com.kaloh.secretsanta.domain;

public class Result {

    private int numberOfSentEMails;

    public Result(int numberOfSentEMails) {
        this.numberOfSentEMails = numberOfSentEMails;
    }

    public Result() { }

    public int getNumberOfSentEMails() {
        return numberOfSentEMails;
    }

    public void setNumberOfSentEMails(int numberOfSentEMails) {
        this.numberOfSentEMails = numberOfSentEMails;
    }
}

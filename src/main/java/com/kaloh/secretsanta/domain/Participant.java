package com.kaloh.secretsanta.domain;

public class Participant {

    private String name;
    private String emailAdress;

    public Participant(String name, String emailAdress) {
        this.name = name;
        this.emailAdress = emailAdress;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }


}

package com.kaloh.secretsanta.dto;


import java.util.ArrayList;

public class SecretSantaRoundRequest {

    private String year;
    ArrayList<ParticipantDto> participants;

    public SecretSantaRoundRequest(String year, ArrayList<ParticipantDto> participants) {
        this.year = year;
        this.participants = participants;
    }

    public SecretSantaRoundRequest() {}

    public String getYear() {
        return year;
    }

    public ArrayList<ParticipantDto> getParticipants() {
        return participants;
    }


}

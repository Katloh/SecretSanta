package com.kaloh.secretsanta.dto;


import lombok.EqualsAndHashCode;

import java.util.ArrayList;
@EqualsAndHashCode
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

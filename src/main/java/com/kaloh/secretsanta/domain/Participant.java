package com.kaloh.secretsanta.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kaloh.secretsanta.dto.ParticipantDto;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Participant {

    private String name;
    private String emailAdress;

    public Participant(String name, String emailAdress) {
        this.name = name;
        this.emailAdress = emailAdress;
    }

    public Participant(ParticipantDto participantDto){
        this.name = participantDto.getName();
        this.emailAdress = participantDto.geteMailAddress();
    }

    public String getName() {
        return name;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

}

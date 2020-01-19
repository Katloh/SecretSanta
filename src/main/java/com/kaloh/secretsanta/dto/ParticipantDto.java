package com.kaloh.secretsanta.dto;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ParticipantDto {

    private String name;
    private String eMailAddress;

    public ParticipantDto(String name, String eMailAddress) {
        this.name = name;
        this.eMailAddress = eMailAddress;
    }

    public ParticipantDto(){}

    public String getName() {
        return name;
    }

    public String geteMailAddress() {
        return eMailAddress;
    }
}

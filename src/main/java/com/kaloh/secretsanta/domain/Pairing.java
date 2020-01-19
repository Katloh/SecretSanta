package com.kaloh.secretsanta.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Pairing {

    private Participant donee;
    private Participant giftee;

    public Pairing(Participant donee, Participant giftee) {
        this.donee = donee;
        this.giftee = giftee;
    }

    public Participant getDonee() {
        return donee;
    }

    public void setDonee(Participant donee) {
        this.donee = donee;
    }

    public Participant getGiftee() {
        return giftee;
    }

    public void setGiftee(Participant giftee) {
        this.giftee = giftee;
    }
}

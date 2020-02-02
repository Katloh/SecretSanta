package com.kaloh.secretsanta.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Pairing {

    private Participant donor;
    private Participant giftee;

    public Pairing(Participant donor, Participant giftee) {
        this.donor = donor;
        this.giftee = giftee;
    }

    public Participant getDonor() {
        return donor;
    }

    public void setDonor(Participant donor) {
        this.donor = donor;
    }

    public Participant getGiftee() {
        return giftee;
    }

    public void setGiftee(Participant giftee) {
        this.giftee = giftee;
    }
}

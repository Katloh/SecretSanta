package com.kaloh.secretsanta.eMail;

import com.kaloh.secretsanta.domain.EMail;
import com.kaloh.secretsanta.domain.Pairing;

import java.util.ArrayList;
import java.util.List;

public class TestMailService {

    List<EMail> sentEMails = new ArrayList<>();

    public void sendMail(Pairing pairing, String year) {
        createNewEMail(pairing, year);
    }

    public EMail createNewEMail(Pairing pairing, String year) {
        EMail eMail = new EMail(pairing.getDonee().getEmailAdress(), pairing.getDonee().getName(), pairing.getGiftee().getName(), year);
        sentEMails.add(eMail);
        return eMail;
    }

    public int getNumberOfSentEMails() {
        return sentEMails.size();
    }

    public List<EMail> getSentEmails() {
        return sentEMails;
    }
}



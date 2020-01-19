package com.kaloh.secretsanta;

import com.kaloh.secretsanta.domain.Participant;
import com.kaloh.secretsanta.domain.SecretSantaRound;
import com.kaloh.secretsanta.dto.SecretSantaRoundRequest;
import com.kaloh.secretsanta.eMail.TestMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecretSantaService {

    private TestMailService testMailService;
    private SecretSantaRound secretSantaRound;

    @Autowired
    public SecretSantaService(TestMailService testMailService){
        this.testMailService = testMailService;

    }

    public ArrayList<String> startRound(SecretSantaRoundRequest request) {

        SecretSantaRound secretSantaRound = new SecretSantaRound(request.getYear(), testMailService);

        List<Participant> participantList = request.getParticipants().stream().map(Participant::new).collect(Collectors.toList());

        secretSantaRound.runSecretSantaRound(participantList);

        ArrayList<String> donorNames = new ArrayList<>();

        testMailService.getSentEmails().forEach(eMail ->
                System.out.println(eMail.getDonorName()));

        testMailService.getSentEmails().forEach(eMail ->
                donorNames.add(eMail.getDonorName()));

        return donorNames;
    }
}

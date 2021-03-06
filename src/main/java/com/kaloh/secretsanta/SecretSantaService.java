package com.kaloh.secretsanta;

import com.kaloh.secretsanta.domain.Participant;
import com.kaloh.secretsanta.domain.Result;
import com.kaloh.secretsanta.domain.SecretSantaRound;
import com.kaloh.secretsanta.dto.SecretSantaRoundRequest;
import com.kaloh.secretsanta.dto.SecretSantaRoundResponse;
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

    public Result startRound(SecretSantaRoundRequest request) {

        SecretSantaRound secretSantaRound = new SecretSantaRound(request.getYear(), testMailService);

        List<Participant> participantList = request.getParticipants().stream().map(Participant::new).collect(Collectors.toList());

        Result result = secretSantaRound.runSecretSantaRound(participantList);

        ArrayList<String> donorNames = new ArrayList<>();

        return result;
    }
}

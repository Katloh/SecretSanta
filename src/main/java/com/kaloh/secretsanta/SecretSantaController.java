package com.kaloh.secretsanta;

import com.kaloh.secretsanta.dto.SecretSantaRoundRequest;
import com.kaloh.secretsanta.dto.SecretSantaRoundResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class SecretSantaController {

    private SecretSantaService secretSantaService;

    public SecretSantaController(SecretSantaService secretSantaService) {
        this.secretSantaService = secretSantaService;
    }

    @RequestMapping(value = "/secretSanta",
                method = RequestMethod.POST)
    public SecretSantaRoundResponse createSecretSantaRound(@RequestBody SecretSantaRoundRequest entry) {
        return secretSantaService.startRound(entry);
    }

}

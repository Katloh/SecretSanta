package com.kaloh.secretsanta;

import com.kaloh.secretsanta.dto.SecretSantaRoundRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretSantaController {

    @RequestMapping(value = "/secretSanta",
                method = RequestMethod.POST)
    public void create(@RequestBody SecretSantaRoundRequest entry) {
        SecretSantaService secretSantaService = new SecretSantaService();
        secretSantaService.startRound(entry);
    }

}

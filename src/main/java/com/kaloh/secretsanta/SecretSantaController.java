package com.kaloh.secretsanta;

import com.kaloh.secretsanta.dto.SantaRoundRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecretSantaController {

    @RequestMapping(value = "/secretSanta",
                method = RequestMethod.POST,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody SantaRoundRequest entry){
    }

}

package com.kaloh.secretsanta;

import com.kaloh.secretsanta.dto.ParticipantDto;
import com.kaloh.secretsanta.dto.SecretSantaRoundRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecretSantaServiceTest {

    @Autowired
    private SecretSantaService secretSantaService;

    @Test           // @Test aus dem Paket org.junit nutzen, nicht org.junit.jupiter.api
    public void can_start_new_round_from_a_request() {
        //given a service and a start request
        ArrayList<ParticipantDto> participantDtos = new ArrayList<>();
        participantDtos.add(new ParticipantDto("Karl", "@karl"));
        participantDtos.add(new ParticipantDto("Lucy", "@Lucy"));
        SecretSantaRoundRequest request = new SecretSantaRoundRequest("2020",participantDtos);

        //when request comes in
        secretSantaService.startRound(request);
    }

    @Test
    public void starting_a_Round_returns_results(){

        ArrayList<ParticipantDto> participantDtos = new ArrayList<>();
        participantDtos.add(new ParticipantDto("Karl", "@karl"));
        participantDtos.add(new ParticipantDto("Lucy", "@Lucy"));

        SecretSantaRoundRequest request = new SecretSantaRoundRequest("2020",participantDtos);
        secretSantaService.startRound(request);

    }
}
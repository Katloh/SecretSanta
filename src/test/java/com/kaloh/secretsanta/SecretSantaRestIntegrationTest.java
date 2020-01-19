package com.kaloh.secretsanta;

import com.kaloh.secretsanta.dto.ParticipantDto;
import com.kaloh.secretsanta.dto.SecretSantaRoundRequest;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SecretSantaRestIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void initialiseRestAssuredMockMvcWebApplicationContext() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @Test
    public void status_is_200_for_a_successful_post() {

        ArrayList<ParticipantDto> participants = new ArrayList<>();
        participants.add(new ParticipantDto("katja", "@foo"));
        participants.add(new ParticipantDto("gergor", "@frefor"));

        SecretSantaRoundRequest secretSantaRoundRequest = new SecretSantaRoundRequest("2020", participants);

        given()
                .contentType("application/json")
                .body(secretSantaRoundRequest)
                .when()
                .post("/secretSanta", secretSantaRoundRequest)
                .then().assertThat().statusCode(200);
    }
}

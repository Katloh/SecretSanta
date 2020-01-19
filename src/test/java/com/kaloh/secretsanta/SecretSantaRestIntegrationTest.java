package com.kaloh.secretsanta;

import com.kaloh.secretsanta.dto.ParticipantDto;
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
    public void test() {

        ArrayList<ParticipantDto> participants = new ArrayList<>();
        participants.add(new ParticipantDto("katja", "@foo"));

        given()
                .body(participants)
                .when()
                .post("/secretSanta")
                .then()
                .assertThat()
                .body("participant.name", equalTo("katja"));

    }




}

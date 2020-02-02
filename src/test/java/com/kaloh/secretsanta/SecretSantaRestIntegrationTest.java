package com.kaloh.secretsanta;

import org.hamcrest.Matchers.*;
import com.kaloh.secretsanta.domain.Pairing;
import com.kaloh.secretsanta.dto.ParticipantDto;
import com.kaloh.secretsanta.dto.SecretSantaRoundRequest;
import com.kaloh.secretsanta.dto.SecretSantaRoundResponse;
import com.kaloh.secretsanta.eMail.TestMailService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import lombok.EqualsAndHashCode;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@EqualsAndHashCode
public class SecretSantaRestIntegrationTest {

    ArrayList<ParticipantDto> participants;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @SpyBean
    private TestMailService testMailService;

    @Before
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

        participants = new ArrayList<>();

        ParticipantDto participantOne = new ParticipantDto("katja", "@foo");
        ParticipantDto participantTwo = new ParticipantDto("gergor", "@frefor");

        participants.add(participantOne);
        participants.add(participantTwo);
    }

    @AfterEach
    public void resetMockMvc() {
        RestAssured.reset();
    }

    @Captor
    private ArgumentCaptor<Pairing> pairingCaptor = ArgumentCaptor.forClass(Pairing.class);

    @Captor
    private ArgumentCaptor<String> yearCaptor = ArgumentCaptor.forClass(String.class);

    @Test
    public void status_is_200_for_a_post_with_two_participants() {
        SecretSantaRoundRequest secretSantaRoundRequest = new SecretSantaRoundRequest("2020", participants);

        given()
                .contentType("application/json")
                .body(secretSantaRoundRequest)
                .when()
                .post("/secretSanta", secretSantaRoundRequest)
                .then().assertThat().statusCode(200);
    }

    @Test
    public void posting_two_participants_will_send_out_two_eMails_to_both_donors() {
        SecretSantaRoundRequest secretSantaRoundRequest = new SecretSantaRoundRequest("2020", participants);
        given()
                .contentType("application/json")
                .body(secretSantaRoundRequest)
                .when()
                .post("/secretSanta", secretSantaRoundRequest);

        verify(testMailService, times(2)).sendMail(pairingCaptor.capture(), anyString());
        List<Pairing> capturedPairings = pairingCaptor.getAllValues();

        assertTrue(isDonorInList("gergor", capturedPairings));
        assertTrue(isDonorInList("katja", capturedPairings));
    }

    @Test
    public void posting_a_year_will_create_eMails_with_correct_year_in_the_subject() {
        SecretSantaRoundRequest secretSantaRoundRequest = new SecretSantaRoundRequest("2020", participants);
        given()
                .contentType("application/json")
                .body(secretSantaRoundRequest)
                .when()
                .post("/secretSanta", secretSantaRoundRequest);

        verify(testMailService, times(2)).sendMail(any(), yearCaptor.capture());
        List<String> capturedYears = yearCaptor.getAllValues();

        assertTrue(capturedYears.stream().allMatch(year -> year.equals("2020")));
    }

    @Test
    public void posting_two_participants_will_return_number_of_sent_eMails() throws UnsupportedEncodingException {

        SecretSantaRoundRequest secretSantaRoundRequest = new SecretSantaRoundRequest("2020", participants);

        given()
                .contentType("application/json")
                .body(secretSantaRoundRequest)
                .when()
                .post("/secretSanta", secretSantaRoundRequest)
                .then()
                .body("numberOfSentEMails", equalTo(2));

    }

    private boolean isDonorInList(String string, List<Pairing> pairings) {
        boolean match = false;
        return match = pairings.stream().filter(pairing -> pairing.getDonor().getName().equals(string)).count() == 1;
    }
}

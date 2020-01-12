package com.kaloh.secretsanta;

import com.kaloh.secretsanta.domain.Pairing;
import com.kaloh.secretsanta.domain.Participant;
import com.kaloh.secretsanta.domain.SecretSantaRoundLogger;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SecretSantaRoundLoggerTest {

    @Test
    public void a_round_is_documented_anonymously() throws IOException {
        //given
        SecretSantaRoundLogger secretSantaRoundLogger = new SecretSantaRoundLogger();
        ArrayList<Pairing> testPairings = new ArrayList<>();
        testPairings.add(new Pairing(
                new Participant("Steve", "foo@bar.com"),
                new Participant("Alice", "foo@bar.com"))
        );
        BufferedWriter mock = mock(BufferedWriter.class);

        //when
        secretSantaRoundLogger.documentSecretSantaRound(testPairings, "2019", mock);

        //then
        verify(mock).write(testPairings.get(0).getDonee().hashCode()+" : "+testPairings.get(0).getGiftee().hashCode()+ "\n");
    }
}

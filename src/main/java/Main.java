import java.util.ArrayList;

public class Main {

    public static void main(String args[]) throws Exception{

        ArrayList<Participant> participants = new ArrayList<>();
        TestMailService testMailService = new TestMailService();
        SecretSantaRound secretSantaRound = new SecretSantaRound("2020",testMailService);

    }

}

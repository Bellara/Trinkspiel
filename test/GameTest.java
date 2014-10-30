import de.lauverngers.challenge.ChallengeService;
import de.lauverngers.objects.Challenge;
import de.lauverngers.objects.Drink;
import de.lauverngers.objects.Game;
import de.lauverngers.objects.Player;

import java.io.IOException;

public class GameTest extends Test{

    public GameTest() throws IOException {
        super();
    }

    public static void main(String[] args) throws IOException {
        final GameTest gameTest = new GameTest();
        final Player playerOne = new Player("Dieter", 0);
        gameTest.getGame().addPlayer(playerOne);
        gameTest.getGame().addDrink(new Drink("Beton", "Liter"));

        final Challenge challenge = gameTest.getGame().nextRound();

        assert gameTest.getGame().getCurrentPlayer().equals(playerOne);

        gameTest.getGame().getOnGoingChallenges();
    }

}

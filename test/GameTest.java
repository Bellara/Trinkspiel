import de.lauverngers.challenge.ChallengeService;
import de.lauverngers.objects.Challenge;
import de.lauverngers.objects.Drink;
import de.lauverngers.objects.Game;
import de.lauverngers.objects.Player;

import java.io.IOException;

public class GameTest {

    public static void main(String[] args) throws IOException {
        final Game game = new Game(1);
        try {
            game.init();
        }
        catch (IOException ioe) {
            System.out.println("Whatever");
        }

        final Player playerOne = new Player("Dieter", 0);
        game.addPlayer(playerOne);
        game.addDrink(new Drink("Beton", "Liter"));

        final Challenge challenge = game.nextRound();

        assert game.getCurrentPlayer().equals(playerOne);

        game.getOnGoingChallenges();


    }

}

import de.lauverngers.challenge.ChallengeService;
import de.lauverngers.objects.Drink;
import de.lauverngers.objects.Game;
import de.lauverngers.objects.Player;

import java.io.IOException;

public class GameTest {

    public static void main(String[] args) {
        final Game game = new Game(1, new ChallengeService());
        try {
            game.init();
        }
        catch (IOException ioe) {
            System.out.println("Whatever");
        }

        final Player playerOne = new Player("Dieter", 0);
        game.addPlayer(playerOne);
        game.addDrink(new Drink("Beton", "Liter"));

        game.nextRound();

        assert game.getCurrentPlayer().equals(playerOne);

        game.getOnGoingChallenges();


    }

}

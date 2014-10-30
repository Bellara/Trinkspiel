import de.lauverngers.objects.Drink;
import de.lauverngers.objects.Game;
import de.lauverngers.objects.Player;

import java.io.IOException;

/**
 * Created by pm on 30.10.14.
 */
public class Test {

    private Game game;

    public Test() throws IOException {
        game = new Game();

        try {
            game.init();
        }
        catch (IOException ioe) {
            System.out.println("Whatever");
        }

        final Player playerOne = new Player("Dieter", 0);
        game.addPlayer(playerOne);
        game.addDrink(new Drink("Beton", "Liter"));

    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}

import de.lauverngers.objects.Game;
import de.lauverngers.objects.Player;

import java.io.IOException;

/**
 * Created by pm on 13.11.14.
 */
public class PlayerSelectTest extends Test{
    public PlayerSelectTest() throws IOException {
        super();
    }

    public static void main(String[] args) throws IOException {
        final PlayerSelectTest test = new PlayerSelectTest();
        final Game game = test.getGame();
        for(int i = 0; i < 8; i++) {
            game.addPlayer(new Player("Player"+i,i));
        }


        game.nextRound();
        game.nextRound();
        game.nextRound();
        game.nextRound();
        game.nextRound();
        game.nextRound();
        game.nextRound();

    }
}

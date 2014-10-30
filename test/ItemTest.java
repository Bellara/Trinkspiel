import de.lauverngers.objects.Game;
import de.lauverngers.objects.Player;
import de.lauverngers.objects.items.GoldCard;
import de.lauverngers.objects.items.Item;
import de.lauverngers.objects.items.PointSteal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pm on 30.10.14.
 */
public class ItemTest extends Test {

    public ItemTest() throws IOException {
        super();
    }

    public static void main(String[] args) throws IOException {
        final ItemTest test = new ItemTest();
        final Game game = test.getGame();
        game.addPlayer(new Player("Günther",0));

        final List<Player> playerList = game.getPlayers();

        assert !playerList.isEmpty();

        Player p = playerList.get(0);

        assert p != null;

        final List<Item> itemList = new ArrayList<>();
        itemList.add(new PointSteal());
        itemList.add(new GoldCard());
        p.setItems(itemList);

        game.useItem(p, itemList.get(0), null);
        assert p.getItems().size() == 2;

        final List<Object> targetList = new ArrayList<>();
        targetList.add(playerList.get(1));

        game.useItem(p, itemList.get(0), targetList);

        assert p.getCredits() == 2;
        assert ((Player)targetList.get(0)).getCredits() == -2;

        //0 because first item has been used and is removed from list
        game.useItem(p, itemList.get(0), null);

        //no more items left for this player
        assert p.getItems().size() == 0;
    }
}

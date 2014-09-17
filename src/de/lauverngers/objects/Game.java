package de.lauverngers.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maren on 17.09.14.
 */
public class Game {

    public List<Player> players = new ArrayList();
    public List<Drink> drinks = new ArrayList();

    public int maxPoints;
    //Die Wahrscheinlichkeit von Aufgaben
    public int challengeQuotient;
    public int round;

    public Game() {
        round = 0;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void addDrink(Drink drink){
        drinks.add(drink);
    }

    public boolean gameFinished(){
        for (Player player : players) {
            if (player.getCredits() >= maxPoints) {
                return true;
            }
        }
        return false;
    }

    public void nextRound(){
        round++;
    }
}

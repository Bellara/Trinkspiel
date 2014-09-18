package de.lauverngers.objects;

import de.lauverngers.challenge.ChallengeReader;
import de.lauverngers.challenge.ChallengeService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maren on 17.09.14.
 */
public class Game {

    public List<Player> players = new ArrayList();
    public List<Drink> drinks = new ArrayList();
    private ChallengeService challengeService;
    private double possibilityOfChallenge;

    public int maxPoints;
    //Die Wahrscheinlichkeit von Aufgaben
    public int challengeQuotient;
    public int round;

    public Game(ChallengeService challengeService) {
        round = 0;
        this.challengeService = challengeService;
    }

    public Game(double possibilityOfChallenge, ChallengeService challengeService) {
        this.challengeService = challengeService;
        this.possibilityOfChallenge = possibilityOfChallenge;
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

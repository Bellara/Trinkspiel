package de.lauverngers.objects;

import de.lauverngers.challenge.ChallengeService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {

    public List<Player> players = new ArrayList();
    public List<Drink> drinks = new ArrayList();
    private ChallengeService challengeService;

    public int maxPoints;
    //Die Wahrscheinlichkeit von Aufgaben
    public double challengeQuotient;
    public int round;

    public Game(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    public Game(double challengeQuotient, ChallengeService challengeService) {
        this.challengeService = challengeService;
        this.challengeQuotient = challengeQuotient;
    }

    public void init() throws IOException {
        round = 0;
        this.challengeQuotient = 0.5;
        final ChallengeService challengeService = new ChallengeService();
        challengeService.loadChallenges();
        this.challengeService = challengeService;

    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public boolean gameFinished() {
        for (Player player : players) {
            if (player.getCredits() >= maxPoints) {
                return true;
            }
        }
        return false;
    }

    public void nextRound() {
        round++;
    }
}

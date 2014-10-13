package de.lauverngers.objects;

import de.lauverngers.challenge.ChallengeService;

import java.io.IOException;
import java.util.*;

public class Game {

    private List<Player> players = new ArrayList();
    private Player currentPlayer;
    private List<Drink> drinks = new ArrayList();
    private ChallengeService challengeService;
    private Map<Long, Challenge> onGoingChallenges = new HashMap<>();

    private int maxPoints;
    private double challengeQuotient;
    private int round;

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
        if (players.size() > 0) {
            currentPlayer = players.get(0);
        }

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

    public Challenge nextRound() {
        round++;

        Challenge challenge = null;

        handleChallengeLifeTimeDecrease();

        if (players.size() > 0) {

            currentPlayer = players.get(round % players.size());
            double d = Math.random();
            if (d < challengeQuotient) {
                challenge = challengeService.getRandomChallenge(players, currentPlayer);
                if (challenge != null && challenge.getRoundCount() != null) {
                    onGoingChallenges.put(challenge.getId(), challenge);
                }
            }
        }

        return challenge;
    }

    private void handleChallengeLifeTimeDecrease() {
        for (Iterator<Long> iterator = onGoingChallenges.keySet().iterator(); iterator.hasNext(); ) {
            final Long challengeId = iterator.next();
            final Challenge challenge = onGoingChallenges.get(challengeId);

            challenge.reduceRoundCounter();



            if (challenge.getRoundCount() <= 0) {
                onGoingChallenges.remove(challengeId);
            }
        }
    }

    public String removePlayer(Long id) {

        if (id != null && id <= players.size()) {
            return removePlayer(players.get(id.intValue()));
        }

        return null;
    }

    public String removePlayer(String name) {

        if (name != null) {

            Player playerToRemove = null;

            for (Player player : players) {
                if (name.equals(player.getName())) {
                    playerToRemove = player;
                    break;
                }
            }

            return removePlayer(playerToRemove);

        }
        return null;
    }

    public String removePlayer(Player player) {
        players.remove(player);
        return "Hier könnte ihre Werbung stehen.";
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public Map<Long, Challenge> getOnGoingChallenges() {
        return onGoingChallenges;
    }

    public void setOnGoingChallenges(Map<Long, Challenge> onGoingChallenges) {
        this.onGoingChallenges = onGoingChallenges;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public double getChallengeQuotient() {
        return challengeQuotient;
    }

    public void setChallengeQuotient(double challengeQuotient) {
        this.challengeQuotient = challengeQuotient;
    }
}

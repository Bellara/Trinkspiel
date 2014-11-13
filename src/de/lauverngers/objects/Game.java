package de.lauverngers.objects;

import de.lauverngers.challenge.ChallengeService;
import de.lauverngers.objects.items.*;

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

    public Game() throws IOException {
        init();
    }

    public Game(double challengeQuotient) throws IOException {
        init();
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

    public Challenge nextRound() {
        round++;

        handleChallengeLifeTimeDecrease();

        if (players.size() > 0) {
            if (players.size() >= 3) {
                final Integer diceRoll = Die.throwDie(100);

                if (diceRoll < 70) {
                    currentPlayer = getRandomPlayer(true);
                } else {
                    currentPlayer = getRandomPlayer(false);
                }

            } else {
                currentPlayer = getRandomPlayer(false);
            }
            return getRandomChallenge();
        }
        return null;
    }

    private Player getRandomPlayer(boolean isSemiRandom) {
        if (isSemiRandom) {
            //cose player semi-random to give player with fewer points more chances to get a turn
            return getSemiRandomPlayer();
        } else {
            //choose player by random (very random, much dice, wow)
            return getRandomPlayer();
        }
    }

    private Player getSemiRandomPlayer() {

        //player points sorted list
        final List<Player> tmpPlayerList = new ArrayList<>();
        tmpPlayerList.addAll(players);
        Collections.sort(tmpPlayerList, PLAYER_POINTS_COMPARATOR);

        //club cant handle me right now !
        final List<Player> worstThreePerforming = tmpPlayerList.subList(0, 3);

        final Integer diceRoll = Die.throwDie(100);

        if (diceRoll < 60) {
            return worstThreePerforming.get(0);
        } else {
            if (diceRoll < 20) {
                return worstThreePerforming.get(1);
            } else {
                return worstThreePerforming.get(2);
            }
        }
    }


    private Player getRandomPlayer() {
        return players.get(Die.throwDice(players.size(), 1));
    }

    private Challenge getRandomChallenge() {
        Challenge challenge = null;
        if (Math.random() < challengeQuotient) {
            challenge = challengeService.getRandomChallenge(players, currentPlayer);
            if (challenge != null && challenge.getRoundCount() != null) {
                onGoingChallenges.put(challenge.getId(), challenge);
            }
        }
        return challenge;
    }

    private void handleChallengeLifeTimeDecrease() {
        for (final Long challengeId : onGoingChallenges.keySet()) {
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

    public Map<Player, List<Item>> getPlayerItemMapping() {
        final Map<Player, List<Item>> playerItemMapping = new HashMap<>();
        for (Player player : players) {
            playerItemMapping.put(player, player.getItems());
        }
        return playerItemMapping;
    }

    //item usage can return a String which can be shown on the client side
    public String useItem(Player player, Item item, List<Object> target) {
        if (player == null || item == null) {
            return null;
        }
        if (!player.getItems().contains(item)) {
            return null;
        }

        final List<Player> targetPlayers = new ArrayList<>();
        final List<Drink> targetDrinks = new ArrayList<>();

        if (target != null && !target.isEmpty()) {
            for (Object o : target) {
                if (o instanceof Player) {
                    targetPlayers.add((Player) o);
                } else if (o instanceof Drink) {
                    targetDrinks.add((Drink) o);
                }
            }
        }

        String returnMessage = null;

        if (item instanceof PointSteal) {
            if (item instanceof MultiPointSteal) { //because MultiSteal is just a child of normal steal
                final MultiPointSteal stealItem = (MultiPointSteal) item;

                if (targetPlayers.isEmpty()) {
                    return null;
                }

                //multisteal ? sounds like fun ;)
                for (Player targetedPlayer : targetPlayers) {
                    player.increaseCredits(stealItem.getAmountOfPoints());
                    targetedPlayer.decreaseCredit(stealItem.getAmountOfPoints());
                }
                returnMessage = "Slappage für alle !! \n" + player.getName() + " erleichert ALLE anderen Spieler um " + stealItem.getAmountOfPoints() + " Punkte  !";
            } else {
                final PointSteal stealItem = (PointSteal) item;

                if (targetPlayers.isEmpty()) {
                    return null;
                }

                player.increaseCredits(stealItem.getAmountOfPoints());
                targetPlayers.get(0).decreaseCredit(stealItem.getAmountOfPoints());
                returnMessage = "Ouch ... " + targetPlayers.get(0).getName() + " wurde durch " + player.getName() + " um " + stealItem.getAmountOfPoints() + " erleichtert !";
            }

        } else if (item instanceof GoldCard) {
            Integer amountOfPoints = 0;
            final GoldCard goldCard = (GoldCard) item;
            amountOfPoints = goldCard.getPoints();
            player.increaseCredits(amountOfPoints);
            returnMessage = "Ka-tsching !! " + player.getName() + " ist um " + amountOfPoints + " reicher geweorden !!";
        } else if (item instanceof DoublingSeason) {
            returnMessage = "Oh es ist " + item.getName() + "\n" + targetPlayers.get(0).getName() + " guten Durst ! PROST !";
        } else if (item instanceof FreeCard) {
            returnMessage = player.getName() + " hat das Getränk geschickt umgangen (Weichei)";
        } else if (item instanceof Execution) {
            returnMessage = player.getName() + " exekutiert " + targetPlayers.get(0).getName() + " ! PROST !";
        } else if (item instanceof FortuneWheel) {
            //toDo: Implement Wheel of Fortune !!
        } else if (item instanceof ComboBreaker) {
            returnMessage = item.getName() + " " + player.getName() + " genießt Narrenfreiheit (vorerst)!";
        }

        player.getItems().remove(item);
        return returnMessage;
    }

    private static final Comparator PLAYER_POINTS_COMPARATOR = new Comparator() {
        public int compare(Object o1, Object o2) {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return 1;
            }
            if (o2 == null) {
                return -1;
            }
            final Player player1 = (Player) o1;
            final Player player2 = (Player) o2;
            if (player1.getCredits() == null && player2.getCredits() == null) {
                return 0;
            }
            if (player1.getCredits() != null && player2.getCredits() == null) {
                return 1;
            }
            if (player1.getCredits() == null && player2.getCredits() != null) {
                return -1;
            }
            return player1.getCredits().compareTo(player2.getCredits());
        }
    };
}

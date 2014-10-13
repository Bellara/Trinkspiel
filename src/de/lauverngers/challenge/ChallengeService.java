package de.lauverngers.challenge;

import de.lauverngers.Constants;
import de.lauverngers.objects.Challenge;
import de.lauverngers.objects.Die;
import de.lauverngers.objects.Player;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChallengeService {

    List<Challenge> challengeList;

    List<Long> idsOfLastFiveChallenges = new ArrayList();

    public ChallengeService() {
    }

    public void loadChallenges() throws IOException {
        challengeList = ChallengeReader.readChallengesFromFile();
    }

    public Challenge getRandomChallenge(List<Player> playerList, Player player) {

        if (challengeList == null || challengeList.isEmpty()) {
            return null;
        }

        int diceRoll = rollChallengeId();

        final Challenge randomChallenge = challengeList.get(diceRoll);

        handleLastFiveChallengesList(randomChallenge);

        fillChallengeWithLifeTime(randomChallenge);

        fillRandomChallengeWithPointsIfNeeded(randomChallenge, playerList, player);

        return randomChallenge;
    }

    private void fillChallengeWithLifeTime(Challenge randomChallenge) {
        if(new Integer(0).equals(randomChallenge.getRoundCount())) {
            randomChallenge.setRoundCount(Die.throwSingleDie());
        }
    }

    private void handleLastFiveChallengesList(Challenge randomChallenge) {
        if (challengeList.size() > 5) {
            if (idsOfLastFiveChallenges.size() + 1 > 5) {
                idsOfLastFiveChallenges.remove(0);
            }
            idsOfLastFiveChallenges.add(randomChallenge.getId());
        }
    }

    private int rollChallengeId() {
        int diceRoll = Die.throwDice(challengeList.size(), 1);

        if (challengeList.size() > 5) {
            while (idsOfLastFiveChallenges.contains(diceRoll)) {
                diceRoll = Die.throwDice(challengeList.size(), 1);
            }
        }

        return diceRoll;
    }

    private void fillRandomChallengeWithPointsIfNeeded(Challenge randomChallenge, List<Player> playerList, Player player) {

        if (randomChallenge.getPoints() == null) {
            if (Constants.DICE_ACTION.equals(randomChallenge.getAction())) {
                fillChallengeTextWithPoints(randomChallenge);
            }
            else if (Constants.PLAYER_ACTION.equals(randomChallenge.getAction())) {

                if (playerList == null || playerList.size() <= 0) {
                    return;
                }

                fillChallengeTextWithPlayers(randomChallenge, playerList, player);
            }
        }
    }

    private void fillChallengeTextWithPoints(Challenge randomChallenge) {
        final int diceRoll = Die.throwSingleDie();
        randomChallenge.setPoints(diceRoll);
        randomChallenge.setText(randomChallenge.getText().replaceAll(Constants.PLACE_HOLDER_STRING, String.valueOf(diceRoll)));
    }

    private void fillChallengeTextWithPlayers(Challenge randomChallenge, List<Player> playerList, Player player) {
        final List<Player> tmpPlayerList = createTemporaryPlayerListToWorkOn(playerList, player);

        String text = randomChallenge.getText();

        final int amountOfPlayerTags = countPlayersInText(text);

        if (amountOfPlayerTags > tmpPlayerList.size()) {
            return;
        }

        for (int i = 0; i < amountOfPlayerTags; i++) {

            final int diceRoll = Die.throwDice(tmpPlayerList.size(), 1);
            final Player newPlayer = tmpPlayerList.get(diceRoll);

            text = text.replaceFirst("\\[p\\]", newPlayer.getName());

            tmpPlayerList.remove(newPlayer);
        }
    }

    private List<Player> createTemporaryPlayerListToWorkOn(List<Player> playerList, Player player) {
        //new tmp list for safety if the original List has been entered
        final List<Player> tmpPlayerList = new ArrayList();
        tmpPlayerList.addAll(playerList);
        tmpPlayerList.remove(player);
        return tmpPlayerList;
    }

    private int countPlayersInText(String text) {
        return StringUtils.countMatches(text, "\\[p\\]");
    }

    public Challenge getChallengeById(Long challengeId) {
        for(Challenge challenge : challengeList) {
            if(challengeId.equals(challenge.getId())) {
                return challenge;
            }
        }
        return null;
    }
}

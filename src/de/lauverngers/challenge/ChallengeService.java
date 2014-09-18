package de.lauverngers.challenge;

import de.lauverngers.Constants;
import de.lauverngers.objects.Challenge;
import de.lauverngers.objects.Die;

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

    public Challenge getRandomChallenge() {

        if (challengeList == null || challengeList.isEmpty()) {
            return null;
        }

        int diceRoll = rollChallengeId();

        final Challenge randomChallenge = challengeList.get(diceRoll);

        handleLastFiveChallengesList(randomChallenge);

        fillRandomChallengeWithPointsIfNeeded(randomChallenge);

        return randomChallenge;
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

    private void fillRandomChallengeWithPointsIfNeeded(Challenge randomChallenge) {
        if (randomChallenge.getPoints() == null) {
            if (Constants.DICE_ACTION.equals(randomChallenge.getAction())) {
                final int diceRoll = Die.throwSingleDie();
                randomChallenge.setPoints(diceRoll);
                randomChallenge.setText(randomChallenge.getText().replaceAll("\\{x\\}", String.valueOf(diceRoll)));
            }
        }
    }

}

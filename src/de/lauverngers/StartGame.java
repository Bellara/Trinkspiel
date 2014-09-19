package de.lauverngers;

import de.lauverngers.challenge.ChallengeService;
import de.lauverngers.view.GameStartPanel;

import java.io.IOException;

/**
 * Created by Maren on 18.09.14.
 */
public class StartGame {

    public static void main(String[] args) {
//        initGame
        final GameStartPanel gameStartPanel= new GameStartPanel();
//        startGame
//        endGame
    }

    private ChallengeService initiateChallenges() throws IOException {
        final ChallengeService challengeService = new ChallengeService();
        challengeService.loadChallenges();
        return challengeService;
    }
}

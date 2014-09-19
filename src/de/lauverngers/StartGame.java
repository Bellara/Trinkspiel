package de.lauverngers;

import de.lauverngers.challenge.ChallengeService;
import de.lauverngers.view.GameStartPanel;

import java.io.IOException;

public class StartGame {

    public static void main(String[] args) throws IOException {
//        initGame
        final GameStartPanel gameStartPanel= new GameStartPanel();

//        startGame
//        endGame
    }

    private static ChallengeService reloadChallengeService() throws IOException {
        final ChallengeService challengeService = new ChallengeService();
        challengeService.loadChallenges();
        return challengeService;
    }
}

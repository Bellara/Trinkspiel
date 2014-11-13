package de.lauverngers.objects;

import java.util.Random;

public class Die {

    private static Integer defaultAmountOfEyes = 6;

    public static int throwDice(int numberOfDice) {
        return throwDice(null, numberOfDice);
    }

    public static int throwDice(Integer amountOfEyesForEachDie, int numberOfDice) {

        int eyes = 0;

        for (int i = 1; i <= numberOfDice; i++) {
            eyes += throwDie(amountOfEyesForEachDie);
        }

        return eyes;
    }

    public static int throwFairDice() {
        return 4;
    }

    public static int throwSingleDie() {
        return throwDie(null);
    }

    public static int throwDie(Integer amountOfEyesForEachDie) {

        if(amountOfEyesForEachDie == null) {
            amountOfEyesForEachDie = defaultAmountOfEyes;
        }

        return new Random().nextInt(amountOfEyesForEachDie);
    }
}
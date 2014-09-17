package de.lauverngers.objects;

import java.util.Random;

/**
 * Created by Maren on 17.09.14.
 */
public class Dice {

    public int throwDice(){
        Random random = new Random();
        return random.nextInt(6);
    }

}
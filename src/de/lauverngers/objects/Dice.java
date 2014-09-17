package de.lauverngers.objects;

import java.util.Random;

/**
 * Created by Maren on 17.09.14.
 */
public class Dice {

    public int throwDice(){
        return new Random().nextInt(6);
    }

}
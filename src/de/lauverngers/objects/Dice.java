package de.lauverngers.objects;

import java.util.Random;

/**
 * Created by Maren on 17.09.14.
 */
public class Dice {

    public int throwDices(int iNumberOfDices){
        int eyes=0;
        for(int i = 1;i <= iNumberOfDices;i++){
            Random random = new Random();
            eyes=eyes+random.nextInt(6);
        }
        return eyes;
    }

}
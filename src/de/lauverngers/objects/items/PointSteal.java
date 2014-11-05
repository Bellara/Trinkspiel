package de.lauverngers.objects.items;

import java.awt.*;

/**
 * Created by pm on 30.10.14.
 */
public class PointSteal implements Item{
    private Integer amountOfPoints;

    public PointSteal() {
        this(2);
    }

    public PointSteal(Integer amountOfPoints) {
        this.amountOfPoints = amountOfPoints;
    }

    public Integer getAmountOfPoints() {
        return amountOfPoints;
    }

    public void setAmountOfPoints(Integer amountOfPoints) {
        this.amountOfPoints = amountOfPoints;
    }

    @Override
    public String getName() {
        return "Dieb";
    }

    @Override
    public String getDescription() {
        return "Zieht einem Spieler/Spielerin " + amountOfPoints + " Punkte ab und fügt Sie dem Anwender hinzu.";
    }
}

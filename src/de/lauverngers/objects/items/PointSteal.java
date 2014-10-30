package de.lauverngers.objects.items;

import java.awt.*;

/**
 * Created by pm on 30.10.14.
 */
public class PointSteal implements Item{
    private Integer amountOfPoints;

    public PointSteal() {
        this.amountOfPoints = 2;
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
}

package de.lauverngers.objects.items;

/**
 * Created by pm on 30.10.14.
 */
public class GoldCard implements Item{
    private Integer points;

    public GoldCard() {
        this.points = 2;
    }

    public GoldCard(Integer points) {
        this.points = points;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}

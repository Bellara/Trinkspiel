package de.lauverngers.objects.items;

/**
 * Created by pm on 30.10.14.
 */
public class GoldCard implements Item{

    //toDo: possibility to be a diamond card giving more points ?
    private Integer points;

    public GoldCard() {
        this(1);
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

    @Override
    public String getName() {
        return "Gold Karte";
    }

    @Override
    public String getDescription() {
        return "Schreibe dir "+points+" Punkt(e) gut";
    }
}

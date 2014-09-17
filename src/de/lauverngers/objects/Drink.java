package de.lauverngers.objects;

/**
 * Created by Maren on 17.09.14.
 */
public class Drink {

    private String name;
    private String unit;

    public Drink(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}

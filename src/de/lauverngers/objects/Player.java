package de.lauverngers.objects;

import de.lauverngers.objects.items.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maren on 17.09.14.
 */
public class Player {

    private String name;
    private int credits;
    private List<Item> items = new ArrayList<>();

    public Player(String name, int credits) {
        this.name = name;
        this.credits = credits;
    }

    public void increaseCredits(int addCredits){
        credits += addCredits;
    }

    public void decreaseCredit(int creditLoss) {
        credits -= creditLoss;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}

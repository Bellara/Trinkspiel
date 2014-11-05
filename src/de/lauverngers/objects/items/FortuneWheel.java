package de.lauverngers.objects.items;

/**
 * Created by pm on 05.11.14.
 */
public class FortuneWheel implements Item{
    @Override
    public String getName() {
        return "Glücksrad";
    }

    @Override
    public String getDescription() {
        return "Weise eine Aufgabe oder ein gerade erhaltenens Getränk einem zufälligen Spieler zu.";
    }
}

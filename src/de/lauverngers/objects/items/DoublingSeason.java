package de.lauverngers.objects.items;

/**
 * Created by pm on 05.11.14.
 */
public class DoublingSeason implements Item{
    @Override
    public String getName() {
        return "Zeit der Verdoppelung"; //doubling season *.*
    }

    @Override
    public String getDescription() {
        return "Verdoppel ein Getränk eines beliebigen Spielers.";
    }
}

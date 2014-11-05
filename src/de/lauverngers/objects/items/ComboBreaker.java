package de.lauverngers.objects.items;

/**
 * Created by pm on 05.11.14.
 */
public class ComboBreaker implements Item{
    @Override
    public String getName() {
        return "C-C-C-Combobreaker";
    }

    @Override
    public String getDescription(){
        return "Du darfst gegen bestehende Regeln ohne Bestrafung verstoßen.";
    }
}

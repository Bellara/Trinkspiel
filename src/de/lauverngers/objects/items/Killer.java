package de.lauverngers.objects.items;

/**
 * Created by pm on 05.11.14.
 */
public class Killer implements Item {
    @Override
    public String getName() {
        return "Killer";
    }

    @Override
    public String getDescription() {
        return "Schicke jemanden einen Killer! vorbei.";
    }
}

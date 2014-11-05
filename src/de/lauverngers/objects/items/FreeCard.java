package de.lauverngers.objects.items;

/**
 * Created by pm on 05.11.14.
 */
public class FreeCard implements Item{
    @Override
    public String getName() {
        return "Gefängnissfreikarte";
    }

    @Override
    public String getDescription() {
        return "Du musst das nächste Getränk nicht Trinken.";
    }
}

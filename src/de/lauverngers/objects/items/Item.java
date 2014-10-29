package de.lauverngers.objects.items;

public class Item {

    //toDo: Implement properly !!!!1 This is just the base so go ahead !

    private String name;
    private String text;

    public Item(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

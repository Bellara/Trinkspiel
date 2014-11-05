package de.lauverngers.objects.items;

public class MultiPointSteal extends PointSteal {
    public MultiPointSteal(){
        super();
    }

    @Override
    public String getName() {
        return "Drain";
    }

    @Override
    public String getDescription() {
        return "Lasse alle anderen Spieler "+getAmountOfPoints()+" Punkt verlieren";
    }
}

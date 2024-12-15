package Herbivores;
import Interfaces.Herbivore;
import Animal.*;
import Plant.*;

public class Goat extends Animal implements Herbivore{
    public Goat(){
        super("Goat \uD83D\uDC10", 60,140,3,10);
        plantEatChances.put(Plant.class,100);
    }

    @Override
    protected Animal createNewAnimal() {
        return new Goat();
    }

    @Override
    public void eat_plant(Plant[][] plants) {

    }
}


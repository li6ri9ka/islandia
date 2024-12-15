package Herbivores;
import Interfaces.Herbivore;
import Animal.*;
import Plant.*;

public class Caterpillar extends Animal implements Herbivore{
    public Caterpillar(){
        super("Caterpillar \uD83D\uDC1B",0.01,1000,0,0);
        plantEatChances.put(Plant.class,100);
    }

    @Override
    protected Animal createNewAnimal() {
        return new Caterpillar();
    }

    @Override
    public void eat_plant(Plant[][] plants) {

    }
}


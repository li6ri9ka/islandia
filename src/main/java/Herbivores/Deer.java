package Herbivores;
import Animal.*;
import Interfaces.Herbivore;
import Plant.*;

public class Deer extends Animal implements Herbivore{
    public Deer(){
        super("Deer \uD83E\uDD8C", 300,20,4,50);
        plantEatChances.put(Plant.class,100);
    }

    @Override
    protected Animal createNewAnimal() {
        return new Deer();
    }

    @Override
    public void eat_plant(Plant[][] plants) {

    }
}


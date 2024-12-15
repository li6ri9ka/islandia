package Herbivores;
import Animal.*;
import Plant.*;
import Interfaces.Herbivore;

public class Rabbit extends Animal implements Herbivore {

    public Rabbit() {
        super("Rabbit \uD83D\uDC07", 2,150,2,0.45);
        plantEatChances.put(Plant.class, 100);
    }

    protected Animal createNewAnimal() {
        return new Rabbit();
    }

    @Override
    public void eat_plant(Plant[][] plants) {

    }
}
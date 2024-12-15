package Herbivores;
import Animal.*;
import Interfaces.Herbivore;
import Plant.*;

public class Sheep extends Animal implements Herbivore{
    public Sheep(){
        super("Sheep \uD83D\uDC11",70,140,3,15);
        plantEatChances.put(Plant.class,100);
    }

    @Override
    protected Animal createNewAnimal() {
        return new Sheep();
    }

    @Override
    public void eat_plant(Plant[][] plants) {

    }
}

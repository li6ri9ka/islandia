package Herbivores;
import Interfaces.Herbivore;
import Plant.*;
import Animal.*;

public class Buffalo extends Animal implements Herbivore{
    public Buffalo(){
        super("Buffalo \uD83D\uDC03",700,10,3,100);
        plantEatChances.put(Plant.class,100);
    }

    @Override
    protected Animal createNewAnimal() {
        return new Buffalo();
    }

    @Override
    public void eat_plant(Plant[][] plants) {

    }
}

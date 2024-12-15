package Herbivores;
import Animal.*;
import Interfaces.Herbivore;
import Plant.*;

public class Horse extends Animal implements Herbivore{
    public Horse(){
        super("Horse \uD83D\uDC0E", 400,20,4,60);
        plantEatChances.put(Plant.class,100);
    }

    @Override
    protected Animal createNewAnimal() {
        return new Horse();
    }

    @Override
    public void eat_plant(Plant[][] plants) {

    }
}
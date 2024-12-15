package Herbivores;
import Animal.*;
import Plant.*;
import Interfaces.*;

public class Duck extends Animal implements Herbivore,Predator{
    public Duck(){
        super("Duck \uD83E\uDD86", 1,200,4,0.15);
        plantEatChances.put(Plant.class,100);
        animalEatChances.put(Caterpillar.class,90);
    }

    @Override
    protected Animal createNewAnimal() {
        return new Duck();
    }

    @Override
    public void eat_plant(Plant[][] plants) {

    }

    @Override
    public void eat_animal(Animal[][] grid) {

    }
}


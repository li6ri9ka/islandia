package Predator;
import Animal.*;
import Interfaces.Predator;
import Herbivores.*;


public class Wolf extends Animal implements Predator {

    public Wolf() {
        super("Wolf \uD83D\uDC3A", 50,30,3,8);
        animalEatChances.put(Rabbit.class, 60);
        animalEatChances.put(Horse.class, 10);
        animalEatChances.put(Deer.class, 15);
        animalEatChances.put(Mouse.class, 80);
        animalEatChances.put(Goat.class, 60);
        animalEatChances.put(Sheep.class, 70);
        animalEatChances.put(Hog.class, 15);
        animalEatChances.put(Buffalo.class, 10);
        animalEatChances.put(Duck.class, 40);
    }

    protected Animal createNewAnimal() {
        return new Wolf();
    }

    @Override
    public void eat_animal(Animal[][] grid) {

    }
}

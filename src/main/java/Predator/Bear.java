package Predator;
import Animal.*;
import Interfaces.Predator;
import Herbivores.*;

public class Bear extends Animal implements Predator{

    public Bear() {
        super("Bear \uD83D\uDC3B",500,5,2,80);
        animalEatChances.put(Snake.class,80);
        animalEatChances.put(Rabbit.class,80);
        animalEatChances.put(Horse.class, 40);
        animalEatChances.put(Deer.class, 80);
        animalEatChances.put(Mouse.class, 90);
        animalEatChances.put(Goat.class, 70);
        animalEatChances.put(Sheep.class, 70);
        animalEatChances.put(Hog.class, 50);
        animalEatChances.put(Buffalo.class, 20);
        animalEatChances.put(Duck.class, 10);
    }
    @Override
    protected Animal createNewAnimal() {
        return new Bear();
    }

    @Override
    public void eat_animal(Animal[][] grid) {

    }
}

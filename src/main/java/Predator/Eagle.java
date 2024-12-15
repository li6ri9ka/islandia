package Predator;
import Animal.*;
import Herbivores.Rabbit;
import Interfaces.Predator;
import Herbivores.Duck;
import Herbivores.Mouse;


public class Eagle extends Animal implements Predator{
    public Eagle() {
        super("Eagle \uD83E\uDD85",6,20,3,1);
        animalEatChances.put(Fox.class,10);
        animalEatChances.put(Rabbit.class,90);
        animalEatChances.put(Mouse.class, 90);
        animalEatChances.put(Duck.class, 80);
    }

    @Override
    protected Animal createNewAnimal() {
        return new Eagle();
    }

    @Override
    public void eat_animal(Animal[][] grid) {

    }
}

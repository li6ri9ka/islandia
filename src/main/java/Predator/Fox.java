package Predator;
import Animal.*;
import Interfaces.Predator;
import Herbivores.Rabbit;
import Herbivores.Mouse;
import Herbivores.Duck;
import Herbivores.Caterpillar;


public class Fox extends Animal implements Predator{
    public Fox() {
        super("Fox \uD83E\uDD8A",8,30,2,2);
        animalEatChances.put(Rabbit.class, 70);
        animalEatChances.put(Mouse.class, 90);
        animalEatChances.put(Duck.class, 60);
        animalEatChances.put(Caterpillar.class, 40);
    }

    @Override
    protected Animal createNewAnimal() {
        return new Fox();
    }

    @Override
    public void eat_animal(Animal[][] grid) {

    }
}


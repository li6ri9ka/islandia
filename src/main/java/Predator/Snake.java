package Predator;
import Animal.*;
import Interfaces.Predator;
import Herbivores.Mouse;
import Herbivores.Rabbit;
import Herbivores.Duck;



    public class Snake extends Animal implements Predator{
        public Snake() {
            super("Snake \uD83D\uDC0D", 15,30,1,3);
            animalEatChances.put(Rabbit.class, 20);
            animalEatChances.put(Fox.class, 15);
            animalEatChances.put(Mouse.class, 40);
            animalEatChances.put(Duck.class, 10);
        }

        @Override
        protected Animal createNewAnimal() {
            return new Snake();
        }

        @Override
        public void eat_animal(Animal[][] grid) {

        }

    }


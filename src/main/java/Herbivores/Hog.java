package Herbivores;
import Animal.*;
import Interfaces.*;
import Plant.*;
import Herbivores.Mouse;

public class Hog extends Animal implements Herbivore,Predator{
    public Hog(){
        super("Hog \uD83D\uDC17",400,50,2,50);
        animalEatChances.put(Mouse.class,50);
        plantEatChances.put(Plant.class,100);
        animalEatChances.put(Caterpillar.class,90);
    }

    @Override
    protected Animal createNewAnimal() {
        return new Hog();
    }

    @Override
    public void eat_plant(Plant[][] plants) {

    }

    @Override
    public void eat_animal(Animal[][] grid) {

    }
}

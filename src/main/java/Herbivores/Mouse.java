package Herbivores;
import Animal.*;
import Interfaces.*;
import Plant.*;

public class Mouse extends Animal implements Herbivore,Predator{
    public Mouse(){
        super("Mouse \uD83D\uDC01",0.05,500,1,0.01);
        plantEatChances.put(Plant.class,100);
        animalEatChances.put(Caterpillar.class,90);
    }
    @Override
    protected Animal createNewAnimal() {
        return new Mouse();
    }

    @Override
    public void eat_plant(Plant[][] plants) {

    }

    @Override
    public void eat_animal(Animal[][] grid) {

    }
}

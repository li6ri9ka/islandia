package Location;
import Animal.*;
import  Plant.*;

import java.util.concurrent.CopyOnWriteArrayList;

public class Location {
    private CopyOnWriteArrayList<Animal> animals;
    private CopyOnWriteArrayList<Plant> plants;

    public Location() {
        this.animals = new CopyOnWriteArrayList<>();
        this.plants = new CopyOnWriteArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public void addPlant(Plant plant) {
        if (plants.size() < plant.getMAX_PLANTS()) {
            plants.add(plant);
        }
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public CopyOnWriteArrayList<Animal> getAnimals() {
        return animals;
    }

    public CopyOnWriteArrayList<Plant> getPlants() {
        return plants;
    }
}
package Generates;

import java.util.Random;
import Animal.*;
import Plant.*;
import Location.*;

public class Generates {
    private static final Random random = new Random();

    static void generateAnimal(Location[][] grid, Class<? extends Animal> animalClass, int count) {
        for (int i = 0; i < count; i++) {
            int x, y;
            do {
                x = random.nextInt(Simulation.getGridSize());
                y = random.nextInt(Simulation.getGridSize());
            } while (grid[x][y].getAnimals().size() >= getMaxAnimal(animalClass));

            try {
                Animal animal = animalClass.getDeclaredConstructor().newInstance();
                animal.setCoordinates(x, y);
                grid[x][y].addAnimal(animal);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    static void generatePlant(Location[][] grid, Class<? extends Plant> plantClass, int count) {
        int gridSize = Simulation.getGridSize();
        if (gridSize <= 0) {
            throw new IllegalArgumentException("Grid size must be greater than 0");
        }

        for (int i = 0; i < count; i++) {
            int x, y;
            do {
                x = random.nextInt(gridSize);
                y = random.nextInt(gridSize);
            } while (grid[x][y].getAnimals().size() >= getMaxPlant(plantClass));

            try {
                Plant plant = plantClass.getDeclaredConstructor().newInstance();
                plant.setCoordinates(x, y);
                grid[x][y].addPlant(plant);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static int getMaxAnimal(Class<? extends Animal> animalClass) {
        try {
            Animal animal = animalClass.getDeclaredConstructor().newInstance();
            return animal.getMaxAnimal();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static int getMaxPlant(Class<? extends Plant> plantClass) {
        try {
            Plant plant = plantClass.getDeclaredConstructor().newInstance();
            return plant.getMAX_PLANTS();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
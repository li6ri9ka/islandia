package Animal;
import Location.*;
import Interfaces.*;
import Plant.Plant;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;

public abstract class Animal {
    private String animalType;
    private int position_x;
    private int position_y;
    protected double weight;
    protected double maxFood;
    protected int maxAnimal;
    protected double currentFood;
    protected int speed;
    protected Random random;
    private boolean isDead;
    private int reproductionDelay;
    private int reproductionDelayCounter;
    protected Map<Class<Plant>, Integer> plantEatChances;
    protected Map<Class<? extends Animal>, Integer> animalEatChances;
    private static final Logger logger = Logger.getLogger(Animal.class.getName());

    public Animal(String animalType,double weight,int maxAnimal,int speed,double maxFood) {
        this.animalType = animalType;
        this.weight = weight;
        this.maxFood = maxFood;
        this.maxAnimal = maxAnimal;
        this.speed = speed;
        this.currentFood = 0;
        this.random = new Random();
        this.isDead = false;
        this.reproductionDelay = 20;
        this.reproductionDelayCounter = 0;
        this.plantEatChances = new HashMap<Class<Plant>, Integer>();
        this.animalEatChances = new HashMap<>();
    }


    public String getAnimalType() {
        return animalType;
    }

    public int getPosition_x() {
        return position_x;
    }

    public int getPosition_y() {
        return position_y;
    }

    public void setCoordinates(int position_x, int position_y) {
        this.position_x = position_x;
        this.position_y = position_y;
    }

    public void move(Location[][] grid) {
        if (isDead) return;
        logger.log(Level.INFO, "Moving " + animalType + " at " + position_x + "," + position_y);
        int newX, newY;
        do {
            newX = getPosition_x() + random.nextInt((speed * 2) + 1) - speed;
            newY = getPosition_y() + random.nextInt((speed * 2) + 1) - speed;
        } while (!isValidMoveForAnimal(newX, newY, grid));

        grid[position_x][position_y].removeAnimal(this);
        position_x = newX;
        position_y = newY;
        grid[position_x][position_y].addAnimal(this);
        currentFood -= maxFood * 0.01;
        logger.log(Level.INFO, animalType + " located: " + position_x + ":" + position_y);
        starve_to_death();
    }

    private boolean isValidMoveForAnimal(int newX, int newY, Location[][] grid) {
        return newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY].getAnimals().size() < maxAnimal;
    }

    public void starve_to_death() {
        if (currentFood<-maxFood){
            eaten();
            logger.log(Level.INFO, "_____________________________");
            logger.log(Level.INFO, animalType + " hungry died...");
        }
    }
    public boolean isDead() {
        return isDead;
    }

    public void eaten() {
        this.isDead = true;
    }

    public void died() {
        if (isDead) {
            logger.log(Level.INFO, animalType + " Died");
        }
    }

    public List<Animal> reproduce(Location[][] grid) {
        List<Animal> newAnimals = new ArrayList<>();
        reproductionDelayCounter++;

        if (reproductionDelayCounter >= reproductionDelay) {
            List<Animal> animals = grid[position_x][position_y].getAnimals();
            int count = 0;
            for (Animal animal : animals) {
                if (animal.getClass() == this.getClass()) {
                    count++;
                }
            }
            if (count >= 2 && random.nextBoolean()) {
                Animal newAnimal = createNewAnimal();
                newAnimal.setCoordinates(position_x, position_y);
                newAnimals.add(newAnimal);
                logger.log(Level.INFO, "_____________________________");
                logger.log(Level.INFO, animalType + " reproduced.");
                reproductionDelayCounter = 0;
            }
        }
        return newAnimals;
    }

    protected abstract Animal createNewAnimal();

    public void eat(Location location) {
        if (isDead()) return;

        if (this instanceof Herbivore) {
            eat_plant(location);
        }else if (this instanceof Predator) {
            eat_animal(location);
        }

        starve_to_death();
    }

    protected void eat_plant(Location location) {
        if (currentFood > maxFood * 0.5) return;

        List<Plant> plants = location.getPlants();
        for (int i = 0; i < plants.size(); i++) {
            Plant plant = plants.get(i);
            if (canEatPlant(plant)) {
                int chance = getEatChance(plant);
                if (random.nextInt(100) < chance) {
                    logger.log(Level.INFO, "_____________________________");
                    logger.log(Level.INFO, animalType + " ate a " + plant.name + ".");
                    currentFood += plant.getWeight();
                    if (currentFood > maxFood) currentFood = maxFood;
                    plant.eaten();
                    location.removePlant(plant);
                } else {
                    logger.log(Level.INFO, "_____________________________");
                    logger.log(Level.INFO, animalType + " failed to eat a " + plant.name + ".");
                }
            }
        }
    }

    protected void eat_animal(Location location) {
        if (currentFood == maxFood) return;

        List<Animal> animals = location.getAnimals();
        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            if (animal != null && animal != this && !animal.isDead() && canEatAnimal(animal)) {
                int chance = getEatChance(animal);
                if (random.nextInt(100) < chance) {
                    logger.log(Level.INFO, "_____________________________");
                    logger.log(Level.INFO, animalType + " ate a " + animal.animalType + ".");
                    logger.log(Level.INFO, "_____________________________");
                    currentFood += animal.weight;
                    if (currentFood > maxFood) currentFood = maxFood;
                    animal.eaten();
                    animal.died();
                    location.removeAnimal(animal);
                } else {
                    logger.log(Level.INFO, "_____________________________");
                    logger.log(Level.INFO, animalType + " failed to eat a " + animal.getAnimalType() + ".");
                }
            }
        }
    }

    protected boolean canEatPlant(Plant plant) {
        return plantEatChances.containsKey(plant.getClass());
    }

    protected boolean canEatAnimal(Animal animal) {
        return animalEatChances.containsKey(animal.getClass());
    }

    protected int getEatChance(Object food) {
        if (food instanceof Plant) {
            return plantEatChances.getOrDefault(food.getClass(), 0);
        } else if (food instanceof Animal) {
            return animalEatChances.getOrDefault(food.getClass(), 0);
        }
        return 0;
    }

    public int getMaxAnimal() {
        return maxAnimal;
    }
}

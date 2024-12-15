package Generates;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import Location.*;
import Animal.*;
import Plant.*;
import Herbivores.*;
import Predator.*;

public class Simulation extends Application {
    private static int GRID_SIZE = 20;
    private static final int CELL_SIZE = 50;
    Location[][] grid;
    Label[][] labels;
    private static final long DELAY = 1_000_000_000L;
    private long lastUpdate = 0;
    protected Scanner scanner = new Scanner(System.in);
    private ExecutorService executorService;

    public static void main(String[] args) {
        launch(args);
    }

    public static void setGridSize(int gridSize) {
        GRID_SIZE = gridSize;
    }



    @Override
    public void start(Stage primaryStage) {
        grid = new Location[GRID_SIZE][GRID_SIZE];
        labels = new Label[GRID_SIZE][GRID_SIZE];

        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, GRID_SIZE * CELL_SIZE, GRID_SIZE * CELL_SIZE);
        System.out.println("Введите количество животных для этого класса:");
        int count = scanner.nextInt();
        scanner.nextLine();
        initializeGrid(gridPane);

                Generates.generateAnimal(grid, Fox.class, count);
                Generates.generateAnimal(grid, Bear.class, count);
                Generates.generateAnimal(grid, Buffalo.class, count);
                Generates.generateAnimal(grid, Caterpillar.class, count);
                Generates.generateAnimal(grid, Deer.class, count);
                Generates.generateAnimal(grid, Duck.class, count);
                Generates.generateAnimal(grid, Eagle.class, count);
                Generates.generateAnimal(grid, Goat.class, count);
                Generates.generateAnimal(grid, Hog.class, count);
                Generates.generateAnimal(grid, Horse.class, count);
                Generates.generateAnimal(grid, Mouse.class, count);
                Generates.generateAnimal(grid, Rabbit.class, count);
                Generates.generateAnimal(grid, Sheep.class, count);
                Generates.generateAnimal(grid, Snake.class, count);
                Generates.generateAnimal(grid, Wolf.class, count);

        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= DELAY) {
                    moveAnimals(grid);
                    eatAnimals(grid);
                    checkDeadAnimals(grid);
                    checkEatenPlants(grid);
                    reproduceAnimals(grid);

                    updateGrid(gridPane);

                    lastUpdate = now;
                }
            }
        };
        timer.start();

        primaryStage.setTitle("Island Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    private void initializeGrid(GridPane gridPane) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Rectangle rect = new Rectangle(CELL_SIZE, CELL_SIZE, Color.GREEN);
                Label label = new Label();
                labels[i][j] = label;
                gridPane.add(rect, j, i);
                gridPane.add(label, j, i);
                grid[i][j] = new Location();
            }
        }
    }

    private void moveAnimals(Location[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int finalI = i;
                int finalJ = j;
                executorService.submit(() -> {
                    CopyOnWriteArrayList<Animal> animals = grid[finalI][finalJ].getAnimals();
                    if (animals != null) { // Проверка на null
                        for (Animal animal : animals) {
                            if (animal != null && !animal.isDead()) { // Проверка на null и isDead
                                animal.move(grid);
                            }
                        }
                    }
                });
            }
        }
    }

    private void eatAnimals(Location[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int finalI = i;
                int finalJ = j;
                executorService.submit(() -> {
                    synchronized (grid[finalI][finalJ]) {
                        CopyOnWriteArrayList<Animal> animals = grid[finalI][finalJ].getAnimals();
                        if (animals != null) { // Проверка на null
                            for (Animal animal : animals) {
                                if (animal != null && !animal.isDead()) { // Проверка на null и isDead
                                    animal.eat(grid[finalI][finalJ]);
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    private void checkDeadAnimals(Location[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int finalI = i;
                int finalJ = j;
                executorService.submit(() -> {
                    synchronized (grid[finalI][finalJ]) {
                        CopyOnWriteArrayList<Animal> animals = grid[finalI][finalJ].getAnimals();
                        if (animals != null) { // Проверка на null
                            animals.removeIf(animal -> animal != null && animal.isDead()); // Убираем мертвых животных из клетки
                        }
                    }
                });
            }
        }
    }

    private void reproduceAnimals(Location[][] grid) {
        List<Animal> newAnimals = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int finalI = i;
                int finalJ = j;
                executorService.submit(() -> {
                    synchronized (grid[finalI][finalJ]) {
                        CopyOnWriteArrayList<Animal> animals = grid[finalI][finalJ].getAnimals();
                        if (animals != null) { // Проверка на null
                            for (Animal animal : animals) {
                                if (animal != null && !animal.isDead()) { // Проверка на null и isDead
                                    newAnimals.addAll(animal.reproduce(grid));
                                }
                            }
                        }
                    }
                });
            }
        }
        for (Animal newAnimal : newAnimals) {
            grid[newAnimal.getPosition_x()][newAnimal.getPosition_y()].addAnimal(newAnimal);
        }
    }

    private void checkEatenPlants(Location[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int finalI = i;
                int finalJ = j;
                executorService.submit(() -> {
                    synchronized (grid[finalI][finalJ]) {
                        CopyOnWriteArrayList<Plant> plants = grid[finalI][finalJ].getPlants();
                        if (plants != null) { // Проверка на null
                            plants.removeIf(plant -> plant != null && plant.isEaten()); // Убираем съеденные растения из клетки
                        }
                    }
                });
            }
        }
    }

    private void updateGrid(GridPane gridPane) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Location location = grid[i][j];
                Label label = labels[i][j];
                if (!location.getAnimals().isEmpty()) {
                    label.setText(getEmojiForAnimal(location.getAnimals().get(0)));
                } else if (!location.getPlants().isEmpty()) {
                    label.setText("🌿");
                } else {
                    label.setText("");
                }
            }
        }
    }

    private String getEmojiForAnimal(Animal animal) {
        if (animal instanceof Wolf) return "🐺";
        if (animal instanceof Rabbit) return "🐇";
        if (animal instanceof Snake) return "\uD83D\uDC0D";
        if (animal instanceof Fox) return "\uD83E\uDD8A";
        if (animal instanceof Bear) return "\uD83D\uDC3B";
        if (animal instanceof Horse) return "\uD83D\uDC0E";
        if (animal instanceof Deer) return "\uD83E\uDD8C";
        if (animal instanceof Mouse) return "\uD83D\uDC01";
        if (animal instanceof Goat) return "\uD83D\uDC10";
        if (animal instanceof Sheep) return "\uD83D\uDC11";
        if (animal instanceof Hog) return "\uD83D\uDC17";
        if (animal instanceof Buffalo) return "\uD83D\uDC03";
        if (animal instanceof Duck) return "\uD83E\uDD86";
        if (animal instanceof Caterpillar) return "\uD83D\uDC1B";
        if (animal instanceof Eagle) return "\uD83E\uDD85";
        return "";
    }

    public static int getGridSize() {
        return GRID_SIZE;
    }
}

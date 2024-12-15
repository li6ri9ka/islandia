package Plant;

public class Plant {
    public String name = "Plant \uD83C\uDF3F";
    private int weight;
    private boolean isEaten;
    private int position_x;
    private int position_y;
    private int MAX_PLANTS = 200;

    public int getMAX_PLANTS() {
        return MAX_PLANTS;
    }

    public Plant() {
        weight = 1;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isEaten() {
        return isEaten;
    }

    public void eaten(){
        this.isEaten = true;
    };

    public void setCoordinates(int position_x, int position_y) {
        this.position_x = position_x;
        this.position_y = position_y;
    }
}


package bsu.frct.java.lab_1;

public class Cheese extends Food {
    public Cheese() {
        super("Cheese", 40);
    }

    public void consume() {
        System.out.println(this + " is eaten..");
    }

    public Integer calculateCalories() {
        return this.calories;
    }
}

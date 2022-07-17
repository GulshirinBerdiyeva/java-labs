package bsu.frct.java.lab_1;

public abstract class Food implements Consumable, Nutritious {
    String name = null;
    int calories = 0;

    public Food(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return this.calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Food) {
            return (this.name != null || ((Food) obj).name != null) && this.name.equals(((Food) obj).name);
        } else {
            return false;
        }
    }

    public String toString() {
        return this.name;
    }
}


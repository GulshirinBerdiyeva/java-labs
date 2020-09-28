package bsu.frct.java.lab_1;

public class Cocktail extends Food {
    String drink = null;
    String fruit = null;

    public Cocktail(String drink, String fruit) {
        super("Cocktail", 150);
        this.drink = drink;
        this.fruit = fruit;
    }

    public String getDrink() {
        return this.drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String getFruit() {
        return this.fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            if (!(obj instanceof Cocktail)) {
                return false;
            } else {
                return this.drink.equals(((Cocktail) obj).drink) && this.fruit.equals(((Cocktail) obj).fruit);
            }
        } else {
            return false;
        }
    }

    public String toString() {
        return super.toString() + " with '" + this.drink.toUpperCase() + "' and '" + this.fruit.toUpperCase() + "'";
    }

    public void consume() {
        System.out.println(this + " is drunk..");
    }

    public Integer calculateCalories() {
        if (this.drink.equals("milk") && this.fruit.equals("banana")) {
            return this.calories;
        }
        if (this.drink.equals("sprite") && this.fruit.equals("lime")) {
            return this.calories - 80;
        } else
            return 0;
    }
}


package bsu.frct.java.lab_1;

public class Apple extends Food {
    String size = null;

    public Apple(String size) {
        super("Apple", 35);
        this.size = size;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return obj instanceof Apple ? this.size.equals(((Apple)obj).size) : false;
        } else {
            return false;
        }
    }

    public String toString() {
        return super.toString() + " of '" + this.size.toUpperCase() + "' size";
    }

    public void consume() {
        System.out.println(this + " is eaten..");
    }

    public Integer calculateCalories() {
        if (this.size.equals("big")) {
            return this.calories;
        } else {
            return this.size.equals("small") ? this.calories - 5 : 0;
        }
    }
}

package bsu.frct.java.lab_1;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws Exception {
        Food[] breakfast = new Food[20];
        byte sort = 0;
        byte calories = 0;

        for (int i = 0; i < args.length; ++i) {
            String[] parts = args[i].split("/");

            try {
                Class myClass = Class.forName("bsu.frct.java.lab_1." + parts[0]);
                Constructor constructor;
                if (parts.length == 1) {
                    constructor = myClass.getConstructor();
                    breakfast[i] = (Food) constructor.newInstance();
                }

                if (parts.length == 2) {
                    constructor = myClass.getConstructor(String.class);
                    breakfast[i] = (Food) constructor.newInstance(parts[1]);
                }

                if (parts.length == 3) {
                    constructor = myClass.getConstructor(String.class, String.class);
                    breakfast[i] = (Food) constructor.newInstance(parts[1], parts[2]);
                }
            } catch (NoSuchMethodException | ClassNotFoundException e) {
                if (!args[i].startsWith("-")) {
                    System.out.println(args[i] + "___Couldn't be added in breakfast");
                }

                if (args[i].equals("-sort")) {
                    ++sort;
                }

                if (args[i].equals("-calories")) {
                    ++calories;
                }

                e.printStackTrace();
            }
        }

        System.out.println("\n\t___Your breakfast___");
        for (Food b : breakfast) {
            if (b != null) {
                System.out.println(b);
            }
        }


        if (sort > 0) {
            Arrays.sort(breakfast, new Comparator() {
                public int compare(Object f1, Object f2) {
                    if (f1 == null) {
                        return 1;
                    }
                    if (f2 == null) {
                        return -1;
                    } else {
                        return ((Food) f2).calculateCalories().compareTo(((Food) f1).calculateCalories());
                    }
                }
            });

            System.out.println("\n\t___Your breakfast after sort___");
            for (Food b : breakfast) {
                if (b != null) {
                    System.out.println("calories of " + b + " are " + b.calculateCalories());
                }
            }
        }


        Food[] breakfastCopy = new Food[20];
        int i = 0;
        for (Food b : breakfast) {
            if (b != null) {
                boolean temp = false;
                for (Food b2 : breakfastCopy) {
                    if (b.equals(b2)) {
                        temp = true;
                    }
                }
                if (!temp) {
                    breakfastCopy[i++] = b;
                }
            }else {
                break;
            }
        }


        System.out.println("\n\t___Amount of products___");
        for (Food b : breakfastCopy) {
            if (b != null) {
                byte count = 0;
                for (Food b2 : breakfast){
                    if (b.equals(b2)){
                        count++;
                    }
                }
                System.out.println(b + " = " + count);
            }
        }


        System.out.println("\n\t___After breakfast___");
        if (calories > 0) {
            int sum = 0;
            for (Food b : breakfast) {
                if (b != null) {
                    sum += b.getCalories();
                    b.consume();
                }
            }

            System.out.println("\n\t___Full sum calories of your breakfast = " + sum);
            System.out.println("\n\t___Have a nice day °l°___\n");
        }

    }
}


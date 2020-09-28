package bsu.frct.java.lab_1;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws Exception {
        Food[] breakfast = new Food[20];
        byte sort = 0;
        byte calories = 0;

        for(int i  = 0; i < args.length; ++i) {
            String[] parts = args[i].split("/");

            try {
                Class myClass = Class.forName("bsu.frct.java.lab_1." + parts[0]);
                Constructor constructor;
                if (parts.length == 1) {
                    constructor = myClass.getConstructor();
                    breakfast[i] = (Food)constructor.newInstance();
                }

                if (parts.length == 2) {
                    constructor = myClass.getConstructor(String.class);
                    breakfast[i] = (Food)constructor.newInstance(parts[1]);
                }

                if (parts.length == 3) {
                    constructor = myClass.getConstructor(String.class, String.class);
                    breakfast[i] = (Food)constructor.newInstance(parts[1], parts[2]);
                }
            } catch (NoSuchMethodException | ClassNotFoundException e) {
                if (!args[i].startsWith("-")) {
                    System.out.println(args[i] + "..Couldn't be added in breakfast..");
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

        System.out.println("\n\tYour breakfast...");
        for (Food b : breakfast){
            if (b != null){
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
                        return ((Food)f2).calculateCalories().compareTo(((Food)f1).calculateCalories());
                    }
                }
            });

            System.out.println("\n\tYour breakfast after sort...");
            for (Food b : breakfast){
                if (b != null){
                    System.out.println("calories of " + b + " are " + b.calculateCalories());
                }
            }
        }

        System.out.println("\n");
        if (calories > 0) {
            int sum = 0;
            for (Food b : breakfast){
                if (b != null){
                    sum += b.getCalories();
                    b.consume();
                }
            }

            System.out.println("\n\tFull sum calories of your breakfast are " + sum);
<<<<<<< HEAD
            System.out.println("Have a nice day °l°");
=======
            System.out.println("Thank you ;) \tHave a nice day °l°");
>>>>>>> e36ae46... ADDED: CommentInMainClass
        }

    }
}

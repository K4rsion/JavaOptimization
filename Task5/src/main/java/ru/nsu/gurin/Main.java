package ru.nsu.gurin;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        // Part 1
        Car car = new Car("Yellow");
        int len = stringLen("Yellow");
        String str = getFieldOfObject(car);
        changeFieldOfObject(car);
        System.out.println(car.getColor());

        // Part 2
        Random random = new Random();
        ArrayList<ValueClass> arr = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ValueClass temp = new ValueClass(random.nextInt(1000));
            arr.add(temp);
        }
        sortValue(arr);
        System.out.println(arr);

    }

    public static int stringLen(String str) {
        return str.length();
    }

    public static String getFieldOfObject(Car car) {
        return car.getColor();
    }

    public static void changeFieldOfObject(Car car) {
        car.color = "White";
    }

    public static void sortValue(ArrayList<ValueClass> objs) {
        for (int j = 0; j + 1 < objs.size(); j++) {
            for (int i = 0; i + 1 < objs.size() - j; i++) {
                if (objs.get(i).value > objs.get(i + 1).value) {
                    ValueClass temp = objs.get(i + 1);
                    objs.set(i + 1, objs.get(i));
                    objs.set(i, temp);
                }
            }
        }
    }
}

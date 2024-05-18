import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<ValueClass> arr = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ValueClass temp = new ValueClass(random.nextInt(1000));
            arr.add(temp);
        }
        sortValue(arr);
        System.out.println(arr);
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

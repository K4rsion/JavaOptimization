import nativeBean.Bean;

public class Main {
    public static void main(String[] args) {
        Bean bean = new Bean();


        // переполнение памяти
        bean.dropMemory();
        System.out.println();


        // 1Кб памяти
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Used memory in bytes before allocation: " + beforeMemory);
        bean.allocateMemory();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long afterMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Used memory in bytes after allocation: " + afterMemory);
        System.out.println("Difference in memory usage: " + (afterMemory - beforeMemory) + " bytes");
        System.out.println();


        // цепочка методов с падением программы
        bean.chainMethods();
        System.out.println();


        // длина строки
        String str = "Hello, JNI!";
        bean.setStringValue(str);
        int length = bean.stringLength(str);

        System.out.println("Длина строки в Java: " + str.length());
        System.out.println("Длина строки в C: " + length);
        System.out.println();


        // вызвать метод у объекта
        bean.callObjectMethod(bean);
        System.out.println();


        // поменять поле у объекта
        bean.setIntValue(10);
        System.out.println("inValue перед вызовом native метода:" + bean.getIntValue());
        bean.changeFieldValue(bean);
        System.out.println("inValue после вызова native метода:" + bean.getIntValue());
        System.out.println();

        // работа со структурой
        long pointer = bean.allocateStructure();
        long value = bean.getStructureValue(pointer);
        System.out.println("Значение из структуры: " + value);
        bean.freeStructure(pointer);
    }
}
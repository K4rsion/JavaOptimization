package nativeBean;

public class Bean {
    // Примитивные типы данных
    private int intValue;
    private double doubleValue;
    private boolean booleanValue;

    // Строка
    private String stringValue;

    // Ссылка на другой Bean
    private Bean otherBean;

    // Массивы примитивных типов и объектов
    private int[] intArray;
    private String[] stringArray;

    // Загрузка библиотеки
    static {
        System.loadLibrary("bean");
    }

    // Native-метод для выделения 1 КБ памяти
    public native void dropMemory();

    public native void allocateMemory();

    public native void chainMethods();

    public native int stringLength(String str);

    public native void callObjectMethod(Bean obj);

    public native void changeFieldValue(Bean obj);
    public native long allocateStructure();
    public native long getStructureValue(long pointer);
    public native void freeStructure(long pointer);

    // Конструкторы, геттеры и сеттеры
    public Bean() {
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void myMethod() {
        System.out.println("Метод объекта вызван из нативного метода");
    }
}

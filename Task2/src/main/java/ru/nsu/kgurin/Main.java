package ru.nsu.kgurin;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Bean bean1 = new Bean();
        Bean bean2 = new Bean();
        Bean bean3 = new Bean();

        bean1.setNextBean(bean2);
        bean2.setNextBean(bean3);
        bean3.setNextBean(bean1);

        Thread thread1 = new Thread(() -> {
            Singleton singleton = Singleton.getInstance();
            Bean bean = new Bean();

            while (true) {
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            Singleton singleton = Singleton.getInstance();
            Bean bean = new Bean();

            while (true) {
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread3 = new Thread(() -> {
            Singleton singleton = Singleton.getInstance();
            Bean bean = new Bean();

            while (true) {
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        if (args.length == 1 && args[0].equals("memOut")) {
            // Start the badThread
            Thread badThread = new Thread(() -> {
                List<byte[]> arrays = new ArrayList<>();
                while (true) {
                    try {
                        byte[] array = new byte[100];
                        arrays.add(array);
                    } catch (OutOfMemoryError e) {
                        System.out.println("Out of memory!");
                        break;
                    }
                }
            });
            badThread.start();
        } else {
            System.out.println("Usage: java MemoryAllocator memOut");
        }
    }
}
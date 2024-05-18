package ru.nsu.kgurin;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        ArrayList<Number> arrayList = new ArrayList<>();

        scheduler.scheduleAtFixedRate(() -> {
            long maxMemory = Runtime.getRuntime().maxMemory();
            long allocatedMemory = Runtime.getRuntime().totalMemory();
            long freeMemory = Runtime.getRuntime().freeMemory();
            long usedMemory = allocatedMemory - freeMemory;

            System.out.println(maxMemory + "  " + allocatedMemory + "  " + freeMemory + "  " + usedMemory);
        }, 0, 50, TimeUnit.MILLISECONDS);

        try {
            while (true) {
                Number n = new Number( 50);
                arrayList.add(n);
            }
        } catch (OutOfMemoryError err) {
            System.out.println("OUT OF MEMORY");
            scheduler.shutdownNow();
            System.exit(0);
        }
    }
}
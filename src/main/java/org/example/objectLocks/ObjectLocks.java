package org.example.objectLocks;

import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.Map;

public class ObjectLocks {
    public static int counter1 = 0;
    public static int counter2 = 0;
    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();


    private static void increment1() {
        synchronized (lock1) {
            System.out.println("entered the lock " + Thread.currentThread().getName());
            counter1++;
            if (counter1 % 2 == 0) {
                increment1();
            }
        }
        System.out.println("released the lock " + Thread.currentThread().getName());
    }

    private static void increment2() {
        synchronized (lock2) {
            counter2++;
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        Thread incrementor1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10_000; i++) {
                    increment1();
                }
            }
        }, "One");


        Thread incrementor2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10_000; i++) {
                    increment1();
                }
            }
        }, "Two");
        incrementor1.start();
        incrementor2.start();
        incrementor2.join();
        incrementor1.join();
        System.out.println(counter1);
        System.out.println(counter2);
        Map<String, String> map = Map.of();
    }

}

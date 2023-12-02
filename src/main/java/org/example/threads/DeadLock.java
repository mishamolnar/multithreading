package org.example.threads;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class DeadLock {

    @SneakyThrows
    public static void main(String[] args) {
        Stats s1 = new Stats();
        Stats s2 = new Stats();
        Thread t1 = new Thread(() -> {
            synchronized (s1) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                s2.increment();
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (s2) {
                s1.increment();
            }
        });
        Thread statePrinter = new Thread(() -> {
             while (true) {
                 System.out.println(t1.getState());
                 System.out.println(t2.getState());
                 try {
                     TimeUnit.MILLISECONDS.sleep(100);
                 } catch (InterruptedException e) {
                     throw new RuntimeException(e);
                 }
             }
        });
        statePrinter.start();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }



    private static class Stats {
        private Long counter = 0L;

        @SneakyThrows
        synchronized public void increment() {
            counter++;
            TimeUnit.SECONDS.sleep(5);
        }

        public Long getCounter() {
            return counter;
        }
    }

}

package org.example.conditionvars;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionCheck {
    private Lock lock = new ReentrantLock();
    private Condition[] conditions = new Condition[10];

    private Thread[] threads = new Thread[10];
    private Random random = new Random();

    public ConditionCheck() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            conditions[i] = lock.newCondition();
        }
        System.out.println("Initializing the bees");
        for (int i = 0; i < conditions.length; i++) {
            threads[i] = new Thread(new Bee(i, lock));
        }
        System.out.println("awaiting");
        for (int i = 0; i < conditions.length; i++) {
            threads[i].start();
            lock.lock();
            try {
                conditions[i].await();
                System.out.println("SOMETHING");
            } finally {
                lock.unlock();
            }
            System.out.println("State" + threads[i].getState());

        }
        System.out.println("All bees finished their work");
    }

    @SneakyThrows
    public static void main(String[] args) {
        ConditionCheck conditionCheck = new ConditionCheck();
    }

    private class Bee implements Runnable {
        int number;
        Lock lock;

        public Bee(int number, Lock lock) {
            this.number = number;
            this.lock = lock;
        }

        @Override
        @SneakyThrows
        public void run() {
            Thread.sleep(500);
            System.out.println("Bee with number " + number + " started to explore field");
            Thread.sleep(random.nextInt(1000));
            System.out.println("Bee with number " + number + " finished the work");
            lock.lock();
            try {
                conditions[number].signal();
            } finally {
                lock.unlock();
            }
        }
    }
}

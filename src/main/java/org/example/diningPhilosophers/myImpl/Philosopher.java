package org.example.diningPhilosophers.myImpl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class Philosopher implements Runnable{
    private final int number;
    private final Lock left;
    private final Lock right;
    private long lastEaten;
    private AtomicInteger counter;


    public Philosopher(int number, Lock left, Lock right) {
        this.number = number;
        this.left = left;
        this.right = right;
        this.counter = new AtomicInteger();
    }


    @Override
    public void run() {
        while (true) {
            try {
                if (!left.tryLock(10, TimeUnit.MILLISECONDS)) {
                    continue;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                if (!right.tryLock(10, TimeUnit.MILLISECONDS)) {
                    continue;
                }
                try {
                    System.out.println(String.format("Philosopher number %1$d is dinning", number));
                    Thread.sleep(150);
                    counter.incrementAndGet();
                    lastEaten = System.nanoTime();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    right.unlock();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                left.unlock();
            }
        }
    }

    public long lastEaten() {
        return System.nanoTime() - lastEaten;
    }

    public long getCounter() {
        return counter.get();
    }

    public int getNumber() {
        return number;
    }
}

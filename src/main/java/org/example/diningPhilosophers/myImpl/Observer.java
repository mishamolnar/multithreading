package org.example.diningPhilosophers.myImpl;

import java.util.List;

public class Observer implements Runnable {
    private final List<Philosopher> philosopherList;


    public Observer(List<Philosopher> philosopherList) {
        this.philosopherList = philosopherList;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            long min = Long.MAX_VALUE;
            for (Philosopher philosopher : philosopherList) {
                System.out.println(String.format("Philosopher number %1$d is starving for %2$d and have eaten for %3$d times", philosopher.getNumber(), philosopher.lastEaten(), philosopher.getCounter()));
                min = Math.min(min, philosopher.lastEaten());
            }
            if (min > 1_000_000_000 * 5) {
                System.out.println("DEADLOCK DEADLOCK DEADLOCK");
            }
        }
    }
}

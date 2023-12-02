package org.example.consumerproducer;

import java.util.ArrayList;
import java.util.List;

public class CoordinatedWorkRunner implements Runnable {

    public static void main(String [] args){
        int numberOfThreads = 200; //or any number you'd like

        List<Thread> threads = new ArrayList<>();

        Barrier barrier = new Barrier(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            threads.add(new Thread(new CoordinatedWorkRunner(barrier)));
        }

        for(Thread thread: threads) {
            thread.start();
        }
    }
    private Barrier barrier;
 
    public CoordinatedWorkRunner(Barrier barrier) {
        this.barrier = barrier;
    }
 
    @Override
    public void run() {
        try {
            task();
        } catch (InterruptedException e) {
        }
    }
 
    private void task() throws InterruptedException {
        // Performing Part 1
        System.out.println(Thread.currentThread().getName() 
                + " part 1 of the work is finished");
 
        barrier.barrier();
 
        // Performing Part2
        System.out.println(Thread.currentThread().getName() 
                + " part 2 of the work is finished");
    }
}
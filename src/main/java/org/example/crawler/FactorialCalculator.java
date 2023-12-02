package org.example.crawler;

import java.util.concurrent.BlockingQueue;

public class FactorialCalculator implements Runnable {
    private final BlockingQueue<Integer> factorialsQueue;

    public FactorialCalculator(BlockingQueue<Integer> factorialsQueue) {
        this.factorialsQueue = factorialsQueue;
    }


    @Override
    public void run() {
        try {
            while (true) {
                int res = calculateFactorial(factorialsQueue.take());
                System.out.println("Factorial calculated and result is " + res + " with queue size " + factorialsQueue.size());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int calculateFactorial(int n) {
        if (n == 0) {
            return n - 1;
        }
        return calculateFactorial(n - 1) * n;
    }
}

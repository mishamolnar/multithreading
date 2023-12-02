package org.example.crawler;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class FactorialProducer implements Runnable {
    private final BlockingQueue<Integer> factorialsQueue;
    private final Random random;

    public FactorialProducer(BlockingQueue<Integer> factorialsQueue, Random random) {
        this.factorialsQueue = factorialsQueue;
        this.random = random;
    }


    @Override
    public void run() {
        while (true) {
            try {
                while (true) {
                    produce();
                }
            } catch (Exception ignored) {
                System.out.println(ignored.toString());
            }
        }
    }

    private void produce() throws InterruptedException {
        int fact = random.nextInt(1000);
        System.out.println("Will be calculated from value " + fact + " with queue size  " + factorialsQueue.size());
        factorialsQueue.put(fact);
    }
}

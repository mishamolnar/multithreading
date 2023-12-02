package org.example.crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Tester {
    public static void main(String[] args) {
        BlockingQueue<Integer> bq = new LinkedBlockingQueue<>(20);
        List<Thread> indexers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            indexers.add(new Thread(new FactorialCalculator(bq)));
        }
        List<Thread> producers =  new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            producers.add(new Thread(new FactorialProducer(bq, new Random())));
        }
        for (Thread indexer : indexers) {
            indexer.start();
        }
        for (Thread producer : producers) {
            producer.start();
        }
    }
}

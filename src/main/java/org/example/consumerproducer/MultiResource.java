package org.example.consumerproducer;

import lombok.SneakyThrows;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultiResource {
    public static void main(String[] args) {
        MultiResource multiResource = new MultiResource();

        multiResource.init();
    }

    private void init() {
        Thread one = new Thread(new Producer());
        Thread two = new Thread(new Consumer());
        one.start();
        two.start();
    }

    private Semaphore full = new Semaphore(0);
    private Semaphore empty = new Semaphore(5);
    private Queue<Integer> queue = new ArrayDeque<>();
    private Lock lock = new ReentrantLock();

    private class Producer implements Runnable {

        @Override
        @SneakyThrows
        public void run() {
            while (true) {
                empty.acquire();
                lock.lock();
                queue.add(getNextItem());
                lock.unlock();
                full.release();
            }
        }

        private int getNextItem() throws InterruptedException {
            long time = System.currentTimeMillis() / 1000;
            System.out.println("Producing new item " + (time));
            System.out.println("Queue size is " + queue.size());
            Thread.sleep(3000);
            return (int) time;
        }
    }

    private class Consumer implements Runnable {

        @Override
        @SneakyThrows
        public void run() {
            while (true) {
                full.acquire();
                lock.lock();
                int item = queue.poll();
                lock.unlock();
                consume(item);
                empty.release();
            }
        }

        private void consume(int item) throws InterruptedException {
            System.out.println("Consuming item"  + item);
            Thread.sleep(3000);
        }
    }
}

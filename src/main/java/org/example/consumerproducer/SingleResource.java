package org.example.consumerproducer;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;

public class SingleResource {
    private int item;
    private Semaphore full;
    private Semaphore empty;

    public static void main(String[] args) {
        SingleResource resource = new SingleResource();
        resource.init();
    }

    private void init() {
        this.full = new Semaphore(0);
        this.empty = new Semaphore(1);
        this.item = 0;
        Thread cons = new Thread(new Consumer());
        Thread prod = new Thread(new Producer());
        cons.start();
        prod.start();
    }

    private class Producer implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                empty.acquire();
                item = produceNewItem(item);
                full.release();
            }
        }

        private int produceNewItem(int item) throws InterruptedException {
            System.out.println("Producing item " + (item + 1));
            Thread.sleep(3000);
            return item + 1;
        }
    }

    private class Consumer implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                full.acquire();
                consumeItem(item);
                empty.release();
            }
        }

        private void consumeItem(int item) throws InterruptedException {
            System.out.println("consuming item " + item);
            Thread.sleep(1000);
        }
    }
}

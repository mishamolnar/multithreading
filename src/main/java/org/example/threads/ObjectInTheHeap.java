package org.example.threads;

import lombok.SneakyThrows;

public class ObjectInTheHeap {

    @SneakyThrows
    public static void main(String[] args) {
        ObjectInTheHeap objectInTheHeap = new ObjectInTheHeap();
        objectInTheHeap.test();
    }

    private void test() throws InterruptedException {
        int[] referenceOne = new int[1];
        int[] referenceTwo = referenceOne;
        ThreadDecrement threadDecrement = new ThreadDecrement(referenceOne);
        ThreadIncrement threadIncrement = new ThreadIncrement(referenceTwo);
        threadIncrement.start();
        threadDecrement.start();
        threadDecrement.join();
        threadIncrement.join();
        System.out.println(referenceOne[0]);
        System.out.println(referenceTwo[0]);
    }

    private class ThreadDecrement extends Thread {
        private int[] reference;

        public ThreadDecrement(int[] reference) {
            this.reference = reference;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                reference[0]--;
            }
        }
    }

    private class ThreadIncrement extends Thread {
        private int[] reference;

        public ThreadIncrement(int[] reference) {
            this.reference = reference;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                reference[0]++;
            }
        }
    }
}

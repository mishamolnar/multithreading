package org.example.threads;

public class DealingWithThreads {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> System.out.println("We are in new thread " + Thread.currentThread().getName()));

        //Thread.sleep(1000);
        System.out.println("before starting the thread we are in: " + Thread.currentThread().getName());
        thread.start();
        //Thread.sleep(0);
        System.out.println("after stating th thread we are in: " + Thread.currentThread().getName());
        System.out.println("after stating th thread we are in: " + Thread.currentThread().getName());
        System.out.println("after stating th thread we are in: " + Thread.currentThread().getName());
        System.out.println("after stating th thread we are in: " + Thread.currentThread().getName());
        System.out.println("after stating th thread we are in: " + Thread.currentThread().getName());
        System.out.println("after stating th thread we are in: " + Thread.currentThread().getName());
        System.out.println("after stating th thread we are in: " + Thread.currentThread().getName());
        System.out.println("after stating th thread we are in: " + Thread.currentThread().getName());
        System.out.println("after stating th thread we are in: " + Thread.currentThread().getName());
        System.out.println("after stating th thread we are in: " + Thread.currentThread().getName());
        System.out.println("after stating th thread we are in: " + Thread.currentThread().getName());
        System.out.println("after stating th thread we are in: " + Thread.currentThread().getName());


    }
}

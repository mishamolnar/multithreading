package org.example.threads;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class ThreadSameName {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(ThreadSameName::run, "SAME");
        Thread thread1 = new Thread(ThreadSameName::runTwo, "SAME");
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
    }

    @SneakyThrows
    private static void run() {
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("hello from first thread");
        }
    }

    @SneakyThrows
    private static void runTwo() {
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("hello from SECOOOOOOND thread");
        }
    }
}

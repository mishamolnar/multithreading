package org.example.threadlocal;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadLocalTest {
    private static final Object notifier = new Object();
    private static boolean canProceed;

    public static void main(String[] args) {
        canProceed = false;
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(new ThreadContainer("_____" + i + "______")));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        System.out.println("All thread were started");
        canProceed = true;
        synchronized (notifier) {
            notifier.notifyAll();
        }
    }

    private static class ThreadContainer implements Runnable {
        private Random random;
        private String value;
        public ThreadContainer(String value) {
            random = new Random();
            this.value = value;
        }


        @SneakyThrows
        @Override
        public void run() {
            Thread.sleep(random.nextInt(2000));
            ThreadLocalPrinter.registerThread(value);
            while (!ThreadLocalTest.canProceed) {
                try {
                    ThreadLocalTest.notifier.wait();
                } catch (InterruptedException ignored) {

                }
            }
            Thread.sleep(random.nextInt(2000));
            ThreadLocalPrinter.printThreadLocalContent();
        }
    }


    private static class ThreadLocalPrinter {
        private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

        private static void registerThread(String value) {
            System.out.printf("Registering thread local with value %s \n", value);
            threadLocal.set(value);
        }


        private static String printThreadLocalContent() {
            String value = threadLocal.get();
            System.out.printf("This is thread with value %s running right now \n", value);
            return value;
        }
    }
}

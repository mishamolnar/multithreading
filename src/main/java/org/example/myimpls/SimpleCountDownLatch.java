package org.example.myimpls;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Phaser;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleCountDownLatch {
    private int count;
    private Lock lock;
    private Condition condition;

    public SimpleCountDownLatch(int count) {
        this.count = count;
        this.lock = new ReentrantLock();
        if (count < 0) {
            throw new IllegalArgumentException("count cannot be negative");
        }
    }

    /**
     * Causes the current thread to wait until the latch has counted down to zero.
     * If the current count is already zero then this method returns immediately.
    */
    public synchronized void await() throws InterruptedException {
        lock.lock();
        try {
            while (count > 0) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     *  Decrements the count of the latch, releasing all waiting threads when the count reaches zero. 
     *  If the current count already equals zero then nothing happens.
     */
    public synchronized void countDown() {
        lock.lock();
        try {
            if (count > 0) {
                count--;
            }
            if (count == 0) {
                condition.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Returns the current count.
    */
    public int getCount() {
        return this.count;
    }
}
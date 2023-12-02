package org.example.objectLocks;

import lombok.SneakyThrows;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerProducerReentrantLock {
    Lock lock = new ReentrantLock();
    Condition full = lock.newCondition();
    Condition empty = lock.newCondition();
    Deque<Integer> list = new ArrayDeque<>();
    Random random = new Random();
    private final int MAX_SIZE = 10;
    private final int MIN_SIZE = 0;


    @SneakyThrows
    private void add(Integer value) {
        Thread.sleep(random.nextInt(480));
        lock.lock();
        try {
            while (list.size() == MAX_SIZE) {
                System.out.println("waiting for element to be consumed");
                full.await();
            }
            list.addLast(value);
            System.out.println("Produced element, now size is " + list.size());
            empty.signal();
        } finally {
            lock.unlock();
        }
    }

    @SneakyThrows
    public Integer get() {
        Thread.sleep(random.nextInt(500));
        lock.lock();
        try {
            while (list.size() == MIN_SIZE) {
                System.out.println("waiting for new element to be produced");
                empty.await();
            }
            Integer buff = list.pollFirst();
            System.out.println("Consumed element, now size is " + list.size());
            full.signal();
            return buff;
        } finally {
            lock.unlock();
        }
    }


    @SneakyThrows
    public static void main(String[] args) {
//        ConsumerProducerReentrantLock consumerProducerReentrantLock = new ConsumerProducerReentrantLock();
//        Random random = new Random();
//        //slow prouducer
//        Thread producer = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 1000; i++) {
//                    consumerProducerReentrantLock.add(random.nextInt(100));
//                }
//            }
//        });
//        Thread consumer = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 1000; i++) {
//                    consumerProducerReentrantLock.get();
//                }
//            }
//        });
//
//        producer.start();
//        consumer.start();
//        producer.join();
//        consumer.join();
        System.out.println("INSERT INTO ULY_BLOCK(id, volume_id, type, location, creation_date, nr, hash, ALLOCATED_SIZE, allocated_file_count, USED_SIZE, NOT_USED_SIZE, WASTED_SIZE, PACK_SIZE, USED_FILE_COUNT, NOT_USED_FILE_COUNT, WASTED_FILE_COUNT, status, status_modification_date) VALUES (20000,74,'VM-BLOCK','/1000/1000_r10_2008/20121114-143950/4/','2012-11-14',4,'0xC6F3EF0D5674BEECD8A5F9FEB01D665C829A77A',12,2,0,0,0,11591,0,0,0,'Disposed','2014-09-09');".substring(326));
        ;
    }

}

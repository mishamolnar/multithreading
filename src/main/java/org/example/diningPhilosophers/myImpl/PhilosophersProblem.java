package org.example.diningPhilosophers.myImpl;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PhilosophersProblem {
    public static void main(String[] args) {
        Lock zeroStick = new ReentrantLock();
        Lock firstStick = new ReentrantLock();
        Lock secondStick = new ReentrantLock();
        Lock thirdStick = new ReentrantLock();
        Lock fourthStick = new ReentrantLock();

        Philosopher zero = new Philosopher(1, fourthStick, zeroStick);
        Philosopher first = new Philosopher(2, zeroStick, firstStick);
        Philosopher second = new Philosopher(3, firstStick, secondStick);
        Philosopher third = new Philosopher(4, secondStick, thirdStick);
        Philosopher fourth = new Philosopher(5, thirdStick, fourthStick);

        Thread observer = new Thread(new Observer(List.of(zero, first, second, third, fourth)));
        observer.setDaemon(true);
        observer.start();

        Thread thread1 = new Thread(zero);
        Thread thread2 = new Thread(first);
        Thread thread3 = new Thread(second);
        Thread thread4 = new Thread(fourth);
        Thread thread5 = new Thread(third);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}

package com.scoful.demo.otherBasicDemo.jucDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 简单的重入锁使用案例
 *
 * @author scoful
 * @date 2021/11/6 19:46
 */
public class ReentrantLockDemo implements Runnable {
    public static final Lock lock = new ReentrantLock();
    public static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        Thread t1 = new Thread(demo);
        Thread t2 = new Thread(demo);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            lock.lock();
            try {
                i++;
            } finally {
                lock.unlock();
            }
        }
    }
}

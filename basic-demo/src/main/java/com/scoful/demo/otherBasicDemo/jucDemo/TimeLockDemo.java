package com.scoful.demo.otherBasicDemo.jucDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟锁申请等待超时，tryLock(5,TimeUnit.SECONDS)
 *
 * @author scoful
 * @date 2021/11/6 19:49
 */
public class TimeLockDemo implements Runnable {
    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        TimeLockDemo demo1 = new TimeLockDemo();
        TimeLockDemo demo2 = new TimeLockDemo();
        Thread t1 = new Thread(demo1);
        Thread t2 = new Thread(demo2);
        t1.start();
        t2.start();
    }

    @Override
    public void run() {

        try {
            if (reentrantLock.tryLock(5, TimeUnit.SECONDS)) {
                Thread.sleep(6000);
            } else {
                System.out.println("Gets lock failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (reentrantLock.isHeldByCurrentThread()) {
                reentrantLock.unlock();
            }
        }
    }
}

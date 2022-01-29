package com.scoful.demo.otherBasicDemo.jucDemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟自旋锁过程，tryLock()
 *
 * @author scoful
 * @date 2021/11/6 19:51
 */
public class TimeLockDemo2 implements Runnable {
    //重入锁ReentrantLock
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    int lock;

    public TimeLockDemo2(int lock) {
        this.lock = lock;
    }

    public static void main(String[] args) throws InterruptedException {
        TimeLockDemo2 r1 = new TimeLockDemo2(1);
        TimeLockDemo2 r2 = new TimeLockDemo2(2);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                while (true) {
                    if (lock1.tryLock()) {
                        try {
                            Thread.sleep(1000);
                        } finally {
                            lock1.unlock();
                        }
                    }

                    if (lock2.tryLock()) {
                        try {
                            System.out.println("thread " + Thread.currentThread()
                                                                 .getId() + " 执行完毕");

                            return;
                        } finally {
                            lock2.unlock();
                        }
                    }
                }

            } else {
                while (true) {
                    if (lock2.tryLock()) {
                        try {
                            Thread.sleep(1000);
                        } finally {
                            lock2.unlock();
                        }
                    }

                    if (lock1.tryLock()) {
                        try {
                            System.out.println("thread " + Thread.currentThread()
                                                                 .getId() + " 执行完毕");
                            return;
                        } finally {
                            lock1.unlock();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

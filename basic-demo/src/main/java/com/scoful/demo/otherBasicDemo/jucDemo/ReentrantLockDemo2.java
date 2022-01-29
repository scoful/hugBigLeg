package com.scoful.demo.otherBasicDemo.jucDemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 手动模拟中断线程打破死锁
 *
 * @author scoful
 * @date 2021/11/6 19:48
 */
public class ReentrantLockDemo2 implements Runnable {
    //重入锁ReentrantLock
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public ReentrantLockDemo2(int lock) {
        this.lock = lock;
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo2 r1 = new ReentrantLockDemo2(1);
        ReentrantLockDemo2 r2 = new ReentrantLockDemo2(2);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        //t2线程被中断，放弃锁申请，释放已获得的lock2，这个操作使得t1线程顺利获得lock2继续执行下去；
        //若没有此段代码，t2线程没有中断，那么会出现t1获取lock1，请求lock2，而t2获取lock2，请求lock1的相互等待死锁情况
        t2.interrupt();
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                Thread.sleep(500);
                lock2.lockInterruptibly();
                System.out.println("this is thread 1");
            } else {
                lock2.lockInterruptibly();
                Thread.sleep(500);
                lock1.lockInterruptibly();
                System.out.println("this is thread 2");
            }
        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();//释放锁
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread()
                                     .getId() + "：线程退出");
        }

    }
}

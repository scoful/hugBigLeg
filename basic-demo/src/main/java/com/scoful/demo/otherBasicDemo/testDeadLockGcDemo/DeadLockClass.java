package com.scoful.demo.otherBasicDemo.testDeadLockGcDemo;


/**
 * 死锁
 */
class DeadLockClass implements Runnable {
    public boolean flag;

    public DeadLockClass(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {
            while (true) {
                synchronized (Suo.o1) {
                    System.out.println("o1" + Thread.currentThread().getName());
                    synchronized (Suo.o2) {
                        System.out.println("o2" + Thread.currentThread().getName());
                    }
                }
            }
        } else {
            while (true) {
                synchronized (Suo.o2) {
                    System.out.println("o2" + Thread.currentThread().getName());
                    synchronized (Suo.o1) {
                        System.out.println("o1" + Thread.currentThread().getName());
                    }
                }
            }
        }
    }
}


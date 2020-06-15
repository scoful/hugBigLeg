package com.scoful.demo.otherBasicDemo.testDeadLockGcDemo;

/**
 * 用死锁来测试GC现象
 */
public class Test {
    public static void main(String[] args) {
        Thread t1 = new Thread(new DeadLockClass(true));
        Thread t2 = new Thread(new DeadLockClass(false));
        t1.start();
        t2.start();
    }
}


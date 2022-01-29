package com.scoful.demo.otherBasicDemo.jucDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量(Semaphore)为多线程协作提供了更为强大的控制用法。
 *
 * 无论是内部锁Synchronized还是ReentrantLock，一次都只允许一个线程访问资源，
 * 而信号量可以多个线程访问同一资源。Semaphore是用来保护一个或者多个共享资源的访问，
 * Semaphore内部维护了一个计数器，其值为可以访问的共享资源的个数。
 * 一个线程要访问共享资源，先获得信号量，如果信号量的计数器值大于1，
 * 意味着有共享资源可以访问，则使其计数器值减去1，再访问共享资源。
 * 如果计数器值为0,线程进入休眠。当某个线程使用完共享资源后，释放信号量，
 * 并将信号量内部的计数器加1，之前进入休眠的线程将被唤醒并再次试图获得信号量。
 * <p>
 * <p>
 * 实例如下：这里我们模拟10个人去银行存款，但是该银行只有两个办公柜台，
 * 有空位则上去存钱，没有空位则只能去排队等待。最后输出银行总额
 *
 * @author scoful
 * @date 2021/11/6 19:58
 */
public class SemaphoreThread {
    private int customer;

    public SemaphoreThread() {
        customer = 0;
    }

    public static void main(String[] args) {
        SemaphoreThread test = new SemaphoreThread();
        test.useThread();
    }

    /**
     * 建立线程，调用内部类，开始存钱
     */
    public void useThread() {
        Bank bank = new Bank();
        // 定义2个新号量
        Semaphore semaphore = new Semaphore(2);
        // 建立一个缓存线程池
        ExecutorService es = Executors.newCachedThreadPool();
        // 建立10个线程
        for (int i = 0; i < 10; i++) {
            // 执行一个线程
            es.submit(new Thread(new NewThread(bank, semaphore)));
        }
        // 关闭线程池
        es.shutdown();

        // 从信号量中获取两个许可，并且在获得许可之前，一直将线程阻塞
        semaphore.acquireUninterruptibly(2);
        System.out.println("到点了，工作人员要吃饭了");
        // 释放两个许可，并将其返回给信号量
        semaphore.release(2);
    }

    /**
     * 银行存钱类
     */
    class Bank {
        private int account = 100;

        public int getAccount() {
            return account;
        }

        public void save(int money) {
            account += money;
        }
    }

    /**
     * 线程执行类，每次存10块钱
     */
    class NewThread implements Runnable {
        private Bank bank;
        private Semaphore semaphore;

        public NewThread(Bank bank, Semaphore semaphore) {
            this.bank = bank;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            int tempCustomer = customer++;
            if (semaphore.availablePermits() > 0) {
                System.out.println("客户" + tempCustomer + "启动，进入银行,有位置立即去存钱");
            } else {
                System.out.println("客户" + tempCustomer + "启动，进入银行,无位置，去排队等待等待");
            }
            try {
                semaphore.acquire();
                bank.save(10);
                System.out.println(tempCustomer + "银行余额为：" + bank.getAccount());
                Thread.sleep(1000);
                System.out.println("客户" + tempCustomer + "存钱完毕，离开银行");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}

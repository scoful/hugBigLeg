package com.scoful.demo.otherBasicDemo.jucDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁模拟
 * 读线程完全并行，而写会阻塞读
 * <p>
 * 把标注//可重入锁注释下的那行代码打开(有2行)，那么所有的读和写线程都必须相互等待，耗时翻好几倍
 *
 * @author scoful
 * @date 2021/11/6 20:05
 */
public class ReadAndWriteLock {
    private static ReentrantLock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;

    public static void main(String[] args) throws InterruptedException {
        final ReadAndWriteLock demo = new ReadAndWriteLock();
        demo.setValue(0);
        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    //读锁
                    demo.handleRead(readLock);
                    //可重入锁
//                    demo.handleRead(lock);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    //写锁
                    demo.handleWrite(readLock, (int) (Math.random() * 1000));
                    //可重入锁
//                    demo.handleWrite(lock, (int) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        ExecutorService exec = new ThreadPoolExecutor(0, 200,
                0, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        ;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 18; i++) {
            exec.execute(readRunnable);
        }

        for (int i = 0; i < 18; i++) {
            exec.execute(writeRunnable);
        }
        exec.shutdown();
        exec.awaitTermination(60, TimeUnit.MINUTES);
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");

    }

    public ReadAndWriteLock setValue(int value) {
        this.value = value;
        return this;
    }

    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            //模拟读操作
            lock.lock();
            System.out.println("thread:" + Thread.currentThread()
                                                 .getId() + " value:" + value);
            Thread.sleep(1000);
            return value;
        } finally {
            lock.unlock();
        }
    }

    public Object handleWrite(Lock lock, int index) throws InterruptedException {
        try {
            //模拟写操作
            lock.lock();
            value = index;
            Thread.sleep(1000);
            System.out.println("thread:" + Thread.currentThread()
                                                 .getId() + " value:" + value);
            return value;

        } finally {
            lock.unlock();
        }
    }
}

/**
 * 官方读写锁案例
 * <p>
 * 多个线程同时访问该缓存对象时，都加上当前对象的读锁，之后其中某个线程优先查看data数据是否为空。【加锁顺序序号：1 】
 * 当前查看的线程，如果发现没有值则释放读锁，然后立即加上写锁，准备写入缓存数据。（进入写锁的前提是当前没有其他线程的读锁或者写锁）【加锁顺序序号：2和3 】
 * 为什么还会再次判断是否为空值（!cacheValid）是因为第二个、第三个线程获得读的权利时也是需要判断是否为空，否则会重复写入数据。
 * 写入数据后先进行读锁的降级后再释放写锁。【加锁顺序序号：4和5】
 * 最后数据数据返回前释放最终的读锁。【加锁顺序序号：6 】
 */
class CachedData {
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    Object data;
    volatile boolean cacheValid;

    public void processCachedData() {
        rwl.readLock()
           .lock();//1
        if (!cacheValid) {
            // Must release read lock before acquiring write lock
            rwl.readLock()
               .unlock();//2
            rwl.writeLock()
               .lock();//3
            try {
                // Recheck state because another thread might have,acquired write lock and changed state before we did.
                if (!cacheValid) {
//                    data = ...
                    cacheValid = true;
                }
                // 在释放写锁之前通过获取读锁降级写锁(注意此时还没有释放写锁)
                rwl.readLock()
                   .lock();//4
            } finally {
                // 释放写锁而此时已经持有读锁
                rwl.writeLock()
                   .unlock();//5
            }
        }

        try {
//            use(data);
        } finally {
            rwl.readLock()
               .unlock();//6
        }
    }
}
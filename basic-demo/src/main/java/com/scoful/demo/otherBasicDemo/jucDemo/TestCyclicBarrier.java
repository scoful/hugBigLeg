package com.scoful.demo.otherBasicDemo.jucDemo;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环栅栏CyclicBarrier
 * <p>
 * 字面意思循环栅栏，栅栏就是一种障碍物。这里就是内存屏障。
 * 通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
 * 叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。
 * CyclicBarrier比CountDownLatch 功能更强大一些，
 * CyclicBarrier可以接受一个参数作为barrierAction。
 * 所谓barrierAction就是当计算器一次计数完成后，系统会执行的动作。
 * CyclicBarrier强调的是n个线程，大家相互等待，只要有一个没完成，
 * 所有人都得等着。（这种思想在高性能计算最为常见，
 * GPU计算中关于也有类似内存屏障的用法）
 * <p>
 * <p>
 * 案例：10个人去旅行,规定达到一个地点后才能继续前行
 *
 * @author scoful
 * @date 2021/11/6 20:34
 */
public class TestCyclicBarrier {
    public static void main(String[] args) {
        int num = 10;
        CyclicBarrier barrier = new CyclicBarrier(num, new Runnable() {
            @Override
            public void run() {
                System.out.println("go on together!");
            }
        });
        for (int i = 1; i <= num; i++) {
            new Thread(new CyclicBarrierWorker(i, barrier)).start();
        }
    }
}

class CyclicBarrierWorker implements Runnable {
    private int id;
    private CyclicBarrier barrier;

    public CyclicBarrierWorker(int id, final CyclicBarrier barrier) {
        this.id = id;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(Math.abs(new Random().nextInt() % 10000));
            System.out.println(id + " th people wait");
            barrier.await(); // 大家等待最后一个线程到达
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
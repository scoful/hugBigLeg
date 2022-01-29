package com.scoful.demo.otherBasicDemo.jucDemo;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch模拟
 * <p>
 * 使用场景：比如对于马拉松比赛，进行排名计算，参赛者的排名，
 * 肯定是跑完比赛之后，进行计算得出的，翻译成Java识别的预发，
 * 就是N个线程执行操作，主线程等到N个子线程执行完毕之后，在继续往下执行
 *
 * @author scoful
 * @date 2021/11/6 20:21
 */
public class CountDownLatchTest {
    public static void main(String[] args) {

        int threadCount = 10;

        final CountDownLatch latch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {

            new Thread(new Runnable() {

                @Override
                public void run() {
                    System.out.println("线程" + Thread.currentThread()
                                                    .getId() + "开始出发");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("线程" + Thread.currentThread()
                                                    .getId() + "已到达终点");

                    latch.countDown();
                }
            }).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("10个线程已经执行完毕！开始计算排名");
    }
}

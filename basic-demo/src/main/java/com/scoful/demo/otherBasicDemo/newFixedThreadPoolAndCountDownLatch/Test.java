package com.scoful.demo.otherBasicDemo.newFixedThreadPoolAndCountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author scoful
 * @date 2020/8/5 1:29
 */
public class Test {
    public static void main(String[] args) {
        threadsCall();

    }

    //所有子线程执行完毕后执行主线程
    static void threadsCall() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        int threadTaskNum = 5;
        final CountDownLatch answers = new CountDownLatch(threadTaskNum);//同步计数器
        for (int i = 0; i < threadTaskNum; i++) {
            threadPool.execute(() -> {
                try {
                    System.out.println("线程" + Thread.currentThread()
                                                    .getName() + " 开始处理任务 ...");
                    Thread.sleep((long) (Math.random() * 5000));
                    System.out.println("线程" + Thread.currentThread()
                                                    .getName() + " 处理完毕，汇报结果！");
                    answers.countDown();
                } catch (Exception e) {
                }
            });
        }
        threadPool.shutdown();// 线程池不再接受新任务，全部任务结束后保证线程池退出运行
        try {
            answers.await();// 在所有子线程结束前保持阻塞
        } catch (InterruptedException e) {
        }
        System.out.println("线程" + Thread.currentThread()
                                        .getName() + "已收到所有汇报结果");
    }
}

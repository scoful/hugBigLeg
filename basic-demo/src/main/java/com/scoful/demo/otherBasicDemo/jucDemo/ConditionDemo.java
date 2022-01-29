package com.scoful.demo.otherBasicDemo.jucDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟条件响应
 * Conditon和ReentrantLock的组合可以让线程在合适的时间等待，
 * 或者在某一个特定的时间得到通知，继续执行
 *
 * @author scoful
 * @date 2021/11/6 19:54
 */
public class ConditionDemo {
    public static void main(String[] args) {
        //初始化可重入锁
        final Lock lock = new ReentrantLock();

        //第一个条件当屏幕上输出到3
        final Condition reachThreeCondition = lock.newCondition();
        //第二个条件当屏幕上输出到6
        final Condition reachSixCondition = lock.newCondition();

        //NumberWrapper只是为了封装一个数字，一边可以将数字对象共享，并可以设置为final
        //注意这里不要用Integer, Integer 是不可变对象
        final NumberWrapper num = new NumberWrapper();
        //初始化A线程
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                //需要先获得锁
                lock.lock();
                try {
                    System.out.println("threadA start write");
                    //A线程先输出前3个数
                    while (num.value <= 3) {
                        System.out.println(num.value);
                        num.value++;
                    }
                    //输出到3时要signal，告诉B线程可以开始了
                    reachThreeCondition.signal();
                } finally {
                    lock.unlock();
                }
                lock.lock();
                try {
                    //等待输出6的条件
                    while (num.value <= 6) {
                        reachSixCondition.await();
                    }
                    System.out.println("threadA start write");
                    //输出剩余数字
                    while (num.value <= 9) {
                        System.out.println(num.value);
                        num.value++;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();

                    while (num.value <= 3) {
                        //等待3输出完毕的信号
                        reachThreeCondition.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                try {
                    lock.lock();
                    //已经收到信号，开始输出4，5，6
                    System.out.println("threadB start write");
                    while (num.value <= 6) {
                        System.out.println(num.value);
                        num.value++;
                    }
                    //4，5，6输出完毕，告诉A线程6输出完了
                    reachSixCondition.signal();
                } finally {
                    lock.unlock();
                }
            }
        });

        //启动两个线程
        threadB.start();
        threadA.start();
    }

    static class NumberWrapper {
        public int value = 1;
    }
}

/**
 * Java API里的某个例子
 * 这个示例中BoundedBuffer是一个固定长度的集合，这个在其put操作时，
 * 如果发现长度已经达到最大长度，那么要等待notFull信号才能继续put，
 * 如果得到notFull信号会像集合中添加元素，并且put操作会发出notEmpty的信号，
 * 而在其take方法中如果发现集合长度为空，那么会等待notEmpty的信号，
 * 接受到notEmpty信号才能继续take，同时如果拿到一个元素，那么会发出notFull的信号
 */
class BoundedBuffer {
    final Lock lock = new ReentrantLock();//锁对象
    final Condition notFull = lock.newCondition();//写线程条件
    final Condition notEmpty = lock.newCondition();//读线程条件

    final Object[] items = new Object[100];//缓存队列
    int putptr/*写索引*/, takeptr/*读索引*/, count/*队列中存在的数据个数*/;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)//如果队列满了
                notFull.await();//阻塞写线程
            items[putptr] = x;//赋值
            if (++putptr == items.length) putptr = 0;//如果写索引写到队列的最后一个位置了，那么置为0
            ++count;//个数++
            notEmpty.signal();//唤醒读线程
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)//如果队列为空
                notEmpty.await();//阻塞读线程
            Object x = items[takeptr];//取值
            if (++takeptr == items.length) takeptr = 0;//如果读索引读到队列的最后一个位置了，那么置为0
            --count;//个数--
            notFull.signal();//唤醒写线程
            return x;
        } finally {
            lock.unlock();
        }
    }
}
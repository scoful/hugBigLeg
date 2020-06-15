package com.scoful.demo.otherBasicDemo.noAtomicDemo;

public class Test {
    final static int LOOP = 1000;

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Producer producer = new Producer(counter);
        Consumer consumer = new Consumer(counter);

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println(counter.getCount());
    }
}

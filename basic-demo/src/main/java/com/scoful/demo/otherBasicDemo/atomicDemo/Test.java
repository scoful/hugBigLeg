package com.scoful.demo.otherBasicDemo.atomicDemo;

public class Test {
    final static int LOOP = 10000;

    public static void main(String[] args) throws InterruptedException {
        AtomicCounter counter = new AtomicCounter();
        AtomicProducer producer = new AtomicProducer(counter);
        AtomicConsumer consumer = new AtomicConsumer(counter);

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println(counter.getInteger());
    }
}

package com.scoful.demo.otherBasicDemo.atomicDemo;

public class AtomicProducer extends Thread {

    private AtomicCounter atomicCounter;

    public AtomicProducer(AtomicCounter atomicCounter) {
        this.atomicCounter = atomicCounter;
    }

    @Override
    public void run() {
        for (int j = 0; j < Test.LOOP; j++) {
            System.out.println("producer : " + atomicCounter.getInteger());
            atomicCounter.increment();
        }
    }


}

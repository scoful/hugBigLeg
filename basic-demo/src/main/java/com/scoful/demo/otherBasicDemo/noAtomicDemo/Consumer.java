package com.scoful.demo.otherBasicDemo.noAtomicDemo;

public class Consumer extends Thread {
    Counter counter;

    public Consumer(Counter counter) {
        this.counter = counter;
    }


    @Override
    public void run() {
        for (int j = 0; j < Test.LOOP; j++) {
            counter.dec();
        }
    }

}

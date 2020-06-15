package com.scoful.demo.otherBasicDemo.noAtomicDemo;

public class Producer extends Thread {
    Counter counter;

    public Producer(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < Test.LOOP; ++i) {
            counter.add();
        }
    }
}



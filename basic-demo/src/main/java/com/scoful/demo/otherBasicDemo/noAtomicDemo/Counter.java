package com.scoful.demo.otherBasicDemo.noAtomicDemo;

public class Counter {
    int count = 0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void add() {
        count += 1;
    }

    public void dec() {
        count -= 1;
    }
}

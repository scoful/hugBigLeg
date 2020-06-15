package com.scoful.demo.otherBasicDemo.binaryTreeDemo;

/**
 * @author scoful
 * @date 2020/6/5 19:04
 */
public class Node {
    Node leftChild;
    Node rightChild;
    int data;

    //构造方法初始化
    Node(int newData) {
        leftChild = null;
        rightChild = null;
        data = newData;
    }

    @Override
    public String toString() {
        return data + "";
    }
}

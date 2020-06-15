package com.scoful.demo.otherBasicDemo.designPatternsDemo.Observer;

/**
 * @author scoful
 * @date 2020/5/21 15:16
 */
public interface Person {
    //小王和小李通过这个接口可以接收到小美发过来的消息
    void getMessage(String s);
}

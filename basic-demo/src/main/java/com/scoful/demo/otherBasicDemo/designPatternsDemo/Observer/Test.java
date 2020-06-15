package com.scoful.demo.otherBasicDemo.designPatternsDemo.Observer;

/**
 * 观察者模式
 * 对象间一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。
 *
 * @author scoful
 * @date 2020/5/21 15:30
 */
public class Test {
    public static void main(String[] args) {
        XiaoMei xiaoMei = new XiaoMei();
        LaoWang laoWang = new LaoWang();
        LaoLi laoLi = new LaoLi();
        //小王和小李在小美那里都注册了一下
        xiaoMei.addPerson(laoLi);
        xiaoMei.addPerson(laoWang);
        //小美向小王和小李发送通知
        xiaoMei.notifyPerson();
    }
}

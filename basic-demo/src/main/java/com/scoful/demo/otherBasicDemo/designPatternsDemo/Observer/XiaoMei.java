package com.scoful.demo.otherBasicDemo.designPatternsDemo.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author scoful
 * @date 2020/5/21 15:20
 */
public class XiaoMei {

    List<Person> list = new ArrayList<Person>();

    public XiaoMei() {
    }

    public void addPerson(Person person) {
        list.add(person);
    }

    //遍历list，把自己的通知发送给所有暗恋自己的人
    public void notifyPerson() {
        for (Person person : list) {
            person.getMessage("你们过来吧，谁先过来谁就能陪我一起玩儿游戏!");
        }
    }


}

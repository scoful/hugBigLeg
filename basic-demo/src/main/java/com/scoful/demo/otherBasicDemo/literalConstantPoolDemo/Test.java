package com.scoful.demo.otherBasicDemo.literalConstantPoolDemo;

/**
 * @author scoful
 * @date 2020/6/5 15:32
 */
public class Test {
    public static void main(String[] args) {
        String a = "1";
        String b = new String("1");
        System.out.println(a == b); // false 因为a一开始进进了字面量常量池，b则创建了个新的对象，地址肯定不一样

        String c = new String("1") + new String("1");
        c.intern();
        String d = "11";
        System.out.println(c == d); // true
        // c在new了2个不同的"1"对象后，用+号连接起来，本来这时候是没有进入字面量常量池，但调用了intern()函数，进入了字面量常量池,所以d这个字面量就直接调用了c的地址

        String e = new String("1") + new String("1");
        String f = "11";
        e.intern();
        System.out.println(e == f); // false 等创建完了才intern，已经没用了。

        String g = new String("1");
        g.intern();
        String h = "1";
        System.out.println(g == h); // false g本来就是新建对象了，intern没生效。

        String i = "abc" + "efg";
        String j = "abcefg";
        System.out.println(i == j); // true 两个字符串用+号连接也进入字面量常量池，i和j都在，指向同一个

    }
}

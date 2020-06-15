package com.scoful.demo.otherBasicDemo.java8DateDemo;

import java.time.LocalDate;

/**
 * @author scoful
 * @date 2020/5/26 10:33
 */
public class Test {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        System.out.printf("year is %d, month is %d%n", year, month);
    }
}

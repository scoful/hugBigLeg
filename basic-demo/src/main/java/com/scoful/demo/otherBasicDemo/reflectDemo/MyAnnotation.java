package com.scoful.demo.otherBasicDemo.reflectDemo;

import java.lang.annotation.*;

/**
 * @author scoful
 * @date 2020/5/26 3:22
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MyAnnotation {

    public String name() default "scoful";
}

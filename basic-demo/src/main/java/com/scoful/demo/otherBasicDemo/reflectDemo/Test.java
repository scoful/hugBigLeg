package com.scoful.demo.otherBasicDemo.reflectDemo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author scoful
 * @date 2020/5/26 1:50
 */
@MyAnnotation(name = "name of type")
public class Test {

    public static void main(String[] args) {
        try {
            testReflect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static void testReflect() throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class c = Class.forName("com.scoful.demo.otherBasicDemo.reflectDemo.Person");
        // 通过反射获取所有构造方法
        Constructor[] constructors = c.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.println("通过反射获取所有构造方法: " + constructor);
        }
        System.out.println("============================");
        // 通过反射获取单个默认构造方法
        Constructor declaredConstructor = c.getDeclaredConstructor();
        Constructor constructor = declaredConstructor;
        System.out.println("通过反射获取单个默认构造方法: " + constructor);
        System.out.println("============================");

        // 通过获得的构造方法新建实例
        Object newInstance = constructor.newInstance();
        System.out.println("通过获得的构造方法新建实例: " + newInstance);
        System.out.println("============================");

        // 通过反射获取单个私有构造方法
        constructor = c.getDeclaredConstructor(String.class);
        System.out.println("通过反射获取单个私有构造方法: " + constructor);
        System.out.println("============================");

        // 暴力访问
        constructor.setAccessible(true);

        // 通过获得的构造方法新建实例
        newInstance = constructor.newInstance("唐炜");
        System.out.println("通过获得的构造方法新建实例: " + newInstance);
        System.out.println("============================");

        // 通过反射获取所有成员变量
        Field[] declaredFields = c.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println("通过反射获取所有成员变量: " + field);
        }
        System.out.println("============================");

        declaredConstructor = c.getDeclaredConstructor();
        Object newInstance2 = declaredConstructor.newInstance();

        // 通过反射获取单个成员变量并赋值
        Field declaredField = c.getDeclaredField("address");
        System.out.println("通过反射获取单个成员变量并赋值: " + declaredField);

        declaredField.set(newInstance2, "汕头");
        System.out.println(newInstance2);
        System.out.println("============================");

        // 通过反射获取私有成员变量并赋值
        declaredField = c.getDeclaredField("age");
        System.out.println(declaredField);

        declaredField.setAccessible(true);
        declaredField.set(newInstance2, 20);
        System.out.println(newInstance2);
        System.out.println("============================");

        // 通过反射获取所有方法
        Method[] declaredMethods = c.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println("通过反射获取所有方法: " + method);
        }
        System.out.println("============================");

        // 通过反射获取单个方法

        declaredConstructor = c.getDeclaredConstructor();
        Object obj3 = declaredConstructor.newInstance();

        Method declaredMethod = c.getDeclaredMethod("show");
        declaredMethod.invoke(obj3);

        // 通过反射获取单个方法有入参

        declaredConstructor = c.getDeclaredConstructor();
        Object obj4 = declaredConstructor.newInstance();

        declaredMethod = c.getDeclaredMethod("getString", String.class, int.class);
        Object ob = declaredMethod.invoke(obj4, "唐炜", 100);
        System.out.println(ob);
        System.out.println("============================");

        // 通过反射获取单个私有方法

        declaredConstructor = c.getDeclaredConstructor();
        Object obj5 = declaredConstructor.newInstance();

        declaredMethod = c.getDeclaredMethod("function");
        declaredMethod.setAccessible(true);
        declaredMethod.invoke(obj5);
        System.out.println("通过反射获取单个私有方法: ");
        System.out.println("============================");


//        Class<Test> testClass = Test.class;
        // 获取类上的所有注解
        Annotation[] annotations = c.getAnnotations();
        for (Annotation annotation : annotations) {
            // 获取注解的全类名
            System.out.println("获取注解的全类名: " + annotation.annotationType().getName());
        }

        // 获取 hello() 方法
        Method method = c.getMethod("hello", new Class[]{});

        // hello() 方法上是否有 MyAnnotation 注解
        if (method.isAnnotationPresent(MyAnnotation.class)) {

            // 获得注解
            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);

            // 获取注解的内容
            System.out.println("hello方法上注解的内容：" + annotation.name());

        }

    }

    @MyAnnotation(name = "name of method")
    public String hello() {
        return "hello";
    }
}

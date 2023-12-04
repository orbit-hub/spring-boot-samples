package com.orbit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans2.xml");
//        Person bean = context.getBean(Person.class);
//        System.out.println(bean);
    }
}
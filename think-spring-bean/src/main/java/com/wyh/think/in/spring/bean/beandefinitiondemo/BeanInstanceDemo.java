package com.wyh.think.in.spring.bean.beandefinitiondemo;

import com.wyh.think.in.spring.bean.beandefinitiondemo.domain.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class BeanInstanceDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInstanceDemo.class);
        applicationContext.refresh();
        UserFactory userFactory = (UserFactory) applicationContext.getBean("userFactory");
        System.out.println(userFactory.toString());
    }

    @Bean(initMethod = "init")
    public UserFactory userFactory(){
        return new UserFactory();
    }
}

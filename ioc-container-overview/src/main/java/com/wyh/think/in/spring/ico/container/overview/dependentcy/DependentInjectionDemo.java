package com.wyh.think.in.spring.ico.container.overview.dependentcy;

import com.wyh.think.in.spring.ico.container.overview.annotaion.Super;
import com.wyh.think.in.spring.ico.container.overview.domain.User;
import com.wyh.think.in.spring.ico.container.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.Map;


public class DependentInjectionDemo {

    public static void main(String[] args) {
        // 内部bean
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:dependent-injection.xml");
        // 自定义bean
        UserRepository userRepository = beanFactory.getBean(UserRepository.class);
        System.out.println(userRepository);
        System.out.println("beanFactory is equal:" + (beanFactory == userRepository.getBeanFactory()));
        System.out.println("applicationContext is equal:" + (beanFactory == userRepository.getObjectFactory().getObject()));
        // 内建依赖
        // userRepository.getBeanFactory()

        // 依赖的来源并不只有自定义bean

    }
}

package com.wyh.think.in.spring.ico.container.overview.dependentcy;

import com.wyh.think.in.spring.ico.container.overview.annotaion.Super;
import com.wyh.think.in.spring.ico.container.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class DependentLookUpDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:dependent-lookup.xml");
        //lookUpByRealTime(beanFactory);
        lookUpByLazy(beanFactory);
//        lookUpByType(beanFactory);
//        lookUpCollectionByType(beanFactory);
//        lookUpByAnnotation(beanFactory);
    }

    // 实时查找
    public static void lookUpByRealTime(BeanFactory beanFactory){
        User user = (User)beanFactory.getBean("user");
        System.out.println(user);
    }

    public static void lookUpByLazy(BeanFactory beanFactory){
        ObjectFactory<User> objectFactory = (ObjectFactory<User>)beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延时查找:" + user);
    }

    // 根据类型查找
    public static void lookUpByType(BeanFactory beanFactory){
        User bean = beanFactory.getBean(User.class);
        System.out.println(bean);
    }

    public static void lookUpCollectionByType(BeanFactory beanFactory){
        if (beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("lookUpCollectionByType:" + beansOfType);
        }
    }

    public static void  lookUpByAnnotation(BeanFactory beanFactory){
        if (beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Object> beansWithAnnotation = listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("lookUpByAnnotation:" + beansWithAnnotation);
        }
    }
}

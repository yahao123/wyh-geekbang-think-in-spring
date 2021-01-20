package com.wyh.think.in.spring.bean.beandefinitiondemo.domain;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class UserFactory implements InitializingBean, DisposableBean {

    @PostConstruct
    public void userFactoryPostConstruct(){
        System.out.println("@PostConstruct 初始化Bean");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet 初始化Bean");
    }

    public void init(){
        System.out.println("init 初始化bean");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("@PreDestroy Bean销毁中");
    }

    @Override
    public void destroy() {
        System.out.println("DisposableBean#destroy Bean销毁中");
    }

    public void myDestroy(){
        System.out.println("myDestroy Bean销毁中");
    }
}

package com.wyh.think.in.spring.ico.container.overview.domain;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class User {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @PostConstruct
    public void init(){
        System.out.println("初始化User");
    }


    @PreDestroy
    public void destory(){
        System.out.println("销毁User");
    }
}

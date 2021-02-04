package com.wyh.think.in.spring.scope.demo;

import com.wyh.think.in.spring.ico.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @author ：WangYaHao
 * @date ：Created in 2021/2/3 1:53 下午
 */
public class ThreadLocalBeanScopeDemo {

    @Autowired
    @Qualifier("threadLocalUser")
    private User user;

    @Bean
    @Scope(ThreadLocalBeanScope.SCOPE_NAME)
    public User threadLocalUser(){
        return creatUser();
    }

    private User creatUser() {
        User user = new User();
        user.setName("wangyahao");
        user.setAge(1);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ThreadLocalBeanScopeDemo.class);
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            ThreadLocalBeanScope threadLocalBeanScope = new ThreadLocalBeanScope();
            beanFactory.registerScope(ThreadLocalBeanScope.SCOPE_NAME, threadLocalBeanScope);
        });
        applicationContext.refresh();


        ThreadLocalBeanScopeDemo bean = applicationContext.getBean(ThreadLocalBeanScopeDemo.class);
        System.out.println(Thread.currentThread().getName() + " user:" + bean.user.hashCode());

        for (int i = 0; i < 20; i++) {
            new Thread(() ->{
                User threadLocalUser = applicationContext.getBean("threadLocalUser", User.class);
                System.out.println(Thread.currentThread().getName() + " user:" + threadLocalUser.hashCode());
            }).start();
        }

        System.out.println(Thread.currentThread().getName() + " user:" + applicationContext.getBean("threadLocalUser", User.class).hashCode());

        applicationContext.close();
    }
}

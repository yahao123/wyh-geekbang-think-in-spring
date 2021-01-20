package com.wyh.think.in.spring.ico.container.overview.container;

import com.wyh.think.in.spring.ico.container.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnotationApplicationAsIocContainerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(AnnotationApplicationAsIocContainerDemo.class);
        annotationConfigApplicationContext.refresh();

        User bean = annotationConfigApplicationContext.getBean(User.class);
        System.out.println(bean);
    }

    @Bean
    public User user(){
        User user = new User();
        user.setAge(1);
        user.setName("小明");
        return user;
    }
}

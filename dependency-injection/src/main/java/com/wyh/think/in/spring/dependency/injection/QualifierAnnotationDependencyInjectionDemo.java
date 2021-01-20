package com.wyh.think.in.spring.dependency.injection;

import com.wyh.think.in.spring.dependency.injection.annotation.UserGroup;
import com.wyh.think.in.spring.ico.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    @Qualifier(value = "user")
    private User user1;

    @Autowired
    private Collection<User> userCollection;

    @Autowired
    @Qualifier
    private Collection<User> qualifierUserCollection;

    @Autowired
    @UserGroup
    private Collection<User> userGroupCollection;

    @Bean
    private User use2(){
        return createUser("2");
    }

    @Bean
    @Qualifier
    private User use3(){
        return createUser("3");
    }

    @Bean
    @Qualifier
    private User use4(){
        return createUser("4");
    }


    @Bean
    @UserGroup
    private User use5(){
        return createUser("5");
    }

    @Bean
    @UserGroup
    private User use6(){
        return createUser("6");
    }

    private User createUser(String id){
        return  new User(id,1);
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 在这里即使AnnotationDependencyFieldInjection没有标明注解，它也会成为springBean
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions("classpath:/dependent-lookup.xml");
        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionDemo bean = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        System.out.println("user1:" + bean.user1);

        System.out.println("userCollection:" + bean.userCollection);

        System.out.println("qualifierUserCollection:" + bean.qualifierUserCollection);

        System.out.println("userGroupCollection:" + bean.userGroupCollection);

        applicationContext.close();
    }

}

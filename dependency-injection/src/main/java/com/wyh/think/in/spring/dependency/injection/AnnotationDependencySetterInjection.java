package com.wyh.think.in.spring.dependency.injection;

import com.wyh.think.in.spring.ico.container.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class AnnotationDependencySetterInjection {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencySetterInjection.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String locationResource = "classpath:/dependent-lookup.xml";
        reader.loadBeanDefinitions(locationResource);

        applicationContext.refresh();
        System.out.println(applicationContext.getBean(UserHolder.class));
        applicationContext.close();
    }

    @Bean
    public UserHolder UserHolder(User user){
        return new UserHolder(user);
    }
}

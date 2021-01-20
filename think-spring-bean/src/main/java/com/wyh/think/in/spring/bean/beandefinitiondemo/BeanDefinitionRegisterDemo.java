package com.wyh.think.in.spring.bean.beandefinitiondemo;

import com.wyh.think.in.spring.ico.container.overview.domain.User;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * BeanDefinition 创建springBean
 */
public class BeanDefinitionRegisterDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        registerBeanDefinition(applicationContext);
        registerUserBeanDefinition(applicationContext, "aaa-user");
        applicationContext.refresh();
        Map<String, User> beansOfType = applicationContext.getBeansOfType(User.class);
        System.out.println(beansOfType);
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name", "王亚豪");
        beanDefinitionBuilder.addPropertyValue("age", "10");
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, beanDefinition);
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry);
        }
    }

    public static void registerBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }
}

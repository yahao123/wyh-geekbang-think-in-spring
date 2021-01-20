package com.wyh.think.in.spring.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;


/**
 * XML 构造器注入
 */
public class XmlDependencyConstructorInjection {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String locationResource = "classpath:/dependency-constructor-injection.xml";
        beanDefinitionReader.loadBeanDefinitions(locationResource);

        System.out.println(beanFactory.getBean(UserHolder.class));
    }
}

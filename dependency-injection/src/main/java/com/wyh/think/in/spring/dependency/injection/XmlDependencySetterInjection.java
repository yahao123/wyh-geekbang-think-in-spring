package com.wyh.think.in.spring.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class XmlDependencySetterInjection {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String locationResource = "classpath:/dependency-setter-injection.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(locationResource);

        System.out.println(beanFactory.getBean(UserHolder.class));
    }
}

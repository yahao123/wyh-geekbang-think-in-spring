package com.wyh.think.in.spring.ico.container.overview.container;

import com.wyh.think.in.spring.ico.container.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class BeanFactoryAsIocContainerDemo {

    // 使用defaultListableBeanFactory启动容器，这样无法使用aop,event等特性
    public static void main(String[] args) {
        DefaultListableBeanFactory application = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(application);
        int i = xmlBeanDefinitionReader.loadBeanDefinitions("classpath:dependent-lookup.xml");
        System.out.println(i);
        System.out.println(application.getBean(User.class));
    }
}

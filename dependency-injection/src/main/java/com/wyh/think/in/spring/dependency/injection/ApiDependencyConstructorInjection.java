package com.wyh.think.in.spring.dependency.injection;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class ApiDependencyConstructorInjection {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String locationResource = "classpath:/dependent-lookup.xml";
        beanDefinitionReader.loadBeanDefinitions(locationResource);

        BeanDefinition beanDefinition = ApiDependencyConstructorInjection.createBeanDefinition();
        beanFactory.registerBeanDefinition("userHolder", beanDefinition);

        System.out.println(beanFactory.getBean(UserHolder.class));
    }

    private static BeanDefinition createBeanDefinition(){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        beanDefinitionBuilder.addConstructorArgReference("superUser");
        return beanDefinitionBuilder.getBeanDefinition();
    }
}

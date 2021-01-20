package com.wyh.think.in.spring.dependency.injection;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class ApiDependencySetterInjection {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition userHolderBeanDefinition = createBeanDefinition();
        beanFactory.registerBeanDefinition("userHolder",userHolderBeanDefinition);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String locationResource = "classpath:/dependent-lookup.xml";
        reader.loadBeanDefinitions(locationResource);
        System.out.println(beanFactory.getBean(UserHolder.class));
    }

    private static BeanDefinition createBeanDefinition(){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        beanDefinitionBuilder.addPropertyReference("user", "superUser");
        return beanDefinitionBuilder.getBeanDefinition();
    }
}

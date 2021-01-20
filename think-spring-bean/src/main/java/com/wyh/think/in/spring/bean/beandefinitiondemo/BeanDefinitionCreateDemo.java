package com.wyh.think.in.spring.bean.beandefinitiondemo;

import com.wyh.think.in.spring.ico.container.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * 创建 BeanDefinition
 */
public class BeanDefinitionCreateDemo {

    public static void main(String[] args) {
        // 第一种方式
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name", "王亚豪");
        beanDefinitionBuilder.addPropertyValue("age", "10");
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        // 第二种方式
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues
                .add("name", "王亚豪")
                .add("age", 10);
        genericBeanDefinition.setPropertyValues(propertyValues);
    }
}

package com.wyh.think.in.spring.dependency.injection;

import com.wyh.think.in.spring.ico.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 字段注入
 */
public class AnnotationDependencyFieldInjection {

    @Autowired
    private UserHolder userHolder;

    @Autowired
    private UserHolder userHolder2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 在这里即使AnnotationDependencyFieldInjection没有标明注解，它也会成为springBean
        applicationContext.register(AnnotationDependencyFieldInjection.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions("classpath:/dependent-lookup.xml");

        applicationContext.refresh();
        // 获取Bean
        AnnotationDependencyFieldInjection bean = applicationContext.getBean(AnnotationDependencyFieldInjection.class);
        System.out.println(bean.userHolder);
        System.out.println(bean.userHolder == bean.userHolder2);
        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user){
        return new UserHolder(user);
    }
}

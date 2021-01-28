package com.wyh.think.in.spring.dependency.injection;

import com.wyh.think.in.spring.ico.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ：WangYaHao
 * @date ：Created in 2021/1/23 7:20 上午
 */
public class AnnotaionDependencyResolveInjection {

    @Autowired
    private User user;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(AnnotaionDependencyResolveInjection.class);
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(annotationConfigApplicationContext);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:/dependent-lookup.xml");
        annotationConfigApplicationContext.refresh();

        AnnotaionDependencyResolveInjection bean = annotationConfigApplicationContext.getBean(AnnotaionDependencyResolveInjection.class);
        System.out.println("user:" + bean.user);

        annotationConfigApplicationContext.close();
    }
}

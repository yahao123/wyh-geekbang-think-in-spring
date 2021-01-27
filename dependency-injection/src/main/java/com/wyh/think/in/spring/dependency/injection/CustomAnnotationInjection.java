package com.wyh.think.in.spring.dependency.injection;

import com.wyh.think.in.spring.dependency.injection.annotation.AutowiredUser;
import com.wyh.think.in.spring.dependency.injection.annotation.MyAutowired;
import com.wyh.think.in.spring.ico.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * 自定义构造器注入
 */
public class CustomAnnotationInjection {

    @Autowired
    private User user;

    @MyAutowired
    private User myAutowiredUser;

    @AutowiredUser
    private User autowiredUser;

    // 替换默认的AutowiredAnnotationBeanPostProcessor
//    @Bean(name = AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        // 替换时这里必须添加原本的Autowired.class 否则原有的Autowired不会生效
//        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(Arrays.asList(Autowired.class, AutowiredUser.class));
//        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
//        return autowiredAnnotationBeanPostProcessor;
//    }


    /**
     * 这种方式可以替换上面的方式，既能加载自定义的注解，也可以保持原有的注解不受影响
     * 使用 @Order(Ordered.LOWEST_PRECEDENCE - 3) 声明这个Bean的优先级
     *
     * @return AutowiredAnnotationBeanPostProcessor
     */
    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationType(AutowiredUser.class);
        return autowiredAnnotationBeanPostProcessor;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(CustomAnnotationInjection.class);
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions("classpath:dependent-lookup.xml");
        applicationContext.refresh();


        CustomAnnotationInjection customAnnotationInjectionBean = applicationContext.getBean(CustomAnnotationInjection.class);
        System.out.println("user:" + customAnnotationInjectionBean.user);

        System.out.println("myAutowired User:" + customAnnotationInjectionBean.myAutowiredUser);

        System.out.println("autowiredUser user:" + customAnnotationInjectionBean.autowiredUser);
        applicationContext.close();
    }
}

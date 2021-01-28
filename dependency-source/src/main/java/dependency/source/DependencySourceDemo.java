package dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖注入来源
 * 依赖注入的来源并非只有XML,和注解，还有spring内建Bean 例如BeanFactory 这点是于依赖查找不同的
 *
 * @author ：WangYaHao
 * @date ：Created in 2021/1/28 8:39 上午
 */
public class DependencySourceDemo {

    // 注入在 postProcessProperties 方法早于setter 注入， 早于 @PostConstruct
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void init() {
        System.out.println("beanFactory == applicationContext: " + (beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext.getAutowireCapableBeanFactory(): " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("applicationContext == resourceLoader: " + (applicationContext == resourceLoader));
        System.out.println("resourceLoader == applicationEventPublisher: " + (resourceLoader == applicationEventPublisher));
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(DependencySourceDemo.class);
        annotationConfigApplicationContext.refresh();

        annotationConfigApplicationContext.close();
    }
}

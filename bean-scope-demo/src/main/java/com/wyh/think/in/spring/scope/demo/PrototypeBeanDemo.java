package com.wyh.think.in.spring.scope.demo;

import com.wyh.think.in.spring.ico.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

/**
 * @author ：WangYaHao
 * @date ：Created in 2021/2/2 2:09 下午
 *
 * 结论1: singleton Bean每次注入或者依赖查找得到的都是同一个对象，而prototype Bean每次注入或依赖查找到的都是新的对象。
 *
 * 结论2: 当通过集合类型注入时，集合类中会包含一个singleton Bean和prototype Bean.该prototype Bean也是新创建的对象。
 *
 * 结论3: singleton Bean和 prototype Bean都会执行生命周期中的初始化方法，但是prototype Bean不会执行bean生命周期中的销毁方法。
 *
 */
public class PrototypeBeanDemo {

    @Autowired
    @Qualifier("prototypeUser")
    private User user;

    @Autowired
    @Qualifier("prototypeUser")
    private User user1;

    @Autowired
    @Qualifier("prototypeUser")
    private User user2;

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;

    @Bean
    @Scope("prototype")
    public User prototypeUser() {
        return creatUser();
    }

    @Bean
    public User singletonUser() {
        return creatUser();
    }

    @Autowired
    public Map<String, User> users;

    private User creatUser() {
        User user = new User();
        user.setAge(1);
        user.setName("wangyahao");
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(PrototypeBeanDemo.class);
        applicationContext.refresh();

        PrototypeBeanDemo bean = applicationContext.getBean(PrototypeBeanDemo.class);
        System.out.println("user: " + bean.user.hashCode());
        System.out.println("user1: " + bean.user1.hashCode());
        System.out.println("user2: " + bean.user2.hashCode());
        System.out.println("singleton User: " + bean.singletonUser.hashCode());
        System.out.println("singleton User: " + bean.singletonUser1.hashCode());

        Map<String, User> users = bean.users;
        for (Map.Entry<String, User> stringUserEntry : users.entrySet()) {
            System.out.println("users: " + stringUserEntry.getKey() + " beanHashCode:" + stringUserEntry.getValue().hashCode());
        }
        applicationContext.close();
    }
}

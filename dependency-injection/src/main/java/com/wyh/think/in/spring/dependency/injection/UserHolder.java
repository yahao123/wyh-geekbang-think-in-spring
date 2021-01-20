package com.wyh.think.in.spring.dependency.injection;

import com.wyh.think.in.spring.ico.container.overview.domain.User;

public class UserHolder {

    private User user;

    public UserHolder() {
    }

    public UserHolder(User user) {
        System.out.printf("构造器注入,传入参数{%s}%n", user);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {

        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}

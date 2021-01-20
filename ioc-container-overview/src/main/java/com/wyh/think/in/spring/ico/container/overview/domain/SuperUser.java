package com.wyh.think.in.spring.ico.container.overview.domain;

import com.wyh.think.in.spring.ico.container.overview.annotaion.Super;

@Super
public class SuperUser extends User{

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                '}' + super.toString();
    }
}

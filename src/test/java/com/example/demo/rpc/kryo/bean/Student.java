package com.example.demo.rpc.kryo.bean;

import java.io.Serializable;

/**
 * Created by liuyumeng on 2018/5/25.
 */
public class Student implements Serializable{
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

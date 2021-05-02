package com.zichen.boot.bean;

/**
 * @name: Pet
 * @description:
 * @author: zichen
 * @date: 2021/5/2  22:04
 */
public class Pet {

    private String name;

    public Pet() {
    }

    public Pet(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

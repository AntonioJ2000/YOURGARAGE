package com.anto.yourgarage.models;

public class CarEntity {
    private String name;

    public CarEntity() {
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        if (name.startsWith("a")) {
            this.name = name;
            return true;
        } else {
            return false;
        }
    }

}

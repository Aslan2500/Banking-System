package com.company;

public class User {
    private final String name;
    private final int id;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Name - '" + name + '\'' +
                ", id - " + id +
                '}';
    }
}

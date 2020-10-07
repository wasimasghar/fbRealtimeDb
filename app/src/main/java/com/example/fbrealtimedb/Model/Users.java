package com.example.fbrealtimedb.Model;

public class Users {
    private String Name;
    private int Age;

    public Users(String name, int age) {
        Name = name;
        Age = age;
    }

    public Users() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public int getAge() {
        return this.Age;
    }

    public void setAge(int age) {
        this.Age = age;
    }
}

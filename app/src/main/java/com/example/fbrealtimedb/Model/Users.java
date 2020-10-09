package com.example.fbrealtimedb.Model;

public class Users {
    private String uid;
    private String Name;
    private int Age;

    public Users(String name, int age) {
        Name = name;
        Age = age;
    }

    public Users() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    @Override
    public boolean equals(Object obj) {
       if (obj instanceof Users){
           Users users= (Users) obj;
           return this.getUid().equals(users.uid);
       }
       else
           return false;
    }
}

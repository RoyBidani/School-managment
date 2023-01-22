package com.company;

public abstract class Person {
    private String name;
    private String id;
    private int age;
    private String phoneNumber;

    public Person(String name, String id, int age, String phoneNumber) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getId() {
        return id;
    }

    public Person setId(String id) {
        this.id = id;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Person setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' ;
    }
}

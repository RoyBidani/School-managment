package com.company;

import java.util.List;

public class Student extends Person {

    private String className;
    private String major;
    private double gpa;
    private String curse;

    public Student(String name, String id, int age, String phoneNumber, String className, String major, double gpa, String curse) {
        super(name, id, age, phoneNumber);
        this.className = className;
        this.major = major;
        this.gpa = gpa;
        this.curse = curse;
    }
    public Student(){
        super("null","null",0,"null");
        this.className = "null";
        this.major = "null";
        this.gpa = 0;
        this.curse = "null";
    }

    public String getClassName() {
        return className;
    }

    public Student setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getMajor() {
        return major;
    }

    public Student setMajor(String major) {
        this.major = major;
        return this;
    }

    public double getGpa() {
        return gpa;
    }

    public Student setGrades(double gpa) {
        this.gpa = gpa;
        return this;
    }

    public String getCurse() {
        return curse;
    }

    public void setCurses(String curse) {
        this.curse = curse;

    }

    @Override
    public String toString() {

        return super.toString()+
                " className='" + className + '\'' +
                ", major='" + major + '\'' +
                ", gpa=" + gpa +
                ", curse='" + curse + '\''+ '\n';
    }
}

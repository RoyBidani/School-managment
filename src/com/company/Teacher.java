package com.company;

public class Teacher extends Person {
    private String curseSubject;
    private String educationClass;

    public Teacher(String name, String id, int age, String phoneNumber, String curseSubject, String educationClass) {
        super(name, id, age, phoneNumber);
        this.curseSubject = curseSubject;
        this.educationClass = educationClass;
    }
    public Teacher(){
        super("null","null",0,"null");
        this.curseSubject = "";
        this.educationClass = "";
    }


    public String getCurseSubject() {
        return curseSubject;
    }

    public Teacher setCurseSubject(String curseSubject) {
        this.curseSubject = curseSubject;
        return this;
    }

    public String getEducationClass() {
        return educationClass;
    }

    public Teacher setEducationClass(String educationClass) {
        this.educationClass = educationClass;
        return this;
    }

    @Override
    public String toString() {
        return super.toString()+
                " curseSubject='" + curseSubject + '\'' +
                ", educationClass='" + educationClass + '\'' + '\n';
    }
}

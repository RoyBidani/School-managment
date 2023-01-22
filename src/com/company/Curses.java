package com.company;

public class Curses {
    private String curseName;

    public Curses(String curseName) {
        this.curseName = curseName;
    }

    public String getCurseName() {
        return curseName;
    }

    public String setCurseName(String curseName) {
        this.curseName = curseName;
        return curseName;
    }
}

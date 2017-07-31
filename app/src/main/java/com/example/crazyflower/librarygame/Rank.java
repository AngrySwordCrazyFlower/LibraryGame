package com.example.crazyflower.librarygame;

/**
 * Created by CrazyFlower on 2017/7/24.
 */

public class Rank {
    private String name;
    private int grade;
    private int num;
    public Rank(int num, String name, int grade) {
        this.name = name;
        this.grade = grade;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public int getNum() {
        return num;
    }
}

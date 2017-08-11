package com.example.crazyflower.librarygame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CrazyFlower on 2017/8/7.
 */

public class Account implements Serializable{
    private int star;
    private String name;

    private int stoneNum;
    private int savedStarNum;


    private boolean realbook;
    private int realbook_num;
    private boolean famousbook;
    private int famousbook_num;
    private boolean lendbook;
    private int lendbook_num;
    private List backList;

    private static Account uniqueInstance;



    private Account() {
        star = 500;
        name = "1234589@qq.com";
        backList = new ArrayList();
        backList.add("213");
        backList.add("fsdfdsfds");
        famousbook = true;
        famousbook_num = 2;
        realbook =false;
        realbook_num = 0;
        lendbook = true;
        lendbook_num = 3;
        stoneNum = 2;
        savedStarNum = 10;

    }


    public static synchronized Account getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Account();
        }
        return uniqueInstance;
    }
    public void addStar(int num) {
        star += num;
    }

    public void decStar(int num) {
        star -= num;
    }

    public int getStar() {
        return star;
    }

    public String getName() {
        return this.name;
    }

    public boolean isRealbook() {
        return realbook;
    }

    public boolean isFamousbook() {
        return famousbook;
    }

    public boolean isLendbook() {
        return lendbook;
    }

    public int getLendbook_num() {
        return lendbook_num;
    }

    public int getRealbook_num() {
        return realbook_num;
    }

    public int getFamousbook_num() {
        return famousbook_num;
    }

    public List getBackList() {
        return backList;
    }

    public int getStoneNum() {
        return stoneNum;
    }

    public int getSavedStarNum() {
        return savedStarNum;
    }
}

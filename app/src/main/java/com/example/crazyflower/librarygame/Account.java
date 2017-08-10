package com.example.crazyflower.librarygame;

import java.io.Serializable;
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

    public Account(String user_name, String user_password, String result) {
        star = 500;
        name = "123456789@qq.com";

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

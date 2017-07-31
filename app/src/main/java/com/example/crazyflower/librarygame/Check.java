package com.example.crazyflower.librarygame;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by CrazyFlower on 2017/7/31.
 */

public class Check {
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }


    public static boolean isMobile (String mobiles) {
        boolean flag = false;
        try{
            Pattern p = Pattern.compile("^（（13[0-9]）|（15[0-9]）|（18[0-9]））\\d{8}$");
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        } catch(Exception e) {
            flag = false;
        }
        return flag;
    }
}
package com.example.crazyflower.librarygame;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

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
    private int freeze;
    private int glass;

    private boolean realbook;
    private int realbook_num;
    private boolean famousbook;
    private int famousbook_num;
    private boolean lendbook;
    private int lendbook_num;
    private List backList;

    private int[] price = {200, 200, 4000, 4000, 4000};

    private String result;

    private static Account uniqueInstance;

    private String[] goodsName = {"freeze", "glass", "famousbook", "realbook", "lendbook"};

    private Account() {
        star = 0;
        name = "";
        backList = new ArrayList();
        backList.add("无");
        backList.add("无");
        famousbook = false;
        famousbook_num = 0;
        realbook =false;
        realbook_num = 0;
        lendbook = false;
        lendbook_num = 0;
        stoneNum = 0;
        savedStarNum = 0;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setStar(int star) {
        this.star = star;
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

    public void setStoneNum(int num) {
        this.stoneNum = num;
    }

    public void setSavedStarNum(int num) {
        this.savedStarNum = num;
    }

    public void setLendbook_num(int num) {
        this.lendbook_num = num;
        if (num > 0) {
            lendbook = true;
        } else {
            lendbook = false;
        }
    }

    public void setRealbook_num(int num) {
        this.realbook_num = num;
        if (num > 0) {
            realbook = true;
        } else {
            realbook = false;
        }
    }

    public void setFamousbook_num(int num) {
        this.famousbook_num = num;
        if (num > 0) {
            famousbook = true;
        } else {
            famousbook = false;
        }
    }

    public int getFreeze() {
        return freeze;
    }

    public void setFreeze(int freeze) {
        this.freeze = freeze;
    }

    public int getGlass() {
        return glass;
    }

    public void setGlass(int glass) {
        this.glass = glass;
    }

    public void buy(final int order, final int num) {
        star -= price[order]*num;
        switch (order) {
            case 0:
                freeze += num;
                break;
            case 1:
                glass += num;
                break;
            case 2:
                if (famousbook == false) {
                    famousbook = true;
                }
                famousbook_num += num;
                break;
            case 3:
                if (realbook == false) {
                    realbook = true;
                }
                realbook_num += num;
                break;
            case 4:
                if (lendbook == false) {
                    lendbook = true;
                }
                lendbook_num += num;
                break;
            default:
                break;
        }
        Thread t1 = new Thread() {
            public void run() {
                try {
                    //请求数据
                    HttpClient hc = new DefaultHttpClient();
                    HttpPost hp = new HttpPost(
                            "http://115.159.92.219:2000/api/store");
                    //请求json报文
                    JSONObject jo = new JSONObject();
                    jo.put("username", name);
                    jo.put("storename", goodsName[order]);
                    jo.put("quantity", num);

                    hp.setEntity(new StringEntity(jo.toString(), "UTF-8"));
                    HttpResponse hr = hc.execute(hp);
                    result = null;
                    //获取返回json报文
                    if (hr.getStatusLine().getStatusCode() == 200) {

                        result = EntityUtils.toString(hr.getEntity());
                    }
                    //关闭连接
                    if (hc != null) {
                        hc.getConnectionManager().shutdown();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
    }

    public void getList() {
        Thread t1 = new Thread() {
            public void run() {
                try {
                    //请求数据
                    HttpClient hc = new DefaultHttpClient();
                    HttpPost hp = new HttpPost(
                            "http://115.159.92.219:2000/api/getsuggestion");
                    //请求json报文
                    JSONObject jo = new JSONObject();
                    jo.put("username", name);

                    hp.setEntity(new StringEntity(jo.toString(), "UTF-8"));
                    HttpResponse hr = hc.execute(hp);
                    result = null;
                    //获取返回json报文
                    if (hr.getStatusLine().getStatusCode() == 200) {
                        result = EntityUtils.toString(hr.getEntity());
                        Log.i("suggcontent", result);
                    }
                    //关闭连接
                    if (hc != null) {
                        hc.getConnectionManager().shutdown();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
        while (t1.isAlive()) {

        }
        try {
            JSONObject jo = new JSONObject(result);
            if (jo.getString("suggestion1").equals("") == false) {
                backList.add(jo.getString("suggestion1"));
            }
            if (jo.getString("suggestion2").equals("") == false) {
                backList.add(jo.getString("suggestion2"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

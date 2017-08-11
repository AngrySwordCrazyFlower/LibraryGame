package com.example.crazyflower.librarygame;

import android.content.Intent;
import android.icu.util.ValueIterator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.method.SingleLineTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by CrazyFlower on 2017/7/30.
 */

public class SignIn extends AppCompatActivity {

    private TextView userName;
    private TextView userPassword;
    private Button signIn;
    private TextView signUp;
    private String result = null;
    private Account myAccount;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        setContentView(R.layout.sign_in);
        initWidget();
    }

    private void initWidget() {
        userName = (TextView) findViewById(R.id.user_name);
        userPassword = (TextView) findViewById(R.id.user_password);
        signIn = (Button) findViewById(R.id.sign_in_bt);
        signUp = (TextView) findViewById(R.id.sign_up_bt);
        signIn.setOnClickListener(new SignInBt());
        signUp.setOnClickListener(new SignUpBt());
    }

    private class SignInBt implements View.OnClickListener {
        public void onClick(View view) {

            final String name = userName.getText().toString();
            final String password = userPassword.getText().toString();
            String type = null;
            Log.i("SignIn", name + password);

            if ((name.equals("") == false) && (password.equals("") == false)) {
                Thread t1 = new Thread() {
                    public void run() {
                        try {
                            //请求数据
                            HttpClient hc = new DefaultHttpClient();
                            HttpPost hp = new HttpPost(
                                    "http://115.159.92.219:2000/api/authenticated");
                            //请求json报文
                            JSONObject jo = new JSONObject();
                            jo.put("username", name);
                            jo.put("password", password);

                            hp.setEntity(new StringEntity(jo.toString(), "UTF-8"));
                            HttpResponse hr = hc.execute(hp);
                            result = null;
                            //获取返回json报文
                            if (hr.getStatusLine().getStatusCode() == 200) {
                                result = EntityUtils.toString(hr.getEntity());
                                Log.d("2134", "result : " + result);
                            }
                            //关闭连接
                            if (hc != null) {
                                hc.getConnectionManager().shutdown();
                                Log.i("2134", "eee");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.i("2134", "error");
                        }
                    }
                };
                t1.start();
                while (t1.isAlive()) {

                }
                myAccount = Account.getInstance();
                try {
                    JSONObject jo = new JSONObject(result);
                    if (jo.getInt("status") == 0) {
                        myAccount.setName(name);
                        myAccount.setStar(jo.getInt("star"));
                        myAccount.setLendbook_num(jo.getInt("lendbook"));
                        myAccount.setFamousbook_num(jo.getInt("famousbook"));
                        myAccount.setRealbook_num(jo.getInt("realbook"));
                        myAccount.setFreeze(jo.getInt("freeze"));
                        myAccount.setGlass(jo.getInt("glass"));
                        myAccount.setSavedStarNum(jo.getInt("savedStar"));
                        myAccount.setStoneNum(jo.getInt("stone"));
                        Intent intent = new Intent(SignIn.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignIn.this, "账号或密码错误", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            //remain httpconnection to do
            //Account.getInstance();
            //Intent intent = new Intent(SignIn.this, MainActivity.class);
            //startActivity(intent);
        }
    }



    public static String getStringFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        String result = baos.toString();
        is.close();
        baos.close();

        return result;
    }
    private boolean checkEdit(String name, String password) {
        if (name.equals("") == true) {
            Toast.makeText(SignIn.this, "请输入用户名", Toast.LENGTH_LONG).show();
            return false;
        }
        if (password.equals("") == true) {
            Toast.makeText(SignIn.this, "请输入密码", Toast.LENGTH_LONG).show();
        }
        return true;
    }
    private class SignUpBt implements View.OnClickListener {
        public void onClick(View view) {
            Intent intent = new Intent(SignIn.this, SignUp.class);
            startActivity(intent);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

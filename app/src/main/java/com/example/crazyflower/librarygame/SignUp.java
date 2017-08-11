package com.example.crazyflower.librarygame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by CrazyFlower on 2017/7/31.
 */

public class SignUp extends AppCompatActivity {

    private EditText user_email;
    private EditText user_psd;
    private EditText user_psd_again;
    private Button sign_up;
    private String result;
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
        setContentView(R.layout.sign_up);
        initWidget();

        sign_up.setOnClickListener(new SignUpSure());
    }

    private void initWidget() {
        user_email = (EditText) findViewById(R.id.user_name);
        user_psd = (EditText) findViewById(R.id.user_password);
        user_psd_again = (EditText) findViewById(R.id.user_password_again);
        sign_up = (Button) findViewById(R.id.sign_up);
    }

    private class SignUpSure implements View.OnClickListener {
        public void onClick(View v) {
            final String name = user_email.getText().toString();
            final String password = user_psd.getText().toString();
            String password_agin = user_psd_again.getText().toString();
            String type = null;
            if ((password.equals(password_agin) == true) && (name.equals("") == false) && (password.equals("") == false)) {
                Thread t1 = new Thread() {
                    public void run() {
                        try {
                            //请求数据
                            HttpClient hc = new DefaultHttpClient();
                            HttpPost hp = new HttpPost(
                                    "http://115.159.92.219:2000/api/users");
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
                    if (jo.getString("result").equals("right")) {
                        myAccount.setName(name);
                    } else {
                        Toast.makeText(SignUp.this, "用户名已存在", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                if (password.equals(password_agin) == false) {
                    Toast.makeText(SignUp.this, "两次密码不一致", Toast.LENGTH_LONG).show();
                } else {
                    if (name.equals("")) {

                        Toast.makeText(SignUp.this, "账号不能为空", Toast.LENGTH_LONG).show();
                    } else {
                       if (password.equals("")) {

                           Toast.makeText(SignUp.this, "密码不能为空", Toast.LENGTH_LONG).show();
                       }
                    }
                }
            }
        }
    }

}

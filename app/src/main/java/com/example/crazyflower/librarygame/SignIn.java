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

            String name = userName.getText().toString();
            String password = userPassword.getText().toString();
            String type = null;
            String result = null;
            Log.i("SignIn", name + password);
            /*if (!checkEdit(name, password)) {
                return;
            } else {
                if (Check.isMobile(name)) {
                    type = "phone";
                } else if (Check.checkEmail(name)) {
                    type = "email";
                }
                HttpURLConnection httpURLConnection = null;
                try {
                    URL url = new URL("http://192.168.0.13:8080/ServerXXX/servlet/RegisiterServle");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    String data = "user=" + name + "&password=" + password + "&type=" + type;
                    OutputStream os = httpURLConnection.getOutputStream();
                    os.write(data.getBytes());
                    os.flush();
                    os.close();

                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream is = httpURLConnection.getInputStream();
                        result = getStringFromInputStream(is);

                    } else {
                        Log.i("SignUp.class", "网络连接不对");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
            //remain httpconnection to do
            Account.getInstance();
            Intent intent = new Intent(SignIn.this, MainActivity.class);
            startActivity(intent);
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

package com.example.crazyflower.librarygame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by CrazyFlower on 2017/7/31.
 */

public class SignUp extends AppCompatActivity {

    private EditText user_email;
    private EditText user_psd;
    private EditText user_psd_again;
    private Button sign_up;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            String name = user_email.getText().toString();
            String password = user_psd.getText().toString();
            String password_agin = user_psd_again.getText().toString();
            String type = null;

            if (!checkEdit(name, password, password_agin)) {
                return;
            } else {
                if (Check.isMobile(name)) {
                    type = "phone";
                } else if (Check.checkEmail(name)) {
                    type = "email";
                }
                HttpURLConnection httpURLConnection = null;
                try {
                    URL url = new URL("http://192.168.0.13:8080/ServerXXX/servlet/LoginServle");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    String data = "user=" + name + "&password=" + password + "&type=" + type;
                    OutputStream os = httpURLConnection.getOutputStream();
                    os.write(data.getBytes());
                    os.flush();
                    os.close();

                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream is = httpURLConnection.getInputStream();
                        String state = getStringFromInputStream(is);

                    } else {
                        Log.i("SignUp.class", "网络连接不对");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
    private boolean checkEdit(String name, String password, String password_again) {
        if (!password.equals(password_again)) {
            Toast.makeText(SignUp.this, "两次密码不一致", Toast.LENGTH_LONG);
            return false;
        }
        if ((!Check.checkEmail(name)) && (!Check.isMobile(name))) {
            Toast.makeText(SignUp.this, "用户名不是手机或邮箱", Toast.LENGTH_LONG);
            return false;
        }
        return true;
    }


}

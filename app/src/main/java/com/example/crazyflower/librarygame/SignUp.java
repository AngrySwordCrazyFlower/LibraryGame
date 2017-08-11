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
            final String[] result = {null};
            if ((password.equals(password_agin) == true) && (name.equals("") == false) && (password.equals("") == false)) {
                /*HttpURLConnection httpURLConnection = null;
                try {
                    URL url = new URL("http://115.159.92.219:2000/api/users");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setConnectTimeout(5000);
                    String data = "username=" + URLEncoder.encode(name, "UTF-8") +
                            "&password=" + URLEncoder.encode(password, "UTF-8");
                    OutputStream os = httpURLConnection.getOutputStream();
                    os.write(data.getBytes());
                    os.flush();
                    os.close();

                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream is = httpURLConnection.getInputStream();
                        result = getStringFromInputStream(is);
                        Log.i("SignUp:", result);
                    } else {
                        Log.i("SignUp.class", "网络连接不对");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
                new Thread() {
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
                            result[0] = null;
                            //获取返回json报文
                            if (hr.getStatusLine().getStatusCode() == 200) {
                                result[0] = EntityUtils.toString(hr.getEntity());
                                Log.d("2134444", "result : " + result[0]);
                            }
                            //关闭连接
                            if (hc != null) {
                                hc.getConnectionManager().shutdown();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

                Account.getInstance();
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }



    /*public static String getStringFromInputStream(InputStream is) throws IOException {
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
    }*/

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

package com.example.crazyflower.librarygame;

import android.content.Intent;
import android.icu.util.ValueIterator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.HttpURLConnection;

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
            Log.i("SignIn", name + password);
            //remain httpconnection to do
        }
    }

    private class SignUpBt implements View.OnClickListener {
        public void onClick(View view) {
            Intent intent = new Intent(SignIn.this, MainActivity.class);
            startActivity(intent);
        }
    }
}

package com.example.crazyflower.librarygame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by CrazyFlower on 2017/8/10.
 */

public class PersonalActivity extends AppCompatActivity implements View.OnClickListener {
    private Account myAccount;

    private ImageView backBt;

    private TextView stoneNum;
    private TextView savedStarNum;
    private TextView userName;

    private RelativeLayout ensureLogOut;
    private ImageView personalBlackBg;

    private TextView loginOut;
    private TextView cancle;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        setContentView(R.layout.personal);
        myAccount = Account.getInstance();
        initView();
    }

    private void initView() {

        backBt = (ImageView) findViewById(R.id.backBt);
        backBt.setOnClickListener(this);

        userName = (TextView) findViewById(R.id.user_name);
        userName.setText(myAccount.getName());
        stoneNum = (TextView) findViewById(R.id.stone_num);
        savedStarNum = (TextView) findViewById(R.id.saved_star_num);
        stoneNum.setText(myAccount.getStoneNum() + "");
        savedStarNum.setText(myAccount.getSavedStarNum() + "");
        userName.setOnClickListener(this);

        ensureLogOut = (RelativeLayout) findViewById(R.id.ensure_login_out);
        personalBlackBg = (ImageView) findViewById(R.id.personal_black_bg);
        ensureLogOut.setOnClickListener(this);
        personalBlackBg.setOnClickListener(this);
        loginOut = (TextView) findViewById(R.id.out);
        cancle = (TextView) findViewById(R.id.cancel);
        loginOut.setOnClickListener(this);
        cancle.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBt:
                finish();
                break;
            case R.id.user_name:
                personalBlackBg.setVisibility(View.VISIBLE);
                ensureLogOut.setVisibility(View.VISIBLE);
                break;
            case R.id.ensure_login_out:
                break;
            case R.id.personal_black_bg:
                personalBlackBg.setVisibility(View.GONE);
                ensureLogOut.setVisibility(View.GONE);
                break;
            case R.id.out:
                Intent intent = new Intent(PersonalActivity.this, SignIn.class);
                startActivity(intent);
                break;
            case R.id.cancel:
                personalBlackBg.setVisibility(View.GONE);
                ensureLogOut.setVisibility(View.GONE);
                break;
        }
    }
}

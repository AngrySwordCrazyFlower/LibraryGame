package com.example.crazyflower.librarygame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal);
        myAccount = (Account) getIntent().getSerializableExtra("Account");
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
        }
    }
}

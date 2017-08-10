package com.example.crazyflower.librarygame;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Rank> rankList = new ArrayList<>();
    private ImageView menu_image;
    private ImageView menu_setting;
    private ImageView menu_mall;
    private ImageView menu_personal;
    private ImageView menu_base;
    private TextView starNum;
    private LinearLayout menu_spread;
    private ImageView threeCircle;
    private Animation animation;
    private Account myAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        myAccount = (Account) intent.getSerializableExtra("Account");



        initDatas();
        RankAdapter adapter = new RankAdapter(MainActivity.this, R.layout.rank_item, rankList);
        ListView listView = (ListView) findViewById(R.id.ranklist_content);
        listView.setAdapter(adapter);


        initView();


    }

    private void initView() {
        menu_image = (ImageView) findViewById(R.id.menu);
        menu_spread = (LinearLayout) findViewById(R.id.menu_spread) ;
        menu_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_image.setVisibility(View.GONE);
                menu_spread.setVisibility(View.VISIBLE);
            }
        });
        menu_spread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_image.setVisibility(View.VISIBLE);
                menu_spread.setVisibility(View.GONE);
            }
        });
        menu_setting = (ImageView) findViewById(R.id.image_setting);
        menu_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIntent().setClass(MainActivity.this, SettingActivity.class);
                startActivity(getIntent());
            }
        });
        menu_mall = (ImageView) findViewById(R.id.image_mall);
        menu_mall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIntent().setClass(MainActivity.this, MallActivity.class);
                startActivity(getIntent());
            }
        });
        menu_base = (ImageView) findViewById(R.id.image_base);
        menu_base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIntent().setClass(MainActivity.this, BaseActivity.class);
                startActivity(getIntent());
            }
        });
        menu_personal = (ImageView) findViewById(R.id.image_personal);
        menu_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIntent().setClass(MainActivity.this, PersonalActivity.class);
                startActivity(getIntent());
            }
        });

        animation = AnimationUtils.loadAnimation(this, R.anim.big_light);
        threeCircle = (ImageView) findViewById(R.id.three_circle);
        threeCircle.startAnimation(animation);

        starNum = (TextView) findViewById(R.id.star_num);
        starNum.setText(myAccount.getStar() + "");
    }
    private void initDatas() {
        for (int i = 0; i < 5; i++) {
            Rank num1 = new Rank(1, "123", 5555);
            rankList.add(num1);
            Rank num2 = new Rank(2, "345", 12345);
            rankList.add(num2);
        }
    }
}

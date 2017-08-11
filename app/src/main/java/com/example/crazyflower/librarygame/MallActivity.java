package com.example.crazyflower.librarygame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by CrazyFlower on 2017/8/6.
 */

public class MallActivity extends AppCompatActivity implements View.OnClickListener {

    //五张图片所在的Linearlayout的控件
    private LinearLayout freeze;
    private LinearLayout glass;
    private LinearLayout realbook;
    private LinearLayout lendbook;
    private LinearLayout famousbook;

    //黑屏Imgae
    private ImageView blackBG;

    //确认框控件
    private LinearLayout ensure;

    //确认框里的各个控件
    private TextView tool_price;
    private ImageView tool_image;
    private TextView tool_content;
    private SeekBar buy_seekbar;
    private TextView cancel;
    private TextView buy;
    private TextView buy_number;

    private Animation animation_bottom_to_top;
    private Animation animation_top_to_bottom;
    private TextView star_num;
    private int currOrd;
    private int currNum;

    private Account myAccount;

    int[] price = {200, 200, 200, 200, 200};

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
        setContentView(R.layout.mall);
        myAccount = Account.getInstance();

        initView();

        star_num.setText(myAccount.getStar() + "");
    }


    private void initView() {
        freeze = (LinearLayout) findViewById(R.id.freeze);
        glass = (LinearLayout) findViewById(R.id.glass);
        realbook = (LinearLayout) findViewById(R.id.realbook);
        lendbook = (LinearLayout) findViewById(R.id.lendbook);
        famousbook = (LinearLayout) findViewById(R.id.famousbook);

        blackBG = (ImageView) findViewById(R.id.black_bg);
        ensure = (LinearLayout) findViewById(R.id.ensure);

        tool_image = (ImageView) findViewById(R.id.tool_which);
        tool_content = (TextView) findViewById(R.id.tool_content);
        tool_price = (TextView) findViewById(R.id.price);
        buy_number = (TextView) findViewById(R.id.buy_number);

        buy_seekbar = (SeekBar) findViewById(R.id.buy_seekbar);
        buy_seekbar.setMax(3);
        currNum = buy_seekbar.getProgress() + 1;
        buy_number.setText("数量: " + (currNum));

        buy_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    currNum = progress + 1;
                    seekBar.setProgress(progress);
                    buy_number.setText("数量: " + currNum);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        cancel = (TextView) findViewById(R.id.cancel);
        buy = (TextView) findViewById(R.id.buy);

        freeze.setOnClickListener(this);
        glass.setOnClickListener(this);
        realbook.setOnClickListener(this);
        lendbook.setOnClickListener(this);
        famousbook.setOnClickListener(this);
        blackBG.setOnClickListener(this);
        ensure.setOnClickListener(this);
        cancel.setOnClickListener(this);
        buy.setOnClickListener(this );

        star_num = (TextView) findViewById(R.id.mall_star_num);
        animation_bottom_to_top = AnimationUtils.loadAnimation(this, R.anim.ensure_from_bottom_to_top);
        animation_top_to_bottom = AnimationUtils.loadAnimation(this, R.anim.ensure_from_top_to_bottom);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.freeze:
                currOrd = 0;
                ensure.startAnimation(animation_bottom_to_top);
                blackBG.setVisibility(View.VISIBLE);
                ensure.setVisibility(View.VISIBLE);
                tool_image.setImageResource(R.mipmap.tool_freeze);
                tool_content.setText("流星冰冻器: 十秒内手稿保持静止");
                tool_price.setText(200 + "");
                break;
            case R.id.glass:
                currOrd = 1;
                ensure.startAnimation(animation_bottom_to_top);
                blackBG.setVisibility(View.VISIBLE);
                ensure.setVisibility(View.VISIBLE);
                tool_image.setImageResource(R.mipmap.tool_glass);
                tool_content.setText("手稿放大镜: 放大十秒内的手稿图片");
                tool_price.setText(200 + "");
                break;
            case R.id.famousbook:
                currOrd = 2;
                ensure.startAnimation(animation_bottom_to_top);
                blackBG.setVisibility(View.VISIBLE);
                ensure.setVisibility(View.VISIBLE);
                tool_image.setImageResource(R.mipmap.gift_famousbook);
                tool_content.setText("名人手稿兑换券: 集齐一定数量兑换名人手稿");
                tool_price.setText(2000 + "");
                break;
            case R.id.realbook:
                currOrd = 3;
                ensure.startAnimation(animation_bottom_to_top);
                blackBG.setVisibility(View.VISIBLE);
                ensure.setVisibility(View.VISIBLE);
                tool_image.setImageResource(R.mipmap.gift_realbook);
                tool_content.setText("实体书兑换券: 集齐一定数量兑换实体书");
                tool_price.setText(200 + "");
                break;
            case R.id.lendbook:
                currOrd = 4;
                ensure.startAnimation(animation_bottom_to_top);
                blackBG.setVisibility(View.VISIBLE);
                ensure.setVisibility(View.VISIBLE);
                tool_image.setImageResource(R.mipmap.gift_lendbook);
                tool_content.setText("借阅图书兑换券: 集齐一定数量借阅图书");
                tool_price.setText(200 + "");
                break;
            case R.id.ensure:
                break;
            case R.id.black_bg:
                ensure.startAnimation(animation_top_to_bottom);
                blackBG.setVisibility(View.GONE);
                ensure.setVisibility(View.GONE);
                break;
            case R.id.cancel:
                ensure.startAnimation(animation_top_to_bottom);
                blackBG.setVisibility(View.GONE);
                ensure.setVisibility(View.GONE);
                break;
            case R.id.buy:
                ensure.startAnimation(animation_top_to_bottom);
                buy_method();
                blackBG.setVisibility(View.GONE);
                ensure.setVisibility(View.GONE);
                break;

        }
    }

    private void buy_method() {
        if (price[currOrd]*currNum > myAccount.getStar()) {
            Toast.makeText(MallActivity.this, "星星不够哦.", Toast.LENGTH_LONG).show();
        } else {
            myAccount.decStar(price[currOrd]*currNum);
            star_num.setText(myAccount.getStar() + "");
            Toast.makeText(MallActivity.this, "购买成功.", Toast.LENGTH_LONG).show();
        }
    }


}

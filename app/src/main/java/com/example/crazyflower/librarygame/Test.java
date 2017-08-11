package com.example.crazyflower.librarygame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by CrazyFlower on 2017/8/11.
 */

public class Test extends AppCompatActivity{
    private ImageView circleHorizental;
    private Animation animation;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        Window window = getWindow();
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        setContentView(R.layout.test);
        circleHorizental = (ImageView) findViewById(R.id.circle_horizental);
        animation = AnimationUtils.loadAnimation(this, R.anim.horizental_big_light);
        circleHorizental.startAnimation(animation);
    }
}

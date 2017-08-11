package com.example.crazyflower.librarygame;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by CrazyFlower on 2017/8/9.
 */

public class ReturnSuggestion extends AppCompatActivity implements View.OnClickListener {



    private LinearLayout suggestion;
    private LinearLayout back;
    private EditText suggestion_content;
    private TextView backHistory0;
    private TextView backHistory1;
    private List backList;
    private EditText searchContent;
    private TextView backHistory;
    private Account myAccount;
    private ImageView backBt;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("returnsugg", "???");
        Window window = getWindow();
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        setContentView(R.layout.return_sugg);
        myAccount = Account.getInstance();
        backList = myAccount.getBackList();
        initViews();
    }

    private void initViews() {
        back = (LinearLayout) findViewById(R.id.back);
        suggestion = (LinearLayout) findViewById(R.id.suggestion);
        suggestion_content = (EditText) findViewById(R.id.suggestion_content);
        backHistory0 = (TextView) findViewById(R.id.back_history_0);
        backHistory1 = (TextView) findViewById(R.id.back_history_1);
        back.setOnClickListener(this);
        suggestion.setOnClickListener(this);
        backBt = (ImageView) findViewById(R.id.returnsugg_backbt);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.suggestion:
                suggestion_content.setVisibility(View.VISIBLE);
                backHistory0.setVisibility(View.INVISIBLE);
                backHistory1.setVisibility(View.INVISIBLE);
                break;
            case R.id.back:
                backHistory0.setVisibility(View.VISIBLE);
                backHistory0.setText(backList.get(backList.size() - 1).toString());
                backHistory1.setVisibility(View.VISIBLE);
                backHistory1.setText(backList.get(backList.size() - 2).toString());
                suggestion_content.setVisibility(View.INVISIBLE);
                break;
            case R.id.returnsugg_backbt:
                finish();
            default:
                break;
        }
    }
}

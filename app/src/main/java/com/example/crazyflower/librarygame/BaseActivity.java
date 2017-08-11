package com.example.crazyflower.librarygame;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by CrazyFlower on 2017/8/8.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView realbookGet;
    private ImageView famousbookGet;
    private ImageView lendbookGet;
    private TextView realbookExchange;
    private TextView famousbookExchange;
    private TextView lendbookExchange;
    private TextView realbookNum;
    private TextView famousbookNum;
    private TextView lendbookNum;
    private Account myAccount;
    private ImageView backBt;


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
        setContentView(R.layout.base);
        myAccount = Account.getInstance();
        initView();
    }
    public void onStart() {
        super.onStart();
        Log.i("12345", myAccount.getStar() + "");
    }

    private void initView() {
        realbookGet = (ImageView) findViewById(R.id.realbook_get);
        famousbookGet = (ImageView) findViewById(R.id.famousbook_get);
        lendbookGet = (ImageView) findViewById(R.id.lendbook_get);

        realbookExchange = (TextView) findViewById(R.id.realbook_exchange);
        famousbookExchange = (TextView) findViewById(R.id.famousbook_exchange);
        lendbookExchange = (TextView) findViewById(R.id.lendbook_exchange);

        realbookNum = (TextView) findViewById(R.id.realbook_num);
        famousbookNum = (TextView) findViewById(R.id.famousbook_num);
        lendbookNum = (TextView) findViewById(R.id.lendbook_num);

        if (myAccount.getFamousbook_num() > 0) {
            famousbookGet.setImageResource(R.mipmap.icon_havenotgot);
            famousbookExchange.setVisibility(View.VISIBLE);
            famousbookNum.setVisibility(View.VISIBLE);
            famousbookNum.setText("x" + myAccount.getFamousbook_num());
        }
        if (myAccount.getRealbook_num() > 0) {
            realbookGet.setImageResource(R.mipmap.icon_havenotgot);
            realbookExchange.setVisibility(View.VISIBLE);
            realbookNum.setVisibility(View.VISIBLE);
            realbookNum.setText("x" + myAccount.getRealbook_num());
        }
        if (myAccount.getLendbook_num() > 0) {
            lendbookGet.setImageResource(R.mipmap.icon_havenotgot);
            lendbookExchange.setVisibility(View.VISIBLE);
            lendbookNum.setVisibility(View.VISIBLE);
            lendbookNum.setText("x" + myAccount.getLendbook_num());
        }

        backBt = (ImageView) findViewById(R.id.base_back);
        backBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base_back:
                finish();
                break;
            default:
                break;
        }
    }
}

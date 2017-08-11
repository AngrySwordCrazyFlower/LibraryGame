package com.example.crazyflower.librarygame;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

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
    private String result;
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
        backBt.setOnClickListener(this);
        suggestion_content.setImeOptions(EditorInfo.IME_ACTION_SEND);
        suggestion_content.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    Log.i("Returnsugg", "12344");
                    final String content = suggestion_content.getText().toString();
                    Thread t1 = new Thread() {
                        public void run() {
                            try {
                                //请求数据
                                HttpClient hc = new DefaultHttpClient();
                                HttpPost hp = new HttpPost(
                                        "http://115.159.92.219:2000/api/suggestion");
                                //请求json报文
                                JSONObject jo = new JSONObject();
                                jo.put("username", myAccount.getName());
                                jo.put("suggestion", content);

                                hp.setEntity(new StringEntity(jo.toString(), "UTF-8"));
                                HttpResponse hr = hc.execute(hp);
                                result = null;
                                //获取返回json报文
                                if (hr.getStatusLine().getStatusCode() == 200) {
                                    result = EntityUtils.toString(hr.getEntity());
                                    Log.d("sugg", "result : " + result);
                                }
                                //关闭连接
                                if (hc != null) {
                                    hc.getConnectionManager().shutdown();
                                    Log.i("sugg", "eee");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.i("sugg", "error");
                            }
                        }
                    };
                    t1.start();
                    suggestion_content.setText("");
                    suggestion_content.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });
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
                myAccount.getList();
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

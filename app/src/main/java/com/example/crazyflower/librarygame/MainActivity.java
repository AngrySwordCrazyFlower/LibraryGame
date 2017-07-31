package com.example.crazyflower.librarygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Rank> rankList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatas();
        RankAdapter adapter = new RankAdapter(MainActivity.this, R.layout.rank_item, rankList);
        ListView listView = (ListView) findViewById(R.id.ranklist_content);
        listView.setAdapter(adapter);
        final ImageView imageView = (ImageView) findViewById(R.id.menu);
        final LinearLayout layout = (LinearLayout) findViewById(R.id.menu_spread) ;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);
            }
        });
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageView.setVisibility(View.VISIBLE);
                layout.setVisibility(View.GONE);
            }
        });
        final ImageView bt_setting = (ImageView) findViewById(R.id.image_setting);
        bt_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MainActivity.class", "123");
            }
        });
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

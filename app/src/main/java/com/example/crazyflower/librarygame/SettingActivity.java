package com.example.crazyflower.librarygame;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * Created by CrazyFlower on 2017/8/5.
 */

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private SeekBar seekBar;
    private AudioManager am;
    private VolumeReceiver volumeReceiver;
    private MusicCircle musicCircle;
    private int maxVolume;
    private ImageView sharePic;
    private ImageView returnSugg;
    private ImageView aboutUs;


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
        setContentView(R.layout.setting);
        initView();

        volumeReceiver = new VolumeReceiver();
        IntentFilter filter = new IntentFilter() ;
        filter.addAction("android.media.VOLUME_CHANGED_ACTION") ;
        registerReceiver(volumeReceiver, filter) ;

    }



    private void initView() {
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        musicCircle = (MusicCircle) findViewById(R.id.music_circle);
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //获取系统最大音量
        maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        seekBar.setMax(maxVolume);
        //获取当前音量
        int currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        seekBar.setProgress(currentVolume);
        musicCircle.setPercen((float) currentVolume / maxVolume);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    //设置系统音量
                    am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                    int currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
                    seekBar.setProgress(currentVolume);
                    musicCircle.setPercen((float) currentVolume / maxVolume);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        sharePic = (ImageView) findViewById(R.id.sharepic);
        aboutUs = (ImageView) findViewById(R.id.aboutus);
        returnSugg = (ImageView) findViewById(R.id.returnsgg);
        aboutUs.setOnClickListener(this);
        returnSugg.setOnClickListener(this);
    }




    public boolean dispatchKeyEvent(KeyEvent event) {
        super.dispatchKeyEvent(event);
        if (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN) {
            am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, 0);
        }else if (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP) {
            am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, 0);
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(volumeReceiver);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.sharepic:
                break;
            case R.id.aboutus:
                intent = new Intent(SettingActivity.this, AboutUs.class);
                startActivity(intent);
                break;
            case R.id.returnsgg:
                intent = new Intent(SettingActivity.this, ReturnSuggestion.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private class VolumeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")){
                int currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
                seekBar.setProgress(currentVolume);
                musicCircle.setPercen((float) currentVolume / maxVolume);
            }
        }
    }
}

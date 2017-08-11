package com.example.crazyflower.librarygame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by CrazyFlower on 2017/7/27.
 */

public class Circle extends View {

    private Paint paint_circle = new Paint();
    private Paint paint_border = new Paint();
    private Paint paint_wave = new Paint();
    private Paint paint_text_black = new Paint();
    private Paint paint_text_white = new Paint();
    private double angle = 0;
    private int startColor = Color.argb(210, 160, 240, 200);
    private int endColor = Color.argb(210, 60, 180, 200);
    private int firstCount = 1;
    private Shader linearGradient = new LinearGradient(0, 0, 400, 400, startColor, endColor, Shader.TileMode.CLAMP);
    private Context mContext;
    private Intent mIntent;
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {
                angle += 1;
                if (angle > 90)
                    angle = 0;
                firstCount += 2;
                if (firstCount > 100)
                    firstCount = 1;
                invalidate();
                handler.sendEmptyMessageDelayed(1, 100);
            } else {
            }
        };
    };




    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mIntent = new Intent();
        initPaint();
        handler.sendEmptyMessage(1);
    }

    private void initPaint() {

        paint_circle.setShader(linearGradient);

        paint_border.setColor(Color.argb(255, 74, 219, 222));
        paint_border.setStrokeWidth(2);
        paint_border.setStyle(Paint.Style.STROKE);

        paint_wave.setColor(Color.WHITE);
        paint_wave.setStyle(Paint.Style.STROKE);

        paint_text_black.setTextSize(75);
        paint_text_black.setColor(Color.argb(200, 0, 0, 0));

        paint_text_white.setTextSize(75);
        paint_text_white.setColor(Color.argb(60, 255, 255, 255));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        double angle2 = (angle + 30) % 90;
        double angle3 = (angle + 60) % 90;
        int y1 = 1080 - (int) (Math.cos(Math.PI*angle/180)*880);
        int x1 = 1080 - (int) (Math.sin(Math.PI*angle/180)*880);
        int y2 = 1080 - (int) (Math.cos(Math.PI*angle2/180)*880);
        int x2 = 1080 - (int) (Math.sin(Math.PI*angle2/180)*880);
        int y3 = 1080 - (int) (Math.cos(Math.PI*angle3/180)*880);
        int x3 = 1080 - (int) (Math.sin(Math.PI*angle3/180)*880);
        int radius = 40* firstCount / 100;
        if ((angle > 20) && (angle <= 50)) {
            canvas.drawCircle(x1, y1, 200, paint_circle);
            canvas.drawCircle(x1, y1, 200 + radius, paint_wave);
            canvas.drawText("星 海", x1 - 78, y1 - 20, paint_text_black);
            canvas.drawText("拾 遗", x1 - 78, y1 + 75, paint_text_black);
        } else {
            canvas.drawCircle(x1, y1, 200, paint_border);
            canvas.drawText("星 海", x1 - 78, y1 - 20, paint_text_white);
            canvas.drawText("拾 遗", x1 - 78, y1 + 75, paint_text_white);
        }
        if ((angle2 > 20) && (angle2 <= 50)) {
            canvas.drawCircle(x2, y2, 200, paint_circle);
            canvas.drawCircle(x2, y2, 200 + radius, paint_wave);
            canvas.drawText("解 锁", x2 - 78, y2 - 20, paint_text_black);
            canvas.drawText("星 空", x2 - 78, y2 + 75, paint_text_black);
        } else {
            canvas.drawCircle(x2, y2, 200, paint_border);

            canvas.drawText("解 锁", x2 - 78, y2 - 20, paint_text_white);
            canvas.drawText("星 空", x2 - 78, y2 + 75, paint_text_white);
        }
        if ((angle3 > 20) && (angle3 <= 50)) {
            canvas.drawCircle(x3, y3, 200, paint_circle);
            canvas.drawCircle(x3, y3, 200 + radius, paint_wave);
            canvas.drawText("揭 秘", x3 - 78, y3 - 20, paint_text_black);
            canvas.drawText("星 图", x3 - 78, y3 + 75, paint_text_black);
        } else {
            canvas.drawCircle(x3, y3, 200, paint_border);
            canvas.drawText("揭 秘", x3 - 78, y3 - 20, paint_text_white);
            canvas.drawText("星 图", x3 - 78, y3 + 75, paint_text_white);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        double angle2 = (angle + 30) % 90;
        double angle3 = (angle + 60) % 90;
        int y1 = 1080 - (int) (Math.cos(Math.PI*angle/180)*880);
        int x1 = 1080 - (int) (Math.sin(Math.PI*angle/180)*880);
        int y2 = 1080 - (int) (Math.cos(Math.PI*angle2/180)*880);
        int x2 = 1080 - (int) (Math.sin(Math.PI*angle2/180)*880);
        int y3 = 1080 - (int) (Math.cos(Math.PI*angle3/180)*880);
        int x3 = 1080 - (int) (Math.sin(Math.PI*angle3/180)*880);
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if ((x - x1)*(x - x1) + (y - y1)*(y - y1) < 200*200) {
                    //星海拾遗的ac
                    //mIntent.setClass(mContext, BaseActivity.class);
                    Log.i("Circle", "1");
                    mContext.startActivity(mIntent);
                }
                if ((x - x2)*(x - x2) + (y - y2)*(y - y2) < 200*200) {
                    //解锁星图的ac
                    mIntent.setClass(mContext, BaseActivity.class);
                    Log.i("Circle", "2");
                    mContext.startActivity(mIntent);
                }
                if ((x - x3)*(x - x3) + (y - y3)*(y - y3) < 200*200) {
                    //揭秘星图的ac
                    mIntent.setClass(mContext, BaseActivity.class);
                    Log.i("Circle", "3");
                    mContext.startActivity(mIntent);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}

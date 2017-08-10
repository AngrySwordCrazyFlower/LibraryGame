package com.example.crazyflower.librarygame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by CrazyFlower on 2017/7/27.
 */

public class Circle extends View {

    private Paint paint_circle = new Paint();
    private Paint paint_border = new Paint();
    private Paint paint_wave = new Paint();
    private double angle = 0;
    private int startColor = Color.argb(210, 160, 240, 200);
    private int endColor = Color.argb(210, 60, 180, 200);
    private int firstCount = 1;
    private Shader linearGradient = new LinearGradient(0, 0, 400, 400, startColor, endColor, Shader.TileMode.CLAMP);
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
        } else {
            canvas.drawCircle(x1, y1, 200, paint_border);
        }
        if ((angle2 > 20) && (angle2 <= 50)) {
            canvas.drawCircle(x2, y2, 200, paint_circle);
            canvas.drawCircle(x2, y2, 200 + radius, paint_wave);
        } else {
            canvas.drawCircle(x2, y2, 200, paint_border);
        }
        if ((angle3 > 20) && (angle3 <= 50)) {
            canvas.drawCircle(x3, y3, 200, paint_circle);
            canvas.drawCircle(x3, y3, 200 + radius, paint_wave);
        } else {
            canvas.drawCircle(x3, y3, 200, paint_border);
        }
    }
}

package com.example.crazyflower.librarygame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by CrazyFlower on 2017/8/5.
 */

public class MusicCircle extends View {

    private float percen = 0;
    private Paint paint = new Paint();

    public MusicCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint.setColor(Color.argb(20, 212, 212, 212));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.argb(20, 212, 212, 212));
        canvas.drawCircle(width / 2, height / 2, percen / 4 * width + width / 4, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.argb(100, 255, 255, 255));
        paint.setStrokeWidth(3f);
        canvas.drawCircle(width / 2, height / 2, percen / 4 * width + width / 4 - 2 , paint);
    }

    public void setPercen(float percen) {
        this.percen = percen;
        invalidate();
    }
}

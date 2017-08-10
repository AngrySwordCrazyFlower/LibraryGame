package com.example.crazyflower.librarygame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by CrazyFlower on 2017/8/9.
 */

public class TextXINGXINGRENZHE extends android.support.v7.widget.AppCompatTextView {
    private Paint myPaint;
    private LinearGradient myLinearGradient;

    public TextXINGXINGRENZHE(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int mViewWidth = getMeasuredWidth();
        int mViewHeight = getMeasuredHeight();
        Log.i("" + mViewHeight, "" + mViewWidth);
        myLinearGradient = new LinearGradient(0, 0, mViewWidth, mViewHeight, Color.argb(100, 61, 172, 192), Color.argb(100, 132, 212, 190), Shader.TileMode.CLAMP);
        myPaint = getPaint();
        myPaint.setShader(myLinearGradient);
        Paint.FontMetricsInt fontMetricsInt = myPaint.getFontMetricsInt();
        int baseline = (mViewHeight - fontMetricsInt.bottom - fontMetricsInt.top) / 2;
        canvas.drawText(getText().toString(), 0, baseline, myPaint);
    }
}

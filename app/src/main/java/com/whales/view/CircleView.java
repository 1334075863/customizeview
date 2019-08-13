package com.whales.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created on 2019/07/27
 *
 * @author whales
 */
public class CircleView extends View {

   private Paint mPaint;

    public CircleView(Context context) {
        super(context);
        initPaint();
    }

    public CircleView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CircleView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initPaint(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5f);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int r = Math.min(width,height)/2;
        int r2 = Math.min(width,height)/3;
        canvas.drawCircle(width/2,height/2,r,mPaint);
        //同心圆
        canvas.drawCircle(width/2,height/2,r2,mPaint);
        //左侧圆
        canvas.drawCircle(width/3,height/2,r2,mPaint);
        //左侧圆
        canvas.drawCircle(getWidth()-width/3,height/2,r2,mPaint);
    }
}

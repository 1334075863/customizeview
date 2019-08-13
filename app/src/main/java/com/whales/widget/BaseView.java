package com.whales.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created on 2019/08/13
 *
 * @author whales
 */
public abstract class BaseView extends View {

    private String TAG = this.getClass().getSimpleName();

    //坐标系画笔
    private Paint mCoordinatePaint;
    //网格线画笔
    private Paint mGridPaint;
    //写字画笔
    private Paint mTextPaint;

    //坐标颜色
    private int mCoordinateColor;
    private int mGridColor;

    //网格宽度
    private int mGridWidth = 50;

    //坐标系宽度
    private final float mCoordinateLineWidth = 2.5f;
    //网格宽度
    private final float mGridLineWidth = 1f;

    private float mTextSize;

    private final float mCoordinateFlagHeight = 8f;

    protected float mWidth;
    protected float mHeight;

    public BaseView(Context context) {
        super(context);
    }

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCoordinate(context);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BaseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initCoordinate(context);
        init(context);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

    }

    private void initCoordinate(Context context){
        mCoordinateColor = Color.BLACK;
        mGridColor = Color.LTGRAY;

        mTextSize = spToPx(10);

        mCoordinatePaint = new Paint();
        mCoordinatePaint.setAntiAlias(true);
        mCoordinatePaint.setColor(mCoordinateColor);
        mCoordinatePaint.setStrokeWidth(mCoordinateLineWidth);

        mGridPaint = new Paint();
        mGridPaint.setAntiAlias(true);
        mGridPaint.setColor(mGridColor);
        mGridPaint.setStrokeWidth(mGridLineWidth);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mCoordinateColor);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(mTextSize);
    }

    protected abstract void init(Context context);

    protected void drawCoordinate(Canvas canvas){
        float halfWidth = mWidth/2;
        float halfHeight = mHeight/2;

        //画网格线
        canvas.save();
        canvas.translate(halfWidth,halfHeight);
        int curWidth = mGridWidth;
        //画横线
        while (curWidth <halfWidth + mGridWidth){
            //向右画
            canvas.drawLine(curWidth,-halfHeight,curWidth,halfHeight,mGridPaint);
            //向左画
            canvas.drawLine(-curWidth,-halfHeight,-curWidth,halfHeight,mGridPaint);

            //画标注
            canvas.drawLine(curWidth,0,curWidth,-mCoordinateFlagHeight,mCoordinatePaint);
            canvas.drawLine(-curWidth,0,-curWidth,-mCoordinateFlagHeight,mCoordinatePaint);

            //标注宽度(每两个画一个)
            if (curWidth % (mGridWidth * 2) == 0){
                canvas.drawText(curWidth + "",curWidth,mTextSize*1.5f,mTextPaint);
                canvas.drawText(-curWidth+  "",-curWidth,mTextSize * 1.5f,mTextPaint);
            }

            curWidth += mGridWidth;
        }

        int curHeight = mGridWidth;
        while (curHeight < halfHeight +mGridWidth){
            //向右画
            canvas.drawLine(-halfWidth,curHeight,halfWidth,curHeight,mGridPaint);
            //向左画
            canvas.drawLine(-halfWidth,-curHeight,halfWidth,-curHeight,mGridPaint);

            //画标注
            canvas.drawLine(0,curHeight,mCoordinateFlagHeight,curHeight,mCoordinatePaint);
            canvas.drawLine(0,-curHeight,mCoordinateFlagHeight,-curHeight,mCoordinatePaint);

            //标注宽度
            if (curHeight % (mGridWidth * 2) == 0){
                canvas.drawText(curHeight+"",-mTextSize * 2,curHeight+mTextSize/2,mTextPaint);
                canvas.drawText(-curHeight + "",-mTextSize/2,-curHeight+mTextSize/2,mTextPaint);
            }
            curHeight += mGridWidth;
        }
        canvas.restore();

        //画x，y轴
        canvas.drawLine(halfWidth,0,halfWidth,mHeight,mCoordinatePaint);
        canvas.drawLine(0,halfHeight,mWidth,halfHeight,mCoordinatePaint);
    }


    /**
     * 转换sp 至 px
     * @param spValue  sp值
     * @return  px值
     */
    protected int spToPx(float spValue){
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 转换 dp 至 px
     * @param dpValue dp值
     * @return px值
     */
    protected int dpToPx(float dpValue){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return (int) (dpValue * metrics.density + 0.5f);
    }
}

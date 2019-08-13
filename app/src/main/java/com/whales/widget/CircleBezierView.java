package com.whales.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/08/13
 *
 * @author whales
 */
public class CircleBezierView extends BaseView{
    //圆的中心点
    private PointF mcenterPoints;
    //圆半径
    private float mRadius;

    //控制点列表，顺序为:右上、右下、左下、左上
    private List<PointF> mConctrolPointList;

    //控制点占半径的比例
    private float mRatio;

    //圆的路径
    private Path mPath;
    //绘制贝塞尔曲线的画笔
    private Paint mPaint;
    //绘制圆的画笔
    private Paint mCirclePaint;
    //绘制控制线的画笔
    private Paint mLinePaint;

    //控制线的颜色
    private int[] mLineColor;

    //线的宽度
    private int LINE_WIDTH;

    @Override
    protected void init(Context context) {
        int width = context.getResources().getDisplayMetrics().widthPixels;

        mRadius = width/2;
        LINE_WIDTH = dpToPx(2);

        mcenterPoints = new PointF(0,0);

        mConctrolPointList = new ArrayList<>();

        mPath = new Path();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#1296db"));

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(LINE_WIDTH);
        mCirclePaint.setColor(Color.RED);

        mRatio = 0.55f;

        mLineColor = new int[4];
        mLineColor[0] = Color.parseColor("#f4ea2a");
        mLineColor[1] = Color.parseColor("#1afa29");
        mLineColor[2] = Color.parseColor("#efb336");
        mLineColor[3] = Color.parseColor("#e89abe");

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(dpToPx(2));
    }

    public CircleBezierView(Context context) {
        super(context);
    }

    public CircleBezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleBezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircleBezierView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCoordinate(canvas);

        canvas.translate(mWidth/2,mHeight/2);
        mPath.reset();

        for (int i = 0;i < 4;i++){
            if (i == 0){

            }
        }
    }

    /**
     * 计算圆的控制点
     */
    private void calculateControlPoint(){
        //计算 中间控制点到端点的距离
        float controlWidth = mRatio * mRadius;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    /**
     * 设置比例
     * @param ratio
     */
    public void setRatio(float ratio){
        this.mRatio = ratio;

    }
}

package main.gooleplay.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import main.gooleplay.R;

/**
 * Created by wanghua on 2017/2/13.
 */
public class CircleBubble extends View {
    private static final int DEFAULT_CIRCEL_COLOR = Color.RED;
    private static final int DEFAULT_SQUARE_COLOR = Color.WHITE;
    /**
     * 圆圈下的文字
     */
    private String text;
    /**
     * 圆圈的颜色
     */
    private int circleColor;
    /**
     * 文字颜色
     */
    private int textColor;
    /**
     * 圆圈的半径
     */
    private int circleRadius;
    /**
     * 活动正方形的边长
     */
    private int squareWidth;
    /**
     * 画笔
     */
    private Paint mpaint;
    private float speedX = 1;
    private float speedY = 1;
    private float changeX = 1;
    private float changeY = 1;
    private float currentX;
    private float currentY;

    public CircleBubble(Context context) {
        this(context, null);
    }

    public CircleBubble(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleBubble(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleBubble, defStyleAttr, 0);
        text = array.getString(R.styleable.CircleBubble_circleText);
        circleColor = array.getColor(R.styleable.CircleBubble_circleColor, DEFAULT_CIRCEL_COLOR);
        textColor = array.getColor(R.styleable.CircleBubble_textColor, DEFAULT_SQUARE_COLOR);
        circleRadius = (int) array.getDimension(R.styleable.CircleBubble_circleRadius, 60);
        squareWidth = (int) array.getDimension(R.styleable.CircleBubble_squareWidth, 300);
        array.recycle();
        init();
    }

    private void init() {
        mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setTextSize(20);
        mpaint.setStrokeWidth(2);
        mpaint.setColor(circleColor);
        currentX = squareWidth / 2;
        currentY = squareWidth / 2;
        speedX = (float) (Math.random() * 2) - 0.5f;
        speedY = (float)(Math.random() * 2) - 0.5f;
      //  changeX = speedX;
      //  changeY = speedY;
        Log.i("=====", "speedX===========" + speedX + "speedY=========" + speedY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        long startTime = System.currentTimeMillis();
        mpaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(currentX, currentY, circleRadius, mpaint);
        canvas.drawText(text, setCenterText(text, mpaint), currentY + circleRadius + 20, mpaint);
        currentX = currentX + speedX;
        currentY = currentY + speedY;
        if (currentX > squareWidth - circleRadius) {
            speedX = -speedX;
           /* if (speedX > 0 && changeX > 0 || speedX < 0 && changeX < 0) {

            } else {
                speedX = changeX;
            }*/
        }
        if (currentX < circleRadius) {
            speedX = -speedX;
           /* if (speedX > 0 && changeX > 0 || speedX < 0 && changeX < 0) {

            } else {
                speedX = changeX;
            }*/
        }
        if (currentY > squareWidth - circleRadius - 20) {
            speedY = -speedY;
           /* if (speedY > 0 && changeY > 0 || speedY < 0 && changeY < 0) {
                speedY = -changeY;
            } else {
                speedY = changeY;
            }*/
        }
        if (currentY < circleRadius) {
            speedY = -speedY;
         /*   if (speedY > 0 && changeY > 0 || speedY < 0 && changeY < 0) {
                speedY = -changeY;
            } else {
                speedY = changeY;
            }*/
        }
       // changeX = (float) Math.abs( (Math.random() * 2) - 0.5f);
      //  changeY =(float) Math.abs( (Math.random() * 2) - 0.5f);
        long stopTime = System.currentTimeMillis();
        long runTime = stopTime - startTime;
        // 100毫秒执行一次
        postInvalidateDelayed(Math.abs(runTime - 100));
    }


    /**
     * 设置画的字体居中位置
     */
    private float setCenterText(String text, Paint paint) {
        return currentX - (paint.measureText(text)) / 2;
    }

    /**
     * 获取字体高度
     */
    public int getFontHeight() {
        Paint.FontMetrics fontMetrics = mpaint.getFontMetrics();
        return (int) (Math.ceil(fontMetrics.descent - fontMetrics.top) + 2);
    }
}

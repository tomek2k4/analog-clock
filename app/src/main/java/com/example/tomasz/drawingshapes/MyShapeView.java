package com.example.tomasz.drawingshapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by tomasz on 14.06.2015.
 */
public class MyShapeView extends View {

    private Paint framePaint = new Paint();
    private Paint whitePaint = new Paint();
    private Paint circlePaint = new Paint();
    private Paint rectanglePaint = new Paint();
    Paint framePressedPaint = new Paint();

    private boolean isPressed = false;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int W = canvas.getWidth();
        final int H = canvas.getHeight();
        final int S = (W<H)?W:H;

        Paint p = null;
        if(isPressed){
            p = framePressedPaint;
        }else {
            p = framePaint;
        }

        canvas.drawRect(5, 5, W - 5, H - 5, p);
        canvas.drawRect(8, 8, W - 8, H - 8, whitePaint);
        canvas.drawCircle(W / 4, H / 2, S / 5, circlePaint);
        canvas.drawRect(3*W / 4 - S / 5, H / 2 - S / 5,3* W / 4 + S / 5, H / 2 + S / 5, rectanglePaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                isPressed = true;
                invalidate();

                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_UP:
                isPressed = false;
                invalidate();
                break;
        }

        return isPressed;
    }


    public MyShapeView(Context context) {
        super(context);
        framePaint.setColor(0xFF888888);
        framePaint.setStyle(Paint.Style.STROKE);
        framePaint.setStrokeWidth(6);

        circlePaint.setColor(0xFFFF0000);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(3);

        rectanglePaint.setColor(0xFF0000FF);
        rectanglePaint.setStyle(Paint.Style.STROKE);
        rectanglePaint.setStrokeWidth(4);


        framePressedPaint.setColor(Color.GREEN);
        framePressedPaint.setStyle(Paint.Style.STROKE);
        framePressedPaint.setStrokeWidth(10);

        whitePaint.setColor(0xFFFFFFFF);
    }

    public MyShapeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public MyShapeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyShapeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }



}

package com.example.tomasz.drawingshapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Calendar;

/**
 * Created by tomasz on 14.06.2015.
 */
public class MyAnalogClock extends View {

    Paint circlePaint = new Paint();
    Paint hourArmPaint = new Paint();
    Paint minuteArmPaint = new Paint();
    Paint secondArmPaint = new Paint();
    Paint smallCirclePaint = new Paint();

    private boolean isPressed = false;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int W = canvas.getWidth();
        final int H = canvas.getHeight();
        final int S = (W<H)?W:H;

        final int R = S/4;
        final int x0 = W / 2;
        final int y0 = H/2;

        final int armHourLength = 2*R/3;
        final int armMinuteLength = R-R/5;
        final int armSecondLength = R-R/6;
        final int smallCircleR = R/12;

        canvas.drawCircle(x0, y0, R, circlePaint);

        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR) % 12;
        int min = c.get(Calendar.MINUTE);
        int sec = c.get(Calendar.SECOND);

        float alpha = (2*(float)Math.PI / 12) * (hour + min/60);
        float beta = (2*(float)Math.PI / 60) * (min);
        float gamma = (2*(float)Math.PI / 60) * (sec);

        canvas.drawLine(x0,y0, (float)( x0 + (armHourLength * Math.sin(alpha))), (float)(y0 - (armHourLength * Math.cos(alpha))),hourArmPaint);
        canvas.drawLine(x0,y0, (float)( x0 + (armMinuteLength * Math.sin(beta))), (float)(y0 - (armMinuteLength * Math.cos(beta))),minuteArmPaint);

        canvas.drawLine(x0,y0, (float)( x0 + (armSecondLength * Math.sin(gamma))), (float)(y0 - (armSecondLength * Math.cos(gamma))),secondArmPaint);

        float katKolek0 = 2*(float)Math.PI / 12;

        for (int i=0;i<12;i++){
            canvas.drawCircle((float)( x0 + (R * Math.sin(katKolek0*i))),(float)(y0 - (R * Math.cos(katKolek0*i))),smallCircleR ,smallCirclePaint);
        }


    }

    public MyAnalogClock(Context context) {
        super(context);

        circlePaint.setColor(0x00000000);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(3);

        hourArmPaint.setColor(Color.BLACK);
        hourArmPaint.setStrokeWidth(4);

        minuteArmPaint.setColor(Color.BLACK);
        minuteArmPaint.setStrokeWidth(3);

        secondArmPaint.setColor(Color.RED);
        minuteArmPaint.setStrokeWidth(2);

        smallCirclePaint.setColor(Color.BLACK);
        smallCirclePaint.setStyle(Paint.Style.STROKE);
        smallCirclePaint.setStrokeWidth(10);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                isPressed = true;
                invalidate();
                return isPressed;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_UP:
                isPressed = false;
                invalidate();
                break;
        }

        return isPressed;
    }

    public MyAnalogClock(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyAnalogClock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyAnalogClock(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}

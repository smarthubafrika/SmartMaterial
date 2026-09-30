package com.smarthub.smartmaterial.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class SmartCircularProgress extends View {
    private final Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private float progress=0.7f;
    private boolean indeterminate;
    private int progressColor=Color.rgb(63,81,181);
    private int trackColor=Color.rgb(225,226,230);
    private float stroke=5f;

    public SmartCircularProgress(Context context){super(context); paint.setStrokeCap(Paint.Cap.ROUND);}
    public SmartCircularProgress setProgress(float value){progress=Math.max(0f,Math.min(1f,value));indeterminate=false;invalidate();return this;}
    public SmartCircularProgress setIndeterminate(boolean value){indeterminate=value;invalidate();return this;}
    public SmartCircularProgress setProgressColor(int color){progressColor=color;invalidate();return this;}
    public SmartCircularProgress setTrackColor(int color){trackColor=color;invalidate();return this;}
    public SmartCircularProgress setStrokeWidth(float dp){stroke=dp;invalidate();return this;}
    @Override protected void onDraw(Canvas c){
        super.onDraw(c); float sw=dp(stroke), r=Math.min(getWidth(),getHeight())/2f-sw*2;
        float cx=getWidth()/2f,cy=getHeight()/2f;
        paint.setStyle(Paint.Style.STROKE);paint.setStrokeWidth(sw);paint.setColor(trackColor);
        c.drawCircle(cx,cy,r,paint);paint.setColor(progressColor);
        if(indeterminate){long t=System.currentTimeMillis()%1200;float start=(t/1200f)*360f;c.drawArc(cx-r,cy-r,cx+r,cy+r,start,100,false,paint);postInvalidateDelayed(16);}
        else c.drawArc(cx-r,cy-r,cx+r,cy+r,-90,360f*progress,false,paint);
    }
    @Override protected void onMeasure(int w,int h){int s=dp(56);setMeasuredDimension(resolveSize(s,w),resolveSize(s,h));}
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
}
package com.smarthub.smartmaterial.divider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class SmartDivider extends View {
    private final Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private int color=0xFFE1E2E6;
    private float thickness=1f;
    public SmartDivider(Context c){super(c);init();}
    public SmartDivider(Context c,AttributeSet a){super(c,a);init();}
    public SmartDivider(Context c,AttributeSet a,int s){super(c,a,s);init();}
    private void init(){paint.setColor(color);}
    public SmartDivider setDividerColor(int c){color=c;invalidate();return this;}
    public SmartDivider setThickness(float dp){thickness=dp;requestLayout();return this;}
    @Override protected void onMeasure(int w,int h){setMeasuredDimension(MeasureSpec.getSize(w),Math.max(1,dp(thickness)));}
    @Override protected void onDraw(Canvas c){paint.setColor(color);c.drawRect(0,0,getWidth(),getHeight(),paint);}
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
}
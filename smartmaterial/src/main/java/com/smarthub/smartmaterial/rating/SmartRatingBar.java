package com.smarthub.smartmaterial.rating;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class SmartRatingBar extends View {
    private final Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private float rating=0f;
    private int max=5;
    private int activeColor=Color.rgb(255,183,77);
    private int inactiveColor=Color.rgb(210,211,215);
    private float starSize=28f;
    private OnRatingChangeListener listener;
    public interface OnRatingChangeListener { void onRatingChanged(float rating); }
    public SmartRatingBar(Context context){super(context);setClickable(true);}
    public SmartRatingBar setRating(float value){rating=Math.max(0,Math.min(max,value));invalidate();return this;}
    public float getRating(){return rating;}
    public SmartRatingBar setMaxRating(int value){max=Math.max(1,value);invalidate();requestLayout();return this;}
    public SmartRatingBar setStarColor(int color){activeColor=color;invalidate();return this;}
    public SmartRatingBar setInactiveColor(int color){inactiveColor=color;invalidate();return this;}
    public SmartRatingBar setStarSize(float dp){starSize=dp;requestLayout();invalidate();return this;}
    public SmartRatingBar setOnRatingChangeListener(OnRatingChangeListener l){listener=l;return this;}
    @Override protected void onMeasure(int w,int h){setMeasuredDimension(resolveSize(dp(starSize*max),w),resolveSize(dp(starSize+8),h));}
    @Override protected void onDraw(Canvas c){super.onDraw(c);paint.setTextSize(dp(starSize));paint.setTypeface(android.graphics.Typeface.DEFAULT);paint.setTextAlign(Paint.Align.LEFT);for(int i=0;i<max;i++){paint.setColor(i+1<=rating?activeColor:inactiveColor);c.drawText("★",i*dp(starSize),dp(starSize),paint);}}
    @Override public boolean onTouchEvent(MotionEvent e){if(e.getAction()==MotionEvent.ACTION_UP){float value=(e.getX()/getResources().getDisplayMetrics().density)/starSize;setRating((float)Math.ceil(value));if(listener!=null)listener.onRatingChanged(rating);return true;}return true;}
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
}
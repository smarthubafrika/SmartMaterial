package com.smarthub.smartmaterial.workflow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SmartStepper extends LinearLayout {
    public SmartStepper(Context c){super(c);setOrientation(HORIZONTAL);setGravity(Gravity.CENTER_VERTICAL);}
    public SmartStepper addStep(CharSequence title){final int index=getChildCount();TextView v=new TextView(getContext());v.setText((index+1)+"  "+title);v.setTextSize(13);v.setGravity(Gravity.CENTER);v.setTextColor(Color.rgb(70,71,76));GradientDrawable d=new GradientDrawable();d.setColor(Color.rgb(242,243,245));d.setCornerRadius(dp(10));v.setBackground(d);addView(v,new LayoutParams(0,dp(48),1));return this;}
    public SmartStepper setCurrentStep(int step){for(int i=0;i<getChildCount();i++){TextView v=(TextView)getChildAt(i);GradientDrawable d=new GradientDrawable();d.setColor(i<=step?Color.rgb(63,81,181):Color.rgb(242,243,245));d.setCornerRadius(dp(10));v.setBackground(d);v.setTextColor(i<=step?Color.WHITE:Color.rgb(70,71,76));}return this;}
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
}
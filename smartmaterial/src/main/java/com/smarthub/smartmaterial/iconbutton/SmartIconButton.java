package com.smarthub.smartmaterial.iconbutton;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageButton;

public class SmartIconButton extends ImageButton {
    private int fillColor=0xFFF0F1F5;
    private float radius=24f;
    public SmartIconButton(Context c){super(c);init();}
    public SmartIconButton(Context c,AttributeSet a){super(c,a);init();}
    public SmartIconButton(Context c,AttributeSet a,int s){super(c,a,s);init();}
    private void init(){setScaleType(ScaleType.CENTER);setGravity(Gravity.CENTER);setClickable(true);setFocusable(true);setMinimumWidth(dp(48));setMinimumHeight(dp(48));setBackground(makeBackground());}
    public SmartIconButton setButtonColor(int c){fillColor=c;setBackground(makeBackground());return this;}
    public SmartIconButton setCornerRadius(float v){radius=v;setBackground(makeBackground());return this;}
    private RippleDrawable makeBackground(){GradientDrawable d=new GradientDrawable();d.setColor(fillColor);d.setCornerRadius(dp(radius));return new RippleDrawable(ColorStateList.valueOf(0x22000000),d,null);}
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
}
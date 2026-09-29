package com.smarthub.smartmaterial.chip;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;
import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartChip extends TextView {
    private boolean selected;
    private float radius=18f;
    public SmartChip(Context c){super(c);init();}
    public SmartChip(Context c,AttributeSet a){super(c,a);init();}
    public SmartChip(Context c,AttributeSet a,int s){super(c,a,s);init();}
    private void init(){setGravity(Gravity.CENTER);setTextSize(14);setTypeface(Typeface.DEFAULT,Typeface.NORMAL);setPadding(dp(14),0,dp(14),0);setMinHeight(dp(36));setClickable(true);setFocusable(true);setBackground(makeBackground());}
    public SmartChip setSelectedState(boolean v){selected=v;setBackground(makeBackground());return this;}
    public boolean isSelectedState(){return selected;}
    public SmartChip setCornerRadius(float v){radius=v;setBackground(makeBackground());return this;}
    private RippleDrawable makeBackground(){GradientDrawable d=new GradientDrawable();d.setColor(selected?SmartTheme.primary(getContext()):0xFFF0F1F5);d.setCornerRadius(dp(radius));return new RippleDrawable(ColorStateList.valueOf(0x22000000),d,null);}
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
}
package com.smarthub.smartmaterial.fab;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.TextView;

import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartFAB extends TextView {
    private int buttonColor=Color.rgb(63,81,181);
    private int iconColor=Color.WHITE;
    private float size=56f;
    public SmartFAB(Context context){super(context);setText("+");setTextSize(28);setGravity(Gravity.CENTER);setTextColor(iconColor);SmartTheme.medium(this);setClickable(true);setFocusable(true);setElevation(dp(6));refresh();}
    public SmartFAB setIconText(CharSequence value){setText(value);return this;}
    public SmartFAB setIconResource(int resId){setCompoundDrawablesWithIntrinsicBounds(resId,0,0,0);return this;}
    public SmartFAB setButtonColor(int color){buttonColor=color;refresh();return this;}
    public SmartFAB setIconColor(int color){iconColor=color;setTextColor(color);return this;}
    public SmartFAB setSize(float dp){size=dp;requestLayout();return this;}
    @Override protected void onMeasure(int w,int h){int s=dp(size);setMeasuredDimension(resolveSize(s,w),resolveSize(s,h));}
    private void refresh(){GradientDrawable d=new GradientDrawable();d.setColor(buttonColor);d.setShape(GradientDrawable.OVAL);setBackground(d);}
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
}
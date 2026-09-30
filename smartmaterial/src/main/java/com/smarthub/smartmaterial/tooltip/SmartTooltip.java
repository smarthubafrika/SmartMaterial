package com.smarthub.smartmaterial.tooltip;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

public final class SmartTooltip {
    private SmartTooltip(){}
    public static PopupWindow show(View anchor, CharSequence message){return show(anchor,message,2500L);}
    public static PopupWindow show(View anchor, CharSequence message, long durationMs){
        Context c=anchor.getContext();TextView t=new TextView(c);t.setText(message);t.setTextColor(Color.WHITE);t.setTextSize(12);t.setPadding(dp(anchor,12),dp(anchor,8),dp(anchor,12),dp(anchor,8));
        GradientDrawable d=new GradientDrawable();d.setColor(Color.rgb(45,47,52));d.setCornerRadius(dp(anchor,8));t.setBackground(d);
        PopupWindow p=new PopupWindow(t,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);p.setOutsideTouchable(true);p.setBackgroundDrawable(d);p.setElevation(dp(anchor,6));p.showAsDropDown(anchor,0,-anchor.getHeight()-dp(anchor,8),Gravity.CENTER_HORIZONTAL);t.postDelayed(p::dismiss,Math.max(500L,durationMs));return p;
    }
    private static int dp(View v,float x){return Math.round(x*v.getResources().getDisplayMetrics().density);}
}
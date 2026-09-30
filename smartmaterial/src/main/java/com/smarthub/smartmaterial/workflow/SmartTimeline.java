package com.smarthub.smartmaterial.workflow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SmartTimeline extends LinearLayout {
    public SmartTimeline(Context c){super(c);setOrientation(VERTICAL);}
    public SmartTimeline addEvent(CharSequence title, CharSequence subtitle){LinearLayout row=new LinearLayout(getContext());row.setOrientation(HORIZONTAL);row.setGravity(Gravity.TOP);TextView dot=new TextView(getContext());dot.setText("●");dot.setTextSize(18);dot.setTextColor(Color.rgb(63,81,181));dot.setGravity(Gravity.CENTER);row.addView(dot,new LayoutParams(dp(28),dp(40)));LinearLayout content=new LinearLayout(getContext());content.setOrientation(VERTICAL);TextView t=new TextView(getContext());t.setText(title);t.setTextSize(15);t.setTextColor(Color.rgb(30,31,36));t.setTypeface(android.graphics.Typeface.DEFAULT_BOLD);content.addView(t,new LayoutParams(-1,-2));TextView s=new TextView(getContext());s.setText(subtitle);s.setTextSize(13);s.setTextColor(Color.rgb(100,101,108));s.setPadding(0,dp(3),0,dp(14));content.addView(s,new LayoutParams(-1,-2));row.addView(content,new LayoutParams(0,-2,1));addView(row,new LayoutParams(-1,-2));return this;}
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
}
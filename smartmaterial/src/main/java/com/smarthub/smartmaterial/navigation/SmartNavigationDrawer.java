package com.smarthub.smartmaterial.navigation;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SmartNavigationDrawer extends LinearLayout {
    public interface OnItemSelectedListener { void onItemSelected(int index, CharSequence title); }
    private OnItemSelectedListener listener;
    public SmartNavigationDrawer(Context c){super(c);setOrientation(VERTICAL);setPadding(dp(20),dp(28),dp(12),dp(20));setBackgroundColor(Color.WHITE);}
    public SmartNavigationDrawer addHeader(CharSequence title, CharSequence subtitle){TextView t=text(title,20);addView(t,new LayoutParams(-1,-2));TextView s=text(subtitle,13);s.setTextColor(Color.rgb(100,101,108));s.setPadding(0,dp(4),0,dp(20));addView(s,new LayoutParams(-1,-2));return this;}
    public SmartNavigationDrawer addItem(CharSequence title){final int index=getChildCount();TextView v=text(title,15);v.setGravity(Gravity.CENTER_VERTICAL);v.setPadding(dp(12),0,dp(8),0);v.setClickable(true);v.setOnClickListener(x->{if(listener!=null)listener.onItemSelected(index,title);});addView(v,new LayoutParams(-1,dp(48)));return this;}
    public SmartNavigationDrawer setOnItemSelectedListener(OnItemSelectedListener l){listener=l;return this;}
    public Dialog show(){final Dialog d=new Dialog(getContext());d.requestWindowFeature(Window.FEATURE_NO_TITLE);d.setContentView(this);Window w=d.getWindow();if(w!=null){w.setBackgroundDrawableResource(android.R.color.transparent);w.setGravity(Gravity.START|Gravity.TOP);w.setLayout(dp(300),-1);}d.show();if(d.getWindow()!=null)d.getWindow().setLayout(dp(300),-1);return d;}
    private TextView text(CharSequence s,float size){TextView v=new TextView(getContext());v.setText(s);v.setTextSize(size);v.setTextColor(Color.rgb(30,31,36));return v;}
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
}
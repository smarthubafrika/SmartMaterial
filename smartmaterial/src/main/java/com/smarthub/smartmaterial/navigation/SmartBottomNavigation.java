package com.smarthub.smartmaterial.navigation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SmartBottomNavigation extends LinearLayout {
    public interface OnItemSelectedListener { void onItemSelected(int index, CharSequence title); }
    private OnItemSelectedListener listener; private int selected=-1;
    public SmartBottomNavigation(Context c){super(c);setOrientation(HORIZONTAL);setGravity(Gravity.CENTER_VERTICAL);setElevation(dp(4));setBackgroundColor(Color.WHITE);}
    public SmartBottomNavigation addItem(CharSequence title){final int index=getChildCount();TextView v=new TextView(getContext());v.setText(title);v.setTextSize(12);v.setGravity(Gravity.CENTER);v.setTextColor(Color.rgb(80,81,86));v.setClickable(true);v.setFocusable(true);v.setOnClickListener(x->{setSelectedItem(index);if(listener!=null)listener.onItemSelected(index,title);});addView(v,new LayoutParams(0,dp(56),1));if(selected<0)setSelectedItem(0);return this;}
    public SmartBottomNavigation setSelectedItem(int index){if(index<0||index>=getChildCount())return this;selected=index;for(int i=0;i<getChildCount();i++){TextView v=(TextView)getChildAt(i);v.setTextColor(i==selected?Color.rgb(63,81,181):Color.rgb(80,81,86));v.setTypeface(android.graphics.Typeface.DEFAULT,i==selected?android.graphics.Typeface.BOLD:android.graphics.Typeface.NORMAL);}return this;}
    public int getSelectedItem(){return selected;}
    public SmartBottomNavigation setOnItemSelectedListener(OnItemSelectedListener l){listener=l;return this;}
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
}
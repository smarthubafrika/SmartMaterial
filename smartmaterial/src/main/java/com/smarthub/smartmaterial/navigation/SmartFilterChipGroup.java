package com.smarthub.smartmaterial.navigation;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SmartFilterChipGroup extends LinearLayout {
    public interface OnFilterSelectedListener { void onFilterSelected(int index, CharSequence title); }
    private OnFilterSelectedListener listener; private int selected=-1;
    public SmartFilterChipGroup(Context c){super(c);setOrientation(HORIZONTAL);setGravity(Gravity.CENTER_VERTICAL);}
    public SmartFilterChipGroup addFilter(CharSequence title){final int index=getChildCount();TextView v=new TextView(getContext());v.setText(title);v.setTextSize(13);v.setGravity(Gravity.CENTER);v.setPadding(dp(14),0,dp(14),0);v.setTextColor(Color.rgb(70,71,76));v.setBackground(round(Color.rgb(242,243,245),18));v.setOnClickListener(x->{select(index);if(listener!=null)listener.onFilterSelected(index,title);});LayoutParams p=new LayoutParams(-2,dp(36));p.rightMargin=dp(8);addView(v,p);if(selected<0)select(0);return this;}
    public SmartFilterChipGroup select(int index){if(index<0||index>=getChildCount())return this;selected=index;for(int i=0;i<getChildCount();i++){TextView v=(TextView)getChildAt(i);v.setTextColor(i==selected?Color.WHITE:Color.rgb(70,71,76));v.setBackground(round(i==selected?Color.rgb(63,81,181):Color.rgb(242,243,245),18));}return this;}
    public int getSelectedIndex(){return selected;}
    public SmartFilterChipGroup setOnFilterSelectedListener(OnFilterSelectedListener l){listener=l;return this;}
    private android.graphics.drawable.GradientDrawable round(int c,float r){android.graphics.drawable.GradientDrawable d=new android.graphics.drawable.GradientDrawable();d.setColor(c);d.setCornerRadius(dp(r));return d;}
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
}
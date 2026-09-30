package com.smarthub.smartmaterial.navigation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SmartSegmentedControl extends LinearLayout {
    public interface OnSegmentSelectedListener { void onSegmentSelected(int index, CharSequence title); }
    private OnSegmentSelectedListener listener; private int selected=-1;
    public SmartSegmentedControl(Context c){super(c);setOrientation(HORIZONTAL);setPadding(dp(2),dp(2),dp(2),dp(2));GradientDrawable bg=new GradientDrawable();bg.setColor(Color.rgb(240,241,244));bg.setCornerRadius(dp(10));setBackground(bg);}
    public SmartSegmentedControl addSegment(CharSequence title){final int index=getChildCount();TextView v=new TextView(getContext());v.setText(title);v.setTextSize(13);v.setGravity(Gravity.CENTER);v.setTextColor(Color.rgb(60,61,66));v.setOnClickListener(x->{select(index);if(listener!=null)listener.onSegmentSelected(index,title);});addView(v,new LayoutParams(0,-1,1));if(selected<0)select(0);return this;}
    public SmartSegmentedControl select(int index){if(index<0||index>=getChildCount())return this;selected=index;for(int i=0;i<getChildCount();i++){TextView v=(TextView)getChildAt(i);GradientDrawable bg=new GradientDrawable();bg.setColor(i==selected?Color.WHITE:Color.TRANSPARENT);bg.setCornerRadius(dp(8));v.setBackground(bg);v.setTextColor(i==selected?Color.rgb(30,31,36):Color.rgb(90,91,96));}return this;}
    public int getSelectedIndex(){return selected;}
    public SmartSegmentedControl setOnSegmentSelectedListener(OnSegmentSelectedListener l){listener=l;return this;}
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
}
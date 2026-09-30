package com.smarthub.smartmaterial.workflow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SmartPagination extends LinearLayout {
    public interface OnPageSelectedListener { void onPageSelected(int page); }
    private int current=1,total=1;private OnPageSelectedListener listener;
    public SmartPagination(Context c){super(c);setOrientation(HORIZONTAL);setGravity(Gravity.CENTER);setPadding(dp(4),0,dp(4),0);}
    public SmartPagination setPageCount(int count){total=Math.max(1,count);render();return this;}
    public SmartPagination setCurrentPage(int page){current=Math.max(1,Math.min(total,page));render();return this;}
    public int getCurrentPage(){return current;}
    public SmartPagination setOnPageSelectedListener(OnPageSelectedListener l){listener=l;return this;}
    private void render(){removeAllViews();addNav("‹",current-1,current==1);int start=Math.max(1,current-2),end=Math.min(total,start+4);start=Math.max(1,end-4);for(int i=start;i<=end;i++)addNav(String.valueOf(i),i,false);addNav("›",current+1,current==total);}
    private void addNav(String text,int page,boolean disabled){TextView v=new TextView(getContext());v.setText(text);v.setTextSize(14);v.setGravity(Gravity.CENTER);v.setTextColor(disabled?Color.rgb(170,171,176):page==current?Color.WHITE:Color.rgb(60,61,66));GradientDrawable d=new GradientDrawable();d.setColor(page==current&&!disabled?Color.rgb(63,81,181):Color.TRANSPARENT);d.setCornerRadius(dp(8));v.setBackground(d);v.setEnabled(!disabled);v.setOnClickListener(x->{current=page;render();if(listener!=null)listener.onPageSelected(page);});LayoutParams p=new LayoutParams(dp(40),dp(40));p.leftMargin=dp(2);p.rightMargin=dp(2);addView(v,p);}
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
}
package com.smarthub.smartmaterial.listitem;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartListItem extends LinearLayout {
    private final TextView title;
    private final TextView subtitle;
    public SmartListItem(Context c){this(c,null);}
    public SmartListItem(Context c,AttributeSet a){super(c,a);setOrientation(VERTICAL);setGravity(Gravity.CENTER_VERTICAL);setPadding(dp(16),dp(10),dp(16),dp(10));setMinimumHeight(dp(64));setBackground(background());title=new TextView(c);title.setTextSize(16);title.setTypeface(Typeface.DEFAULT,Typeface.BOLD);title.setTextColor(SmartTheme.onSurface(c));subtitle=new TextView(c);subtitle.setTextSize(14);subtitle.setTextColor(0xFF707178);addView(title,new LayoutParams(-1,-2));addView(subtitle,new LayoutParams(-1,-2));}
    public SmartListItem setTitle(CharSequence v){title.setText(v);return this;}
    public SmartListItem setSubtitle(CharSequence v){subtitle.setText(v);subtitle.setVisibility(v==null||v.length()==0?GONE:VISIBLE);return this;}
    public TextView getTitleView(){return title;}
    public TextView getSubtitleView(){return subtitle;}
    private GradientDrawable background(){GradientDrawable d=new GradientDrawable();d.setColor(SmartTheme.surface(getContext()));d.setCornerRadius(dp(12));return d;}
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
}
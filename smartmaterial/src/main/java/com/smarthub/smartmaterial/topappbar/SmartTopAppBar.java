package com.smarthub.smartmaterial.topappbar;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartTopAppBar extends LinearLayout {
    private final TextView title;
    public SmartTopAppBar(Context c){super(c);setOrientation(HORIZONTAL);setGravity(Gravity.CENTER_VERTICAL);setPadding(dp(16),0,dp(16),0);setMinimumHeight(dp(64));setBackground(background());title=new TextView(c);title.setTextSize(20);title.setTypeface(Typeface.DEFAULT,Typeface.BOLD);title.setTextColor(SmartTheme.onSurface(c));addView(title,new LayoutParams(0,-2,1));}
    public SmartTopAppBar setTitle(CharSequence v){title.setText(v);return this;}
    public TextView getTitleView(){return title;}
    public SmartTopAppBar addNavigation(View v){addView(v,0,new LayoutParams(dp(48),dp(48)));return this;}
    public SmartTopAppBar addAction(View v){addView(v,new LayoutParams(dp(48),dp(48)));return this;}
    private GradientDrawable background(){GradientDrawable d=new GradientDrawable();d.setColor(SmartTheme.surface(getContext()));return d;}
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
}
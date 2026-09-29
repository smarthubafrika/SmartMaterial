package com.smarthub.smartmaterial.tab;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartTabLayout extends LinearLayout {
    private final List<TextView> tabs=new ArrayList<>();
    private int selected=-1;
    public SmartTabLayout(Context c){super(c);setOrientation(HORIZONTAL);setGravity(Gravity.CENTER_VERTICAL);setMinimumHeight(dp(48));}
    public SmartTabLayout addTab(CharSequence text){TextView t=new TextView(getContext());t.setText(text);t.setTextSize(14);t.setGravity(Gravity.CENTER);t.setTextColor(SmartTheme.onSurface(getContext()));t.setClickable(true);t.setPadding(dp(16),0,dp(16),0);int index=tabs.size();t.setOnClickListener(v->selectTab(index));tabs.add(t);addView(t,new LayoutParams(0,-1,1));if(selected<0)selectTab(0);return this;}
    public SmartTabLayout selectTab(int index){if(index<0||index>=tabs.size())return this;selected=index;for(int i=0;i<tabs.size();i++){TextView t=tabs.get(i);t.setTextColor(i==selected?SmartTheme.primary(getContext()):0xFF707178);GradientDrawable d=new GradientDrawable();d.setColor(Color.TRANSPARENT);if(i==selected)d.setStroke(dp(2),SmartTheme.primary(getContext()));t.setBackground(d);}return this;}
    public int getSelectedTab(){return selected;}
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
}
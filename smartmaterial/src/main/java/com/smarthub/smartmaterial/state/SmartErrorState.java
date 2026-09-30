package com.smarthub.smartmaterial.state;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarthub.smartmaterial.button.SmartButton;
import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartErrorState extends LinearLayout {
    private final TextView icon,title,message; private final SmartButton action;
    public SmartErrorState(Context c){super(c);setOrientation(VERTICAL);setGravity(Gravity.CENTER);setPadding(dp(24),dp(32),dp(24),dp(32));
        icon=new TextView(c);icon.setText("!");icon.setTextSize(42);icon.setTextColor(Color.rgb(211,47,47));icon.setGravity(Gravity.CENTER);addView(icon,new LayoutParams(-1,dp(64)));
        title=new TextView(c);title.setText("Something went wrong");title.setTextSize(20);title.setTextColor(SmartTheme.onSurface(c));SmartTheme.bold(title);title.setGravity(Gravity.CENTER);addView(title,new LayoutParams(-1,-2));
        message=new TextView(c);message.setText("We couldn't load this information.");message.setTextSize(14);message.setTextColor(Color.rgb(100,101,108));message.setGravity(Gravity.CENTER);message.setPadding(0,dp(8),0,dp(16));addView(message,new LayoutParams(-1,-2));
        action=new SmartButton(c);action.setButtonText("Try Again");addView(action,new LayoutParams(-2,dp(44)));
    }
    public SmartErrorState setTitle(CharSequence v){title.setText(v);return this;}
    public SmartErrorState setMessage(CharSequence v){message.setText(v);return this;}
    public SmartErrorState setActionText(CharSequence v){action.setButtonText(v);return this;}
    public SmartErrorState setOnActionClickListener(OnClickListener l){action.setOnClickListener(l);return this;}
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
}
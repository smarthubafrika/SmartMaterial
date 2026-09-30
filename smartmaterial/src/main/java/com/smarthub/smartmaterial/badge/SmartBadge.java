package com.smarthub.smartmaterial.badge;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.TextView;

import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartBadge extends TextView {
    private int badgeColor = SmartTheme.isDark(getContext()) ? Color.rgb(55,57,64) : Color.rgb(232,234,238);
    private float radius = 12f;

    public SmartBadge(Context context) {
        super(context);
        setGravity(Gravity.CENTER);
        setTextSize(11);
        setPadding(dp(10), 0, dp(10), 0);
        setTextColor(Color.rgb(45,46,51));
        SmartTheme.medium(this);
        refresh();
    }

    public SmartBadge setTextValue(CharSequence text) { setText(text); return this; }
    public SmartBadge setBadgeColor(int color) { badgeColor=color; refresh(); return this; }
    public SmartBadge setTextColorValue(int color) { setTextColor(color); return this; }
    public SmartBadge setCornerRadius(float value) { radius=value; refresh(); return this; }

    private void refresh() {
        GradientDrawable d = new GradientDrawable();
        d.setColor(badgeColor);
        d.setCornerRadius(dp(radius));
        setBackground(d);
    }
    private int dp(float v) { return Math.round(v * getResources().getDisplayMetrics().density); }
}
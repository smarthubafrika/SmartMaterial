package com.smarthub.smartmaterial.badge;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.TextView;

import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

public class SmartBadge extends TextView {
    private int badgeColor = SmartColors.PRIMARY;
    private int badgeTextColor = SmartColors.ON_PRIMARY;

    public SmartBadge(Context context) { super(context); init(); }
    public SmartBadge(Context context, android.util.AttributeSet attrs) { super(context, attrs); init(); }
    public SmartBadge(Context context, android.util.AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); init(); }

    private void init() {
        setGravity(Gravity.CENTER);
        setTextSize(12);
        setTextColor(badgeTextColor);
        setPadding(dp(8), dp(3), dp(8), dp(3));
        setMinHeight(dp(24));
        updateBackground();
    }

    private int dp(float v) { return SmartDimensions.dp(getContext(), v); }

    private void updateBackground() {
        GradientDrawable d = new GradientDrawable();
        d.setColor(badgeColor);
        d.setCornerRadius(dp(12));
        setBackground(d);
    }

    public SmartBadge setBadgeText(CharSequence text) { setText(text); return this; }
    public SmartBadge setBadgeColor(int color) { badgeColor = color; updateBackground(); return this; }
    public SmartBadge setBadgeTextColor(int color) { badgeTextColor = color; setTextColor(color); return this; }
}

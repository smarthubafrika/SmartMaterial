package com.smarthub.smartmaterial.card;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartCard extends FrameLayout {
    private float radius = 16f;
    private int fillColor;

    public SmartCard(Context context) { super(context); init(); }
    public SmartCard(Context context, AttributeSet attrs) { super(context, attrs); init(); }
    public SmartCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr); init();
    }

    private void init() {
        fillColor = SmartTheme.surface(getContext());
        setPadding(dp(16), dp(16), dp(16), dp(16));
        setBackground(makeBackground());
        setElevation(dp(1));
    }

    public SmartCard setCardColor(int color) {
        fillColor = color;
        setBackground(makeBackground());
        return this;
    }

    public SmartCard setCornerRadius(float value) {
        radius = value;
        setBackground(makeBackground());
        return this;
    }

    public SmartCard setStroke(int color, float widthDp) {
        GradientDrawable d = makeBackground();
        d.setStroke(dp(widthDp), color);
        setBackground(d);
        return this;
    }

    private GradientDrawable makeBackground() {
        GradientDrawable d = new GradientDrawable();
        d.setColor(fillColor);
        d.setCornerRadius(dp(radius));
        return d;
    }

    private int dp(float value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}

package com.smarthub.smartmaterial.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.TextView;

import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartButton extends TextView {
    private int buttonColor;
    private int textColor;
    private float radius = 12f;

    public SmartButton(Context context) { super(context); init(); }
    public SmartButton(Context context, AttributeSet attrs) { super(context, attrs); init(); }
    public SmartButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr); init();
    }

    private void init() {
        buttonColor = SmartTheme.primary(getContext());
        textColor = SmartColors.ON_PRIMARY;
        setGravity(Gravity.CENTER);
        setTextSize(14);
        setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        int p = dp(16);
        setPadding(p, 0, p, 0);
        setMinHeight(dp(48));
        setMinimumHeight(dp(48));
        setTextColor(textColor);
        setAllCaps(false);
        setClickable(true);
        setFocusable(true);
        setBackground(makeBackground());
    }

    public SmartButton setButtonText(CharSequence text) { setText(text); return this; }

    public SmartButton setButtonColor(int color) {
        buttonColor = color;
        setBackground(makeBackground());
        return this;
    }

    public SmartButton setTextColorValue(int color) {
        textColor = color;
        setTextColor(color);
        return this;
    }

    public SmartButton setCornerRadius(float dp) {
        radius = dp;
        setBackground(makeBackground());
        return this;
    }

    private RippleDrawable makeBackground() {
        GradientDrawable base = new GradientDrawable();
        base.setColor(buttonColor);
        base.setCornerRadius(dp(radius));
        return new RippleDrawable(ColorStateList.valueOf(0x33FFFFFF), base, null);
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            animate().scaleX(0.98f).scaleY(0.98f).setDuration(60).start();
        } else if (event.getAction() == MotionEvent.ACTION_UP
                || event.getAction() == MotionEvent.ACTION_CANCEL) {
            animate().scaleX(1f).scaleY(1f).setDuration(100).start();
        }
        return super.onTouchEvent(event);
    }

    private int dp(float value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}

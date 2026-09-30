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

    private static final int JITPACK_RED = 0xFFE53935;
    private static final int JITPACK_GREEN = 0xFF43A047;

    private int buttonColor;
    private int textColor;
    private float radius = 6f;

    public SmartButton(Context context) {
        super(context);
        init();
    }

    public SmartButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SmartButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        buttonColor = JITPACK_RED;
        textColor = 0xFFFFFFFF;

        setGravity(Gravity.CENTER);
        setTextSize(14);
        setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        setAllCaps(false);

        int horizontalPadding = dp(16);
        setPadding(horizontalPadding, 0, horizontalPadding, 0);

        setMinHeight(dp(40));
        setMinimumHeight(dp(40));

        setTextColor(textColor);

        setClickable(true);
        setFocusable(true);
        setElevation(dp(1));

        setBackground(makeBackground());
        setAlpha(1f);
    }

    public SmartButton setButtonText(CharSequence text) {
        setText(text);
        return this;
    }

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

    public SmartButton setRedButton() {
        buttonColor = JITPACK_RED;
        textColor = 0xFFFFFFFF;
        setTextColor(textColor);
        setBackground(makeBackground());
        return this;
    }

    public SmartButton setGreenButton() {
        buttonColor = JITPACK_GREEN;
        textColor = 0xFFFFFFFF;
        setTextColor(textColor);
        setBackground(makeBackground());
        return this;
    }

    public SmartButton setJitPackRed() {
        return setRedButton();
    }

    public SmartButton setJitPackGreen() {
        return setGreenButton();
    }

    private RippleDrawable makeBackground() {
        GradientDrawable base = new GradientDrawable();
        base.setShape(GradientDrawable.RECTANGLE);
        base.setColor(buttonColor);
        base.setCornerRadius(dp(radius));

        ColorStateList rippleColor =
                new ColorStateList(
                        new int[][] {
                                new int[] { android.R.attr.state_pressed },
                                new int[] { android.R.attr.state_focused },
                                new int[] {}
                        },
                        new int[] {
                                0x33000000,
                                0x22000000,
                                0x00000000
                        }
                );

        return new RippleDrawable(rippleColor, base, null);
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();

        if (!isEnabled()) {
            setAlpha(0.50f);
        } else {
            setAlpha(1f);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            animate()
                    .scaleX(0.98f)
                    .scaleY(0.98f)
                    .setDuration(60)
                    .start();

        } else if (event.getAction() == MotionEvent.ACTION_UP
                || event.getAction() == MotionEvent.ACTION_CANCEL) {
            animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(100)
                    .start();
        }

        return super.onTouchEvent(event);
    }

    private int dp(float value) {
        return Math.round(
                value * getResources().getDisplayMetrics().density
        );
    }
}

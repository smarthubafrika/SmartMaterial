package com.smarthub.smartmaterial.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
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
    private float radius = 15f;

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

        buttonColor = SmartTheme.primary(getContext());
        textColor = SmartColors.ON_PRIMARY;

        setGravity(Gravity.CENTER);

        // Material-style typography
        setTextSize(14);
        setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        setAllCaps(false);

        // Material button spacing
        int horizontalPadding = dp(16);
        setPadding(horizontalPadding, 0, horizontalPadding, 0);

        // Material button minimum height
        setMinHeight(dp(48));
        setMinimumHeight(dp(48));

        setTextColor(textColor);

        // Click/focus behavior
        setClickable(true);
        setFocusable(true);

        // Small elevation similar to Material buttons
        setElevation(dp(2));

        // Rounded Material shape
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

    private RippleDrawable makeBackground() {

        GradientDrawable base = new GradientDrawable();

        base.setShape(GradientDrawable.RECTANGLE);
        base.setColor(buttonColor);
        base.setCornerRadius(dp(radius));

        // Material-style ripple
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

        return new RippleDrawable(
                rippleColor,
                base,
                null
        );
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
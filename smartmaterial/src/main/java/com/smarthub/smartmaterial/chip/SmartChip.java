package com.smarthub.smartmaterial.chip;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.TextView;

import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartChip extends TextView {
    private int chipColor = 0xFFE7E8EC;
    private int chipTextColor;
    private float radius = 20f;

    public SmartChip(Context context) { super(context); init(); }

    private void init() {
        chipTextColor = SmartTheme.onSurface(getContext());
        setGravity(Gravity.CENTER);
        setTextSize(14);
        setTextColor(chipTextColor);
        setPadding(dp(16), 0, dp(16), 0);
        setMinHeight(dp(40));
        setClickable(true);
        setFocusable(true);
        setBackground(makeBackground());
    }

    public SmartChip setTextValue(CharSequence text) { setText(text); return this; }
    public SmartChip setChipColor(int color) { chipColor = color; setBackground(makeBackground()); return this; }
    public SmartChip setTextColorValue(int color) { chipTextColor = color; setTextColor(color); return this; }
    public SmartChip setCornerRadius(float value) { radius = value; setBackground(makeBackground()); return this; }
    public SmartChip setOnChipClickListener(OnClickListener listener) { setOnClickListener(listener); return this; }

    private GradientDrawable makeBackground() {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(SmartTheme.isDark(getContext()) ? Color.rgb(48, 49, 55) : chipColor);
        drawable.setCornerRadius(dp(radius));
        drawable.setStroke(dp(1), 0x33777780);
        return drawable;
    }

    private int dp(float value) { return Math.round(value * getResources().getDisplayMetrics().density); }
}
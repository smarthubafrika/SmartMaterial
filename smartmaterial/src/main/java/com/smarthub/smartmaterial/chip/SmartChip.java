package com.smarthub.smartmaterial.chip;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.TextView;

import com.smarthub.smartmaterial.animation.SmartAnimations;
import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

public class SmartChip extends TextView {
    private int chipColor = SmartColors.SURFACE_VARIANT;
    private int textColor = SmartColors.ON_SURFACE_VARIANT;
    private float cornerRadius = 20f;

    public SmartChip(Context context) { super(context); init(); }
    public SmartChip(Context context, AttributeSet attrs) { super(context, attrs); init(); }
    public SmartChip(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); init(); }

    private void init() {
        setGravity(Gravity.CENTER);
        setTextSize(14);
        setTextColor(textColor);
        setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
        setPadding(SmartDimensions.dp(getContext(),16), SmartDimensions.dp(getContext(),8),
                SmartDimensions.dp(getContext(),16), SmartDimensions.dp(getContext(),8));
        setMinHeight(SmartDimensions.dp(getContext(),40));
        setClickable(true);
        setFocusable(true);
        updateBackground();
    }

    private void updateBackground() {
        GradientDrawable d = new GradientDrawable();
        d.setColor(chipColor);
        d.setCornerRadius(SmartDimensions.dp(getContext(), cornerRadius));
        setBackground(d);
    }

    public SmartChip setChipText(CharSequence text) { setText(text); return this; }
    public SmartChip setChipColor(int color) { chipColor = color; updateBackground(); return this; }
    public SmartChip setTextColorValue(int color) { textColor = color; setTextColor(color); return this; }
    public SmartChip setCornerRadius(float dp) { cornerRadius = dp; updateBackground(); return this; }

    @Override public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) SmartAnimations.press(this);
        if (event.getActionMasked() == MotionEvent.ACTION_UP || event.getActionMasked() == MotionEvent.ACTION_CANCEL) SmartAnimations.release(this);
        return super.onTouchEvent(event);
    }
}

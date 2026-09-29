package com.smarthub.smartmaterial.navigation;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageButton;

import com.smarthub.smartmaterial.animation.SmartAnimations;
import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

public class SmartFloatingActionButton extends ImageButton {
    private int fabColor = SmartColors.PRIMARY_CONTAINER;
    private int iconTint = SmartColors.ON_PRIMARY_CONTAINER;

    public SmartFloatingActionButton(Context context) { super(context); init(); }
    public SmartFloatingActionButton(Context context, AttributeSet attrs) { super(context, attrs); init(); }
    public SmartFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); init(); }

    private void init() {
        setScaleType(ScaleType.CENTER);
        setPadding(dp(16), dp(16), dp(16), dp(16));
        setColorFilter(iconTint);
        setClickable(true);
        setFocusable(true);
        setElevation(dp(6));
        updateBackground();
    }

    private int dp(float v) { return SmartDimensions.dp(getContext(), v); }

    private void updateBackground() {
        GradientDrawable d = new GradientDrawable();
        d.setColor(fabColor);
        d.setShape(GradientDrawable.OVAL);
        setBackground(d);
    }

    @Override protected void onMeasure(int widthSpec, int heightSpec) {
        setMeasuredDimension(resolveSize(dp(56), widthSpec), resolveSize(dp(56), heightSpec));
    }

    public SmartFloatingActionButton setIcon(int resId) { setImageResource(resId); return this; }
    public SmartFloatingActionButton setFabColor(int color) { fabColor = color; updateBackground(); return this; }
    public SmartFloatingActionButton setIconTint(int color) { iconTint = color; setColorFilter(color); return this; }

    @Override public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) SmartAnimations.press(this);
        if (event.getActionMasked() == MotionEvent.ACTION_UP || event.getActionMasked() == MotionEvent.ACTION_CANCEL) SmartAnimations.release(this);
        return super.onTouchEvent(event);
    }
}

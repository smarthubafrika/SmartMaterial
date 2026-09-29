package com.smarthub.smartmaterial.button;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.ImageButton;

import com.smarthub.smartmaterial.animation.SmartAnimations;
import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

public class SmartIconButton extends ImageButton {
    private int backgroundColor = SmartColors.SURFACE_VARIANT;
    private int iconTint = SmartColors.ON_SURFACE;
    private float cornerRadius = 20f;
    private float size = 48f;

    public SmartIconButton(Context context) { super(context); init(); }
    public SmartIconButton(Context context, AttributeSet attrs) { super(context, attrs); init(); }
    public SmartIconButton(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); init(); }

    private void init() {
        setScaleType(ScaleType.CENTER);
        setColorFilter(iconTint);
        setPadding(SmartDimensions.dp(getContext(),12), SmartDimensions.dp(getContext(),12),
                SmartDimensions.dp(getContext(),12), SmartDimensions.dp(getContext(),12));
        setClickable(true);
        setFocusable(true);
        setMinimumWidth(SmartDimensions.dp(getContext(), size));
        setMinimumHeight(SmartDimensions.dp(getContext(), size));
        updateBackground();
    }

    private void updateBackground() {
        GradientDrawable d = new GradientDrawable();
        d.setColor(backgroundColor);
        d.setCornerRadius(SmartDimensions.dp(getContext(), cornerRadius));
        setBackground(d);
    }

    public SmartIconButton setIcon(int resId) { setImageResource(resId); return this; }
    public SmartIconButton setIconTint(int color) { iconTint = color; setColorFilter(color); return this; }
    public SmartIconButton setButtonColor(int color) { backgroundColor = color; updateBackground(); return this; }
    public SmartIconButton setCornerRadius(float dp) { cornerRadius = dp; updateBackground(); return this; }
    public SmartIconButton setIconSize(float dp) {
        int p = SmartDimensions.dp(getContext(), dp);
        setPadding(p,p,p,p);
        return this;
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) SmartAnimations.press(this);
        if (event.getActionMasked() == MotionEvent.ACTION_UP || event.getActionMasked() == MotionEvent.ACTION_CANCEL) SmartAnimations.release(this);
        return super.onTouchEvent(event);
    }
}

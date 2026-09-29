package com.smarthub.smartmaterial.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.TextView;

import com.smarthub.smartmaterial.R;
import com.smarthub.smartmaterial.animation.SmartAnimations;
import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

public class SmartButton extends TextView {
    private int backgroundColor = SmartColors.PRIMARY;
    private int textColor = SmartColors.ON_PRIMARY;
    private float cornerRadius = SmartDimensions.CORNER_MEDIUM;

    public SmartButton(Context context) { super(context); init(null); }
    public SmartButton(Context context, AttributeSet attrs) { super(context, attrs); init(attrs); }
    public SmartButton(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); init(attrs); }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SmartButton);
            backgroundColor = a.getColor(R.styleable.SmartButton_smartButtonColor, backgroundColor);
            textColor = a.getColor(R.styleable.SmartButton_smartButtonTextColor, textColor);
            cornerRadius = a.getDimension(R.styleable.SmartButton_smartCornerRadius, SmartDimensions.dp(getContext(), cornerRadius)) / getResources().getDisplayMetrics().density;
            a.recycle();
        }
        setGravity(Gravity.CENTER);
        setTextColor(textColor);
        setTextSize(14);
        setTypeface(android.graphics.Typeface.DEFAULT, android.graphics.Typeface.BOLD);
        setMinHeight(SmartDimensions.dp(getContext(), SmartDimensions.BUTTON_HEIGHT));
        setPadding(SmartDimensions.dp(getContext(),24), 0, SmartDimensions.dp(getContext(),24), 0);
        setAllCaps(false);
        setClickable(true);
        setFocusable(true);
        setContentDescription(getText());
        updateBackground();
    }

    private void updateBackground() {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(backgroundColor);
        drawable.setCornerRadius(SmartDimensions.dp(getContext(), cornerRadius));
        setBackground(drawable);
    }

    public SmartButton setButtonText(CharSequence text) { setText(text); if (getContentDescription() == null) setContentDescription(text); return this; }
    public SmartButton setButtonColor(int color) { backgroundColor = color; updateBackground(); return this; }
    public SmartButton setButtonTextColor(int color) { textColor = color; setTextColor(color); return this; }
    public SmartButton setCornerRadius(float dp) { cornerRadius = dp; updateBackground(); return this; }

    @Override public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) SmartAnimations.press(this);
        if (event.getActionMasked() == MotionEvent.ACTION_UP || event.getActionMasked() == MotionEvent.ACTION_CANCEL) SmartAnimations.release(this);
        return super.onTouchEvent(event);
    }
}
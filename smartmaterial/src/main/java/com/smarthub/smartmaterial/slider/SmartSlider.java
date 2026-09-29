package com.smarthub.smartmaterial.slider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

public class SmartSlider extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float value = 0f;
    private float min = 0f;
    private float max = 1f;
    private int activeColor = SmartColors.PRIMARY;
    private int inactiveColor = SmartColors.SURFACE_VARIANT;
    private OnValueChangeListener listener;

    public interface OnValueChangeListener {
        void onValueChanged(SmartSlider slider, float value);
    }

    public SmartSlider(Context context) { super(context); init(); }
    public SmartSlider(Context context, AttributeSet attrs) { super(context, attrs); init(); }
    public SmartSlider(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); init(); }

    private void init() {
        setClickable(true);
        setFocusable(true);
        setMinimumHeight(dp(48));
    }

    private int dp(float v) { return SmartDimensions.dp(getContext(), v); }

    @Override protected void onMeasure(int widthSpec, int heightSpec) {
        setMeasuredDimension(resolveSize(dp(240), widthSpec), resolveSize(dp(48), heightSpec));
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float left = dp(8);
        float right = getWidth() - dp(8);
        float cy = getHeight() / 2f;
        float track = dp(4);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(inactiveColor);
        canvas.drawRoundRect(left, cy-track/2, right, cy+track/2, track/2, track/2, paint);

        float fraction = max == min ? 0 : (value-min)/(max-min);
        float x = left + (right-left) * Math.max(0, Math.min(1, fraction));

        paint.setColor(activeColor);
        canvas.drawRoundRect(left, cy-track/2, x, cy+track/2, track/2, track/2, paint);
        canvas.drawCircle(x, cy, dp(10), paint);
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN ||
            event.getActionMasked() == MotionEvent.ACTION_MOVE ||
            event.getActionMasked() == MotionEvent.ACTION_UP) {
            updateFromTouch(event.getX());
            if (event.getActionMasked() == MotionEvent.ACTION_UP) performClick();
            return true;
        }
        return true;
    }

    @Override public boolean performClick() {
        super.performClick();
        return true;
    }

    private void updateFromTouch(float x) {
        float left = dp(8);
        float right = getWidth() - dp(8);
        float fraction = (x-left)/(right-left);
        fraction = Math.max(0f, Math.min(1f, fraction));
        float newValue = min + fraction * (max-min);
        if (newValue != value) {
            value = newValue;
            invalidate();
            if (listener != null) listener.onValueChanged(this, value);
        }
    }

    public SmartSlider setValue(float value) {
        this.value = Math.max(min, Math.min(max, value));
        invalidate();
        return this;
    }

    public float getValue() { return value; }

    public SmartSlider setRange(float min, float max) {
        if (max < min) throw new IllegalArgumentException("max must be >= min");
        this.min = min;
        this.max = max;
        value = Math.max(min, Math.min(max, value));
        invalidate();
        return this;
    }

    public float getMin() { return min; }
    public float getMax() { return max; }

    public SmartSlider setActiveColor(int color) { activeColor = color; invalidate(); return this; }
    public SmartSlider setInactiveColor(int color) { inactiveColor = color; invalidate(); return this; }

    public SmartSlider setOnValueChangeListener(OnValueChangeListener l) {
        listener = l;
        return this;
    }
}

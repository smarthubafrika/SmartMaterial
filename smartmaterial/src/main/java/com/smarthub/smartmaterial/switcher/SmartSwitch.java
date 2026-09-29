package com.smarthub.smartmaterial.switcher;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

public class SmartSwitch extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean checked = false;
    private int checkedColor = SmartColors.PRIMARY;
    private int uncheckedColor = SmartColors.OUTLINE;
    private int thumbCheckedColor = SmartColors.ON_PRIMARY;
    private int thumbUncheckedColor = SmartColors.SURFACE;
    private OnCheckedChangeListener listener;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(SmartSwitch view, boolean checked);
    }

    public SmartSwitch(Context context) { super(context); init(); }
    public SmartSwitch(Context context, AttributeSet attrs) { super(context, attrs); init(); }
    public SmartSwitch(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); init(); }

    private void init() {
        setClickable(true);
        setFocusable(true);
        setMinimumWidth(dp(52));
        setMinimumHeight(dp(32));
    }

    private int dp(float value) { return SmartDimensions.dp(getContext(), value); }

    @Override protected void onMeasure(int widthSpec, int heightSpec) {
        setMeasuredDimension(resolveSize(dp(52), widthSpec), resolveSize(dp(32), heightSpec));
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float w = getWidth();
        float h = getHeight();
        float radius = h / 2f;

        paint.setColor(checked ? checkedColor : uncheckedColor);
        canvas.drawRoundRect(0, 0, w, h, radius, radius, paint);

        float thumbRadius = checked ? dp(12) : dp(8);
        float cx = checked ? w - dp(16) : dp(16);
        float cy = h / 2f;

        paint.setColor(checked ? thumbCheckedColor : thumbUncheckedColor);
        canvas.drawCircle(cx, cy, thumbRadius, paint);
    }

    @Override public boolean performClick() {
        super.performClick();
        setChecked(!checked);
        return true;
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_UP) {
            performClick();
        }
        return true;
    }

    public void setChecked(boolean value) {
        if (checked == value) return;
        checked = value;
        invalidate();
        if (listener != null) listener.onCheckedChanged(this, checked);
    }

    public boolean isChecked() { return checked; }

    public SmartSwitch setOnCheckedChangeListener(OnCheckedChangeListener l) {
        listener = l;
        return this;
    }

    public SmartSwitch setCheckedColor(int color) { checkedColor = color; invalidate(); return this; }
    public SmartSwitch setUncheckedColor(int color) { uncheckedColor = color; invalidate(); return this; }
    public SmartSwitch setThumbColors(int checked, int unchecked) {
        thumbCheckedColor = checked;
        thumbUncheckedColor = unchecked;
        invalidate();
        return this;
    }
}

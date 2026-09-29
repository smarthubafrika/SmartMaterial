package com.smarthub.smartmaterial.control;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

public class SmartRadioButton extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean checked = false;
    private int checkedColor = SmartColors.PRIMARY;
    private int uncheckedColor = SmartColors.OUTLINE;
    private OnCheckedChangeListener listener;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(SmartRadioButton view, boolean checked);
    }

    public SmartRadioButton(Context context) { super(context); init(); }
    public SmartRadioButton(Context context, AttributeSet attrs) { super(context, attrs); init(); }
    public SmartRadioButton(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); init(); }

    private void init() {
        setClickable(true);
        setFocusable(true);
    }

    private int dp(float value) { return SmartDimensions.dp(getContext(), value); }

    @Override protected void onMeasure(int widthSpec, int heightSpec) {
        setMeasuredDimension(resolveSize(dp(48), widthSpec), resolveSize(dp(48), heightSpec));
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float cx = getWidth() / 2f;
        float cy = getHeight() / 2f;

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(dp(2));
        paint.setColor(checked ? checkedColor : uncheckedColor);
        canvas.drawCircle(cx, cy, dp(10), paint);

        if (checked) {
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(checkedColor);
            canvas.drawCircle(cx, cy, dp(5), paint);
        }
        paint.setStyle(Paint.Style.FILL);
    }

    @Override public boolean performClick() {
        super.performClick();
        setChecked(!checked);
        return true;
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_UP) performClick();
        return true;
    }

    public void setChecked(boolean value) {
        if (checked == value) return;
        checked = value;
        invalidate();
        if (listener != null) listener.onCheckedChanged(this, checked);
    }

    public boolean isChecked() { return checked; }

    public SmartRadioButton setOnCheckedChangeListener(OnCheckedChangeListener l) {
        listener = l;
        return this;
    }

    public SmartRadioButton setCheckedColor(int color) { checkedColor = color; invalidate(); return this; }
    public SmartRadioButton setUncheckedColor(int color) { uncheckedColor = color; invalidate(); return this; }
}

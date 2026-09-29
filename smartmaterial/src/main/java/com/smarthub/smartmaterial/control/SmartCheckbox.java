package com.smarthub.smartmaterial.control;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

public class SmartCheckbox extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean checked = false;
    private int checkedColor = SmartColors.PRIMARY;
    private int uncheckedColor = SmartColors.OUTLINE;
    private OnCheckedChangeListener listener;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(SmartCheckbox view, boolean checked);
    }

    public SmartCheckbox(Context context) { super(context); init(); }
    public SmartCheckbox(Context context, AttributeSet attrs) { super(context, attrs); init(); }
    public SmartCheckbox(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); init(); }

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
        float left = dp(10);
        float top = dp(10);
        float right = getWidth() - dp(10);
        float bottom = getHeight() - dp(10);
        float radius = dp(4);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(checked ? checkedColor : android.graphics.Color.TRANSPARENT);
        canvas.drawRoundRect(left, top, right, bottom, radius, radius, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(dp(2));
        paint.setColor(checked ? checkedColor : uncheckedColor);
        canvas.drawRoundRect(left, top, right, bottom, radius, radius, paint);

        if (checked) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(dp(2));
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setColor(SmartColors.ON_PRIMARY);
            android.graphics.Path path = new android.graphics.Path();
            path.moveTo(dp(18), dp(24));
            path.lineTo(dp(22), dp(28));
            path.lineTo(dp(31), dp(18));
            canvas.drawPath(path, paint);
            paint.setStrokeCap(Paint.Cap.BUTT);
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

    public SmartCheckbox setOnCheckedChangeListener(OnCheckedChangeListener l) {
        listener = l;
        return this;
    }

    public SmartCheckbox setCheckedColor(int color) { checkedColor = color; invalidate(); return this; }
    public SmartCheckbox setUncheckedColor(int color) { uncheckedColor = color; invalidate(); return this; }
}

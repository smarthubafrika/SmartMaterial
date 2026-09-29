package com.smarthub.smartmaterial.switchcontrol;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartSwitch extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean checked;
    private float knobPosition;
    private OnCheckedChangeListener listener;

    public SmartSwitch(Context context) {
        super(context);
        init();
    }

    public SmartSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SmartSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setClickable(true);
        setFocusable(true);
        setMinimumWidth(dp(52));
        setMinimumHeight(dp(32));
        knobPosition = 0f;
    }

    public SmartSwitch setChecked(boolean value) {
        checked = value;
        knobPosition = checked ? 1f : 0f;
        invalidate();
        return this;
    }

    public boolean isChecked() {
        return checked;
    }

    public SmartSwitch setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public boolean performClick() {
        super.performClick();
        checked = !checked;
        knobPosition = checked ? 1f : 0f;
        invalidate();

        if (listener != null) {
            listener.onCheckedChanged(this, checked);
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            performClick();
        }
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = resolveSize(dp(52), widthMeasureSpec);
        int height = resolveSize(dp(32), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = getWidth();
        float height = getHeight();

        float trackHeight = Math.min(dp(32), height * 0.72f);
        float trackWidth = Math.min(dp(52), width);
        float left = (width - trackWidth) / 2f;
        float top = (height - trackHeight) / 2f;
        float right = left + trackWidth;
        float bottom = top + trackHeight;

        float radius = trackHeight / 2f;

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(checked
                ? SmartTheme.primary(getContext())
                : 0xFF777980);

        canvas.drawRoundRect(
                new RectF(left, top, right, bottom),
                radius,
                radius,
                paint
        );

        float knobDiameter = checked ? dp(24) : dp(16);
        float knobRadius = knobDiameter / 2f;
        float offCenter = left + radius;
        float onCenter = right - radius;
        float centerX = offCenter + (onCenter - offCenter) * knobPosition;
        float centerY = top + radius;

        paint.setColor(checked ? 0xFFFFFFFF : 0xFFE4E4E8);
        canvas.drawCircle(centerX, centerY, knobRadius, paint);
    }

    private int dp(float value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(SmartSwitch view, boolean checked);
    }
}

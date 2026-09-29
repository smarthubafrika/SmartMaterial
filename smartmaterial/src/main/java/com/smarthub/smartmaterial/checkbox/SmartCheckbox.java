package com.smarthub.smartmaterial.checkbox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartCheckbox extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean checked;
    private OnCheckedChangeListener listener;

    public SmartCheckbox(Context context) {
        super(context);
        init();
    }

    public SmartCheckbox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SmartCheckbox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setClickable(true);
        setFocusable(true);
        setMinimumWidth(dp(48));
        setMinimumHeight(dp(48));
    }

    public SmartCheckbox setChecked(boolean value) {
        checked = value;
        invalidate();
        return this;
    }

    public boolean isChecked() {
        return checked;
    }

    public SmartCheckbox setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public boolean performClick() {
        super.performClick();
        checked = !checked;
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
        int size = dp(48);
        setMeasuredDimension(
                resolveSize(size, widthMeasureSpec),
                resolveSize(size, heightMeasureSpec)
        );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float size = dp(20);
        float left = (getWidth() - size) / 2f;
        float top = (getHeight() - size) / 2f;
        float right = left + size;
        float bottom = top + size;

        paint.setStyle(Paint.Style.FILL);

        if (checked) {
            paint.setColor(SmartTheme.primary(getContext()));
            canvas.drawRoundRect(
                    new RectF(left, top, right, bottom),
                    dp(4), dp(4), paint
            );

            paint.setColor(0xFFFFFFFF);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(dp(2));
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeJoin(Paint.Join.ROUND);

            android.graphics.Path path = new android.graphics.Path();
            path.moveTo(left + dp(4), top + dp(10));
            path.lineTo(left + dp(8), top + dp(14));
            path.lineTo(left + dp(16), top + dp(6));
            canvas.drawPath(path, paint);
        } else {
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(dp(2));
            paint.setColor(0xFF777980);
            canvas.drawRoundRect(
                    new RectF(left + dp(1), top + dp(1), right - dp(1), bottom - dp(1)),
                    dp(4), dp(4), paint
            );
        }
    }

    private int dp(float value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(SmartCheckbox view, boolean checked);
    }
}
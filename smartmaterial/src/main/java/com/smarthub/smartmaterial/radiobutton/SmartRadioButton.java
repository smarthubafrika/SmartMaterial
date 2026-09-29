package com.smarthub.smartmaterial.radiobutton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartRadioButton extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean checked;
    private OnCheckedChangeListener listener;

    public SmartRadioButton(Context context) {
        super(context);
        init();
    }

    public SmartRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SmartRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setClickable(true);
        setFocusable(true);
        setMinimumWidth(dp(48));
        setMinimumHeight(dp(48));
    }

    public SmartRadioButton setChecked(boolean value) {
        checked = value;
        invalidate();
        return this;
    }

    public boolean isChecked() {
        return checked;
    }

    public SmartRadioButton setOnCheckedChangeListener(OnCheckedChangeListener listener) {
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

        float radius = dp(10);
        float cx = getWidth() / 2f;
        float cy = getHeight() / 2f;

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(dp(2));
        paint.setColor(checked
                ? SmartTheme.primary(getContext())
                : 0xFF777980);

        canvas.drawCircle(cx, cy, radius, paint);

        if (checked) {
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(cx, cy, dp(5), paint);
        }
    }

    private int dp(float value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(SmartRadioButton view, boolean checked);
    }
}

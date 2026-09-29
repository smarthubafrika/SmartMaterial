package com.smarthub.smartmaterial.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartProgress extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float progress = 0.65f;
    private boolean indeterminate;
    private float rotation;
    private final Runnable animator = new Runnable() {
        @Override public void run() {
            rotation += 8f;
            if (rotation >= 360f) rotation -= 360f;
            invalidate();
            postDelayed(this, 16);
        }
    };

    public SmartProgress(Context context) { super(context); init(); }
    public SmartProgress(Context context, AttributeSet attrs) { super(context, attrs); init(); }
    public SmartProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr); init();
    }

    private void init() {
        paint.setStrokeWidth(dp(4));
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        setMinimumHeight(dp(24));
    }

    public SmartProgress setProgress(float value) {
        progress = Math.max(0f, Math.min(1f, value));
        indeterminate = false;
        stopAnimation();
        invalidate();
        return this;
    }

    public SmartProgress setIndeterminate(boolean value) {
        indeterminate = value;
        if (value) {
            removeCallbacks(animator);
            post(animator);
        } else {
            stopAnimation();
        }
        invalidate();
        return this;
    }

    @Override protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (indeterminate) post(animator);
    }

    @Override protected void onDetachedFromWindow() {
        stopAnimation();
        super.onDetachedFromWindow();
    }

    private void stopAnimation() { removeCallbacks(animator); }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float cx = getWidth() / 2f;
        float cy = getHeight() / 2f;
        float radius = Math.max(2f, Math.min(getWidth(), getHeight()) / 2f - dp(6));
        RectF rect = new RectF(cx - radius, cy - radius, cx + radius, cy + radius);

        paint.setColor(0x332F4B7C);
        canvas.drawArc(rect, 0, 360, false, paint);

        paint.setColor(SmartTheme.primary(getContext()));
        if (indeterminate) canvas.drawArc(rect, rotation, 105, false, paint);
        else canvas.drawArc(rect, -90, 360f * progress, false, paint);
    }

    private float dp(float value) {
        return value * getResources().getDisplayMetrics().density;
    }
}

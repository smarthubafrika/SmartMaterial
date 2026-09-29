package com.smarthub.smartmaterial.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

public class SmartLinearProgress extends View {
    private final Paint trackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float progress = 0f;
    private boolean indeterminate = false;
    private float animatedOffset = 0f;

    public SmartLinearProgress(Context context) { super(context); init(); }
    public SmartLinearProgress(Context context, AttributeSet attrs) { super(context, attrs); init(); }
    public SmartLinearProgress(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); init(); }

    private void init() {
        trackPaint.setColor(SmartColors.SURFACE_VARIANT);
        progressPaint.setColor(SmartColors.PRIMARY);
        trackPaint.setStyle(Paint.Style.FILL);
        progressPaint.setStyle(Paint.Style.FILL);
        setMinimumHeight(SmartDimensions.dp(getContext(),4));
        setIndeterminate(true);
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float radius = getHeight() / 2f;
        RectF track = new RectF(0, 0, getWidth(), getHeight());
        canvas.drawRoundRect(track, radius, radius, trackPaint);

        if (indeterminate) {
            float segmentWidth = getWidth() * 0.35f;
            float left = animatedOffset * (getWidth() + segmentWidth) - segmentWidth;
            RectF segment = new RectF(left, 0, left + segmentWidth, getHeight());
            canvas.drawRoundRect(segment, radius, radius, progressPaint);
        } else {
            RectF bar = new RectF(0, 0, getWidth() * progress, getHeight());
            canvas.drawRoundRect(bar, radius, radius, progressPaint);
        }
    }

    public void setProgress(float value) {
        progress = Math.max(0f, Math.min(1f, value));
        indeterminate = false;
        invalidate();
    }

    public float getProgress() { return progress; }

    public void setIndeterminate(boolean value) {
        indeterminate = value;
        if (value) animateIndeterminate();
        else { animate().cancel(); invalidate(); }
    }

    public boolean isIndeterminate() { return indeterminate; }

    public SmartLinearProgress setProgressColor(int color) {
        progressPaint.setColor(color);
        invalidate();
        return this;
    }

    public SmartLinearProgress setTrackColor(int color) {
        trackPaint.setColor(color);
        invalidate();
        return this;
    }

    public SmartLinearProgress setBarHeight(float dp) {
        getLayoutParams().height = SmartDimensions.dp(getContext(), dp);
        requestLayout();
        return this;
    }

    private void animateIndeterminate() {
        if (!indeterminate) return;
        animateOffset();
    }

    private void animateOffset() {
        if (!indeterminate) return;
        animate().alpha(1f).setDuration(700).withEndAction(() -> {
            if (!indeterminate) return;
            animatedOffset = 1f;
            invalidate();
            animate().setDuration(700).withEndAction(() -> {
                animatedOffset = 0f;
                invalidate();
                animateOffset();
            }).start();
        }).start();
    }

    @Override protected void onDetachedFromWindow() {
        animate().cancel();
        super.onDetachedFromWindow();
    }
}

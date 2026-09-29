package com.smarthub.smartmaterial.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

public class SmartCircularProgress extends View {
    private final Paint trackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float progress = 0f;
    private boolean indeterminate = false;
    private float rotationAngle = 0f;

    public SmartCircularProgress(Context context) { super(context); init(); }
    public SmartCircularProgress(Context context, AttributeSet attrs) { super(context, attrs); init(); }
    public SmartCircularProgress(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); init(); }

    private void init() {
        trackPaint.setStyle(Paint.Style.STROKE);
        trackPaint.setStrokeWidth(SmartDimensions.dp(getContext(),4));
        trackPaint.setColor(SmartColors.SURFACE_VARIANT);
        trackPaint.setStrokeCap(Paint.Cap.ROUND);

        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(SmartDimensions.dp(getContext(),4));
        progressPaint.setColor(SmartColors.PRIMARY);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);

        setIndeterminate(true);
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float cx = getWidth() / 2f;
        float cy = getHeight() / 2f;
        float radius = Math.min(getWidth(), getHeight()) / 2f - progressPaint.getStrokeWidth();
        canvas.drawCircle(cx, cy, radius, trackPaint);

        if (indeterminate) {
            canvas.save();
            canvas.rotate(rotationAngle, cx, cy);
            canvas.drawArc(cx-radius, cy-radius, cx+radius, cy+radius, 0, 110, false, progressPaint);
            canvas.restore();
        } else {
            canvas.drawArc(cx-radius, cy-radius, cx+radius, cy+radius, -90, progress * 360f, false, progressPaint);
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
        if (value) animateRotation();
        else { animate().cancel(); invalidate(); }
    }

    public boolean isIndeterminate() { return indeterminate; }

    public SmartCircularProgress setProgressColor(int color) {
        progressPaint.setColor(color);
        invalidate();
        return this;
    }

    public SmartCircularProgress setTrackColor(int color) {
        trackPaint.setColor(color);
        invalidate();
        return this;
    }

    public SmartCircularProgress setStrokeWidth(float dp) {
        float px = SmartDimensions.dp(getContext(), dp);
        trackPaint.setStrokeWidth(px);
        progressPaint.setStrokeWidth(px);
        invalidate();
        return this;
    }

    private void animateRotation() {
        if (!indeterminate) return;
        rotationAngle = 0f;
        animate().rotationBy(360f).setDuration(900).withEndAction(this::animateRotation).start();
        invalidate();
    }

    @Override protected void onDetachedFromWindow() {
        animate().cancel();
        super.onDetachedFromWindow();
    }
}

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
    private final Runnable animation = new Runnable() {
        @Override public void run() {
            if (!indeterminate || getWindowToken() == null) return;
            animatedOffset += 0.012f;
            if (animatedOffset > 1f) animatedOffset = 0f;
            invalidate();
            postDelayed(this, 16);
        }
    };

    public SmartLinearProgress(Context context) { super(context); init(); }
    public SmartLinearProgress(Context context, AttributeSet attrs) { super(context, attrs); init(); }
    public SmartLinearProgress(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); init(); }

    private void init() {
        trackPaint.setColor(SmartColors.SURFACE_VARIANT);
        progressPaint.setColor(SmartColors.PRIMARY);
        trackPaint.setStyle(Paint.Style.FILL);
        progressPaint.setStyle(Paint.Style.FILL);
        setMinimumHeight(dp(4));
        setIndeterminate(true);
    }

    private int dp(float v) { return SmartDimensions.dp(getContext(), v); }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float radius = getHeight()/2f;
        canvas.drawRoundRect(new RectF(0,0,getWidth(),getHeight()), radius, radius, trackPaint);
        if (indeterminate) {
            float segmentWidth = Math.max(dp(24), getWidth()*0.35f);
            float left = animatedOffset*(getWidth()+segmentWidth)-segmentWidth;
            canvas.drawRoundRect(new RectF(left,0,left+segmentWidth,getHeight()), radius, radius, progressPaint);
        } else {
            canvas.drawRoundRect(new RectF(0,0,getWidth()*progress,getHeight()), radius, radius, progressPaint);
        }
    }

    public void setProgress(float value) { progress=Math.max(0f,Math.min(1f,value)); indeterminate=false; removeCallbacks(animation); invalidate(); }
    public float getProgress() { return progress; }

    public void setIndeterminate(boolean value) {
        indeterminate=value;
        removeCallbacks(animation);
        if (value) post(animation);
        else invalidate();
    }

    public boolean isIndeterminate() { return indeterminate; }
    public SmartLinearProgress setProgressColor(int color) { progressPaint.setColor(color); invalidate(); return this; }
    public SmartLinearProgress setTrackColor(int color) { trackPaint.setColor(color); invalidate(); return this; }

    public SmartLinearProgress setBarHeight(float dp) {
        if (getLayoutParams() != null) {
            getLayoutParams().height=dp(dp);
            requestLayout();
        }
        return this;
    }

    @Override protected void onDetachedFromWindow() {
        removeCallbacks(animation);
        super.onDetachedFromWindow();
    }
}
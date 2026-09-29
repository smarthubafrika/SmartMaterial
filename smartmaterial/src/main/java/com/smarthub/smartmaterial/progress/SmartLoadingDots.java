package com.smarthub.smartmaterial.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

public class SmartLoadingDots extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int dotColor = SmartColors.PRIMARY;
    private int dotCount = 3;
    private float dotRadius;
    private float spacing;
    private float animationPhase = 0f;

    public SmartLoadingDots(Context context) { super(context); init(); }
    public SmartLoadingDots(Context context, AttributeSet attrs) { super(context, attrs); init(); }
    public SmartLoadingDots(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); init(); }

    private void init() {
        dotRadius = SmartDimensions.dp(getContext(),4);
        spacing = SmartDimensions.dp(getContext(),8);
        paint.setColor(dotColor);
        setMinimumHeight(SmartDimensions.dp(getContext(),16));
        animateDots();
    }

    @Override protected void onMeasure(int widthSpec, int heightSpec) {
        int width = SmartDimensions.dp(getContext(), dotCount * 8 + (dotCount - 1) * 8);
        int height = SmartDimensions.dp(getContext(),16);
        setMeasuredDimension(resolveSize(width, widthSpec), resolveSize(height, heightSpec));
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float total = dotCount * dotRadius * 2 + (dotCount - 1) * spacing;
        float start = (getWidth() - total) / 2f + dotRadius;

        for (int i = 0; i < dotCount; i++) {
            float x = start + i * (dotRadius * 2 + spacing);
            double wave = Math.sin(animationPhase + i * 0.8);
            float radius = dotRadius * (0.75f + 0.25f * (float)((wave + 1) / 2));
            canvas.drawCircle(x, getHeight() / 2f, radius, paint);
        }
    }

    public SmartLoadingDots setDotColor(int color) {
        dotColor = color;
        paint.setColor(color);
        invalidate();
        return this;
    }

    public SmartLoadingDots setDotCount(int count) {
        dotCount = Math.max(2, Math.min(8, count));
        requestLayout();
        invalidate();
        return this;
    }

    public SmartLoadingDots setDotRadius(float dp) {
        dotRadius = SmartDimensions.dp(getContext(), dp);
        requestLayout();
        invalidate();
        return this;
    }

    public SmartLoadingDots setSpacing(float dp) {
        spacing = SmartDimensions.dp(getContext(), dp);
        requestLayout();
        invalidate();
        return this;
    }

    private void animateDots() {
        animate().setDuration(80).withEndAction(() -> {
            animationPhase += 0.22f;
            invalidate();
            animateDots();
        }).start();
    }

    @Override protected void onDetachedFromWindow() {
        animate().cancel();
        super.onDetachedFromWindow();
    }
}

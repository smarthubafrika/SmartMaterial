package com.smarthub.smartmaterial.avatar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;

public class SmartAvatar extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;
    private Drawable drawable;
    private String initials = "";
    private int avatarColor = Color.rgb(63,81,181);
    private int sizeDp = 48;
    private boolean online;

    public SmartAvatar(Context context) {
        super(context);
        setContentDescription("Avatar");
    }

    public SmartAvatar setImageBitmap(Bitmap value) { bitmap=value; drawable=null; invalidate(); return this; }
    public SmartAvatar setImageDrawable(Drawable value) { drawable=value; bitmap=null; invalidate(); return this; }
    public SmartAvatar setInitials(CharSequence value) { initials=value == null ? "" : value.toString(); invalidate(); return this; }
    public SmartAvatar setAvatarColor(int color) { avatarColor=color; invalidate(); return this; }
    public SmartAvatar setSize(float dp) { sizeDp=(int)dp; requestLayout(); return this; }
    public SmartAvatar setOnline(boolean value) { online=value; invalidate(); return this; }

    @Override protected void onMeasure(int w,int h) {
        int s=dp(sizeDp);
        setMeasuredDimension(resolveSize(s,w), resolveSize(s,h));
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float cx=getWidth()/2f, cy=getHeight()/2f, r=Math.min(getWidth(),getHeight())/2f;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(avatarColor);
        canvas.drawCircle(cx,cy,r,paint);
        if (drawable != null) {
            drawable.setBounds((int)(cx-r),(int)(cy-r),(int)(cx+r),(int)(cy+r));
            canvas.save(); canvas.clipPath(android.graphics.Path.class.cast(new android.graphics.Path())); drawable.draw(canvas); canvas.restore();
        } else if (bitmap != null) {
            android.graphics.Rect src=new android.graphics.Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
            android.graphics.RectF dst=new android.graphics.RectF(cx-r,cy-r,cx+r,cy+r);
            canvas.save();
            android.graphics.Path p=new android.graphics.Path(); p.addCircle(cx,cy,r,android.graphics.Path.Direction.CW);
            canvas.clipPath(p); canvas.drawBitmap(bitmap,src,dst,paint); canvas.restore();
        } else if (!initials.isEmpty()) {
            paint.setColor(Color.WHITE); paint.setTextSize(r*.7f); paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setTextAlign(Paint.Align.CENTER);
            Paint.FontMetrics fm=paint.getFontMetrics();
            canvas.drawText(initials,cx,cy-(fm.ascent+fm.descent)/2f,paint);
        }
        if (online) {
            paint.setColor(Color.rgb(46,125,50)); canvas.drawCircle(cx+r*.68f,cy+r*.68f,r*.22f,paint);
            paint.setStyle(Paint.Style.STROKE); paint.setStrokeWidth(dp(2)); paint.setColor(Color.WHITE);
            canvas.drawCircle(cx+r*.68f,cy+r*.68f,r*.22f,paint); paint.setStyle(Paint.Style.FILL);
        }
    }
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
}
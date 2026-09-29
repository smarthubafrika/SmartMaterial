package com.smarthub.smartmaterial.radio;

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

    public SmartRadioButton(Context c){super(c);init();}
    public SmartRadioButton(Context c, AttributeSet a){super(c,a);init();}
    public SmartRadioButton(Context c, AttributeSet a,int s){super(c,a,s);init();}
    private void init(){setClickable(true);setFocusable(true);setMinimumWidth(dp(48));setMinimumHeight(dp(48));}
    public SmartRadioButton setChecked(boolean v){checked=v;invalidate();return this;}
    public boolean isChecked(){return checked;}
    public SmartRadioButton setOnCheckedChangeListener(OnCheckedChangeListener l){listener=l;return this;}
    @Override public boolean performClick(){super.performClick();if(!checked){checked=true;invalidate();if(listener!=null)listener.onCheckedChanged(this,true);}return true;}
    @Override public boolean onTouchEvent(MotionEvent e){if(e.getAction()==MotionEvent.ACTION_UP)performClick();return true;}
    @Override protected void onMeasure(int w,int h){int s=dp(48);setMeasuredDimension(resolveSize(s,w),resolveSize(s,h));}
    @Override protected void onDraw(Canvas c){
        float cx=getWidth()/2f,cy=getHeight()/2f,r=dp(10);
        paint.setStyle(Paint.Style.STROKE);paint.setStrokeWidth(dp(2));paint.setColor(checked?SmartTheme.primary(getContext()):0xFF777980);
        c.drawCircle(cx,cy,r,paint);
        if(checked){paint.setStyle(Paint.Style.FILL);paint.setColor(SmartTheme.primary(getContext()));c.drawCircle(cx,cy,dp(5),paint);}
    }
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
    public interface OnCheckedChangeListener{void onCheckedChanged(SmartRadioButton view,boolean checked);}
}
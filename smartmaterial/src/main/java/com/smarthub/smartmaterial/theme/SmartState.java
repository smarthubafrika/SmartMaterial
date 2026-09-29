package com.smarthub.smartmaterial.theme;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.view.View;

public final class SmartState {
    private SmartState() {}
    public static void applyRipple(View view,int baseColor,int rippleColor){applyRipple(view,baseColor,rippleColor,12f);}
    public static void applyRipple(View view,int baseColor,int rippleColor,float radius){GradientDrawable d=new GradientDrawable();d.setColor(baseColor);d.setCornerRadius(SmartDimensions.dp(view.getContext(),radius));applyRipple(view,d,rippleColor);}
    public static void applyRipple(View view,Drawable content,int rippleColor){view.setBackground(new RippleDrawable(ColorStateList.valueOf(rippleColor),content,null));view.setClickable(true);view.setFocusable(true);}
    public static void accessible(View view,CharSequence description){if(description!=null)view.setContentDescription(description);view.setFocusable(true);view.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_YES);}
}
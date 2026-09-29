package com.smarthub.smartmaterial.bottomsheet;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.smarthub.smartmaterial.theme.SmartTheme;

public final class SmartBottomSheet {
    private SmartBottomSheet(){}
    public static Dialog show(Context context,View content){
        Dialog dialog=new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(content);
        Window w=dialog.getWindow();
        if(w!=null){
            w.setBackgroundDrawable(round(SmartTheme.surface(context),dp(context,28)));
            w.setDimAmount(0.32f);
            w.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            WindowManager.LayoutParams p=w.getAttributes();
            p.gravity=Gravity.BOTTOM;
            p.width=WindowManager.LayoutParams.MATCH_PARENT;
            p.height=WindowManager.LayoutParams.WRAP_CONTENT;
            w.setAttributes(p);
        }
        dialog.show();
        return dialog;
    }
    private static GradientDrawable round(int c,float r){GradientDrawable d=new GradientDrawable();d.setColor(c);d.setCornerRadius(r);return d;}
    private static int dp(Context c,float v){return Math.round(v*c.getResources().getDisplayMetrics().density);}
}
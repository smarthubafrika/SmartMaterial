package com.smarthub.smartmaterial.alert;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarthub.smartmaterial.theme.SmartTheme;

public final class SmartAlert {
    private SmartAlert(){}
    public static TextView show(Context context, CharSequence message){return show(context,"",message,Color.rgb(63,81,181));}
    public static TextView success(Context context, CharSequence message){return show(context,"Success",message,Color.rgb(46,125,50));}
    public static TextView error(Context context, CharSequence message){return show(context,"Error",message,Color.rgb(211,47,47));}
    public static TextView warning(Context context, CharSequence message){return show(context,"Warning",message,Color.rgb(245,124,0));}
    public static TextView info(Context context, CharSequence message){return show(context,"Info",message,Color.rgb(2,119,189));}
    public static TextView show(Context context, CharSequence title, CharSequence message, int color){
        LinearLayout box=new LinearLayout(context);box.setOrientation(LinearLayout.VERTICAL);box.setPadding(dp(context,16),dp(context,12),dp(context,16),dp(context,12));
        GradientDrawable bg=new GradientDrawable();bg.setColor(lighten(color));bg.setCornerRadius(dp(context,12));bg.setStroke(dp(context,1),color);box.setBackground(bg);
        if(title!=null && title.length()>0){TextView t=new TextView(context);t.setText(title);t.setTextSize(14);t.setTextColor(color);SmartTheme.bold(t);box.addView(t);}
        TextView m=new TextView(context);m.setText(message);m.setTextSize(14);m.setTextColor(SmartTheme.onSurface(context));SmartTheme.light(m);box.addView(m);
        return m;
    }
    private static int lighten(int c){return Color.argb(30,Color.red(c),Color.green(c),Color.blue(c));}
    private static int dp(Context c,float v){return Math.round(v*c.getResources().getDisplayMetrics().density);}
}
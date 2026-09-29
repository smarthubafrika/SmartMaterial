package com.smarthub.smartmaterial.datepicker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartDatePicker extends TextView {
    private final Calendar calendar=Calendar.getInstance();
    private String format="dd/MM/yyyy";
    private OnDateChangeListener listener;

    public SmartDatePicker(Context c){super(c);init();}
    private void init(){
        setTextSize(16);
        setTextColor(SmartTheme.onSurface(getContext()));
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(dp(16),0,dp(16),0);
        setMinHeight(dp(56));
        setClickable(true);
        setText(formatDate());
        setOnClickListener(v->showPicker());
    }
    public SmartDatePicker setDate(int year,int month,int day){calendar.set(year,month,day);setText(formatDate());return this;}
    public SmartDatePicker setFormat(String value){if(value!=null&&!value.isEmpty())format=value;setText(formatDate());return this;}
    public SmartDatePicker setOnDateChangeListener(OnDateChangeListener l){listener=l;return this;}
    public Calendar getDate(){return (Calendar)calendar.clone();}
    public String getDateText(){return formatDate();}
    public SmartDatePicker showPicker(){
        DatePickerDialog d=new DatePickerDialog(getContext(),(view,y,m,day)->{
            calendar.set(y,m,day);setText(formatDate());if(listener!=null)listener.onDateChanged(this,y,m,day);
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        d.show();
        return this;
    }
    private String formatDate(){return new SimpleDateFormat(format,Locale.getDefault()).format(calendar.getTime());}
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
    public interface OnDateChangeListener{void onDateChanged(SmartDatePicker view,int year,int month,int day);}
}
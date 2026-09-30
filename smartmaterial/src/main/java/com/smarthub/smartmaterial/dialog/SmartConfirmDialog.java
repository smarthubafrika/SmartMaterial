package com.smarthub.smartmaterial.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public final class SmartConfirmDialog {
    private SmartConfirmDialog(){}
    public static AlertDialog show(Context context, CharSequence title, CharSequence message, CharSequence positiveText, CharSequence negativeText, DialogInterface.OnClickListener positiveListener){
        AlertDialog d=new AlertDialog.Builder(context).setTitle(title).setMessage(message).setNegativeButton(negativeText==null?"Cancel":negativeText,null).setPositiveButton(positiveText==null?"Confirm":positiveText,positiveListener).create();
        d.show();return d;
    }
    public static AlertDialog delete(Context context, CharSequence message, DialogInterface.OnClickListener listener){
        return show(context,"Delete Item",message,"Delete","Cancel",listener);
    }
}
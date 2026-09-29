package com.smarthub.smartmaterial.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

public final class SmartAnimations {
    private SmartAnimations() {}

    public static void press(View view) {
        view.animate().cancel();
        view.animate().scaleX(0.98f).scaleY(0.98f).setDuration(70).start();
    }

    public static void release(View view) {
        view.animate().scaleX(1f).scaleY(1f).setDuration(120).start();
    }

    public static void fadeIn(View view, long duration) {
        view.setAlpha(0f);
        view.setVisibility(View.VISIBLE);
        view.animate().alpha(1f).setDuration(duration).start();
    }
}
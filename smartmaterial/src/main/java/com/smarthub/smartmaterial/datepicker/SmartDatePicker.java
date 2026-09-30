package com.smarthub.smartmaterial.datepicker;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarthub.smartmaterial.theme.SmartTheme;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SmartDatePicker extends TextView {

    private final Calendar calendar = Calendar.getInstance();
    private final Calendar displayedMonth = Calendar.getInstance();
    private String format = "dd/MM/yyyy";
    private OnDateChangeListener listener;
    private Dialog dialog;

    private final int primary = 0xFF6750A4;
    private final int surface = 0xFFFFFFFF;
    private final int onSurface = 0xFF1C1B1F;
    private final int secondaryText = 0xFF6F6B74;

    public SmartDatePicker(Context c) {
        super(c);
        init();
    }

    private void init() {
        displayedMonth.set(Calendar.DAY_OF_MONTH, 1);

        setTextSize(16);
        setTextColor(SmartTheme.onSurface(getContext()));
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(dp(16), 0, dp(16), 0);
        setMinHeight(dp(56));
        setClickable(true);
        setFocusable(true);
        setText(formatDate());
        setBackground(makeFieldBackground());
        setOnClickListener(v -> showPicker());
    }

    public SmartDatePicker setDate(int year, int month, int day) {
        calendar.set(year, month, day);
        displayedMonth.set(year, month, 1);
        setText(formatDate());
        return this;
    }

    public SmartDatePicker setFormat(String value) {
        if (value != null && !value.isEmpty()) {
            format = value;
        }
        setText(formatDate());
        return this;
    }

    public SmartDatePicker setOnDateChangeListener(OnDateChangeListener l) {
        listener = l;
        return this;
    }

    public Calendar getDate() {
        return (Calendar) calendar.clone();
    }

    public String getDateText() {
        return formatDate();
    }

    public SmartDatePicker showPicker() {
        if (dialog != null && dialog.isShowing()) {
            return this;
        }

        displayedMonth.setTimeInMillis(calendar.getTimeInMillis());
        displayedMonth.set(Calendar.DAY_OF_MONTH, 1);

        dialog = new Dialog(getContext());
        dialog.setContentView(buildCalendar());

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
        }

        dialog.show();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(
                    Math.min(dp(360), getResources().getDisplayMetrics().widthPixels - dp(32)),
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
        }

        return this;
    }

    private View buildCalendar() {
        LinearLayout root = new LinearLayout(getContext());
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(20), dp(18), dp(20), dp(14));
        root.setBackground(roundBackground(surface, 20));

        TextView title = textView("SELECT DATE", 12, secondaryText);
        SmartTheme.bold(title);
        root.addView(title, lp(-1, -2, 0, 0, 0, 8));

        LinearLayout monthBar = new LinearLayout(getContext());
        monthBar.setGravity(Gravity.CENTER_VERTICAL);

        TextView monthYear = textView("", 18, onSurface);
        SmartTheme.bold(monthYear);
        monthBar.addView(monthYear, new LinearLayout.LayoutParams(0, dp(48), 1));

        TextView previous = navButton("‹");
        TextView next = navButton("›");

        monthBar.addView(previous, lp(44, 44, 0, 0, 4, 0));
        monthBar.addView(next, lp(44, 44, 4, 0, 0, 0));
        root.addView(monthBar);

        TextView today = textView("Today", 13, primary);
        today.setGravity(Gravity.CENTER_VERTICAL);
        today.setPadding(dp(8), 0, dp(8), 0);
        today.setOnClickListener(v -> {
            Calendar now = Calendar.getInstance();
            calendar.setTimeInMillis(now.getTimeInMillis());
            displayedMonth.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), 1);
            setText(formatDate());
            refreshCalendar(root, monthYear);
        });
        root.addView(today, lp(-1, 36, 0, 0, 0, 4));

        GridLayout weekdays = new GridLayout(getContext());
        weekdays.setColumnCount(7);

        String[] names = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        for (String name : names) {
            TextView dayName = textView(name, 11, secondaryText);
            dayName.setGravity(Gravity.CENTER);
            GridLayout.LayoutParams weekdayParams = new GridLayout.LayoutParams();
            weekdayParams.width = 0;
            weekdayParams.height = dp(32);
            weekdayParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
            weekdayParams.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
            weekdays.addView(dayName, weekdayParams);
        }
        root.addView(weekdays, lp(-1, 32, 0, 0, 0, 0));

        GridLayout daysGrid = new GridLayout(getContext());
        daysGrid.setColumnCount(7);
        daysGrid.setTag("daysGrid");
        root.addView(daysGrid, lp(-1, -2, 0, 0, 0, 8));

        LinearLayout actions = new LinearLayout(getContext());
        actions.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);

        TextView cancel = actionButton("CANCEL", secondaryText);
        TextView select = actionButton("SELECT", primary);

        cancel.setOnClickListener(v -> dialog.dismiss());
        select.setOnClickListener(v -> {
            setText(formatDate());
            if (listener != null) {
                listener.onDateChanged(
                        this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
            }
            dialog.dismiss();
        });

        actions.addView(cancel, lp(-2, 44, 0, 0, 8, 0));
        actions.addView(select, lp(-2, 44, 8, 0, 0, 0));
        root.addView(actions, lp(-1, 44, 0, 0, 0, 0));

        previous.setOnClickListener(v -> {
            displayedMonth.add(Calendar.MONTH, -1);
            refreshCalendar(root, monthYear);
        });

        next.setOnClickListener(v -> {
            displayedMonth.add(Calendar.MONTH, 1);
            refreshCalendar(root, monthYear);
        });

        refreshCalendar(root, monthYear);
        return root;
    }

    private void refreshCalendar(LinearLayout root, TextView monthYear) {
        monthYear.setText(
                new SimpleDateFormat("MMMM yyyy", Locale.getDefault())
                        .format(displayedMonth.getTime())
        );

        GridLayout grid = (GridLayout) root.findViewWithTag("daysGrid");
        if (grid == null) {
            return;
        }

        grid.removeAllViews();

        Calendar first = (Calendar) displayedMonth.clone();
        first.set(Calendar.DAY_OF_MONTH, 1);

        int firstDay = first.get(Calendar.DAY_OF_WEEK) - 1;
        int maxDay = first.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < firstDay; i++) {
            TextView empty = textView("", 14, onSurface);
            grid.addView(empty, dayParams());
        }

        for (int day = 1; day <= maxDay; day++) {
            final int selectedDay = day;

            TextView dayView = textView(String.valueOf(day), 14, onSurface);
            dayView.setGravity(Gravity.CENTER);

            boolean selected =
                    calendar.get(Calendar.YEAR) == displayedMonth.get(Calendar.YEAR)
                            && calendar.get(Calendar.MONTH) == displayedMonth.get(Calendar.MONTH)
                            && calendar.get(Calendar.DAY_OF_MONTH) == day;

            boolean isToday = isToday(
                    displayedMonth.get(Calendar.YEAR),
                    displayedMonth.get(Calendar.MONTH),
                    day
            );

            if (selected) {
                dayView.setTextColor(Color.WHITE);
                SmartTheme.bold(dayView);
                dayView.setBackground(circleBackground(primary));
            } else if (isToday) {
                dayView.setTextColor(primary);
                dayView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            }

            dayView.setOnClickListener(v -> {
                calendar.set(
                        displayedMonth.get(Calendar.YEAR),
                        displayedMonth.get(Calendar.MONTH),
                        selectedDay
                );
                refreshCalendar(root, monthYear);
            });

            grid.addView(dayView, dayParams());
        }
    }

    private boolean isToday(int year, int month, int day) {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.YEAR) == year
                && now.get(Calendar.MONTH) == month
                && now.get(Calendar.DAY_OF_MONTH) == day;
    }

    private TextView textView(String text, float size, int color) {
        TextView view = new TextView(getContext());
        view.setText(text);
        view.setTextSize(size);
        view.setTextColor(color);
        SmartTheme.light(view);
        return view;
    }

    private TextView navButton(String text) {
        TextView view = textView(text, 28, onSurface);
        view.setGravity(Gravity.CENTER);
        view.setBackground(roundBackground(0xFFF4F1F7, 14));
        view.setClickable(true);
        return view;
    }

    private TextView actionButton(String text, int color) {
        TextView view = textView(text, 13, color);
        SmartTheme.bold(view);
        view.setGravity(Gravity.CENTER);
        view.setPadding(dp(14), 0, dp(14), 0);
        view.setClickable(true);
        return view;
    }

    private GridLayout.LayoutParams dayParams() {
        GridLayout.LayoutParams p = new GridLayout.LayoutParams(
                GridLayout.spec(GridLayout.UNDEFINED, 1f),
                GridLayout.spec(GridLayout.UNDEFINED, 1f)
        );
        p.width = 0;
        p.height = dp(42);
        p.setMargins(dp(2), dp(2), dp(2), dp(2));
        return p;
    }

    private LinearLayout.LayoutParams lp(
            int width, int height, int left, int top, int right, int bottom) {
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                width < 0 ? width == -1 ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT : dp(width),
                height < 0 ? height == -1 ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT : dp(height)
        );
        p.setMargins(dp(left), dp(top), dp(right), dp(bottom));
        return p;
    }

    private GradientDrawable makeFieldBackground() {
        return roundBackground(0xFFF7F5FA, 10);
    }

    private GradientDrawable roundBackground(int color, float radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(dp(radius));
        return drawable;
    }

    private GradientDrawable circleBackground(int color) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setShape(GradientDrawable.OVAL);
        return drawable;
    }

    private String formatDate() {
        return new SimpleDateFormat(format, Locale.getDefault())
                .format(calendar.getTime());
    }

    private int dp(float value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }

    public interface OnDateChangeListener {
        void onDateChanged(SmartDatePicker view, int year, int month, int day);
    }
}

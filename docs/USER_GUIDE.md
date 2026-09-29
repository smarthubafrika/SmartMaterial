# SmartMaterial User Guide

**Version:** 1.0.0  
**Language:** Java  
**Minimum SDK:** 23  
**Package:** `com.smarthub.smartmaterial`

SmartMaterial is a lightweight, dependency-free, Material 3-inspired Android UI library built with Java and Android framework APIs. It provides reusable controls, containers, navigation, feedback, progress indicators, theme helpers, shapes, typography, animation and accessibility utilities without requiring Google Material Components.

> **API accuracy:** This guide documents the public API currently present in SmartMaterial 1.0.0.

---

## Table of Contents

1. [What SmartMaterial Provides](#1-what-smartmaterial-provides)
2. [Requirements](#2-requirements)
3. [Installation](#3-installation)
4. [Imports and Packages](#4-imports-and-packages)
5. [Basic Usage Pattern](#5-basic-usage-pattern)
6. [Theme and Design Utilities](#6-theme-and-design-utilities)
7. [SmartButton](#7-smartbutton)
8. [SmartIconButton](#8-smarticonbutton)
9. [SmartCard](#9-smartcard)
10. [SmartTextField](#10-smarttextfield)
11. [SmartChip](#11-smartchip)
12. [SmartSwitch](#12-smartswitch)
13. [SmartCheckbox](#13-smartcheckbox)
14. [SmartRadioButton](#14-smartradiobutton)
15. [SmartSlider](#15-smartslider)
16. [SmartDropdown](#16-smartdropdown)
17. [SmartSearchBar](#17-smartsearchbar)
18. [SmartBadge](#18-smartbadge)
19. [SmartLinearProgress](#19-smartlinearprogress)
20. [SmartCircularProgress](#20-smartcircularprogress)
21. [SmartLoadingDots](#21-smartloadingdots)
22. [SmartDialog](#22-smartdialog)
23. [SmartSnackbar](#23-smartsnackbar)
24. [SmartToast](#24-smarttoast)
25. [SmartBottomSheet](#25-smartbottomsheet)
26. [SmartTopAppBar](#26-smarttopappbar)
27. [SmartNavigationBar](#27-smartnavigationbar)
28. [SmartNavigationRail](#28-smartnavigationrail)
29. [SmartFloatingActionButton](#29-smartfloatingactionbutton)
30. [SmartAnimations](#30-smartanimations)
31. [SmartState](#31-smartstate)
32. [Light and Dark Mode](#32-light-and-dark-mode)
33. [Typography](#33-typography)
34. [Dimensions](#34-dimensions)
35. [Shapes](#35-shapes)
36. [XML Usage](#36-xml-usage)
37. [Building a Complete Screen](#37-building-a-complete-screen)
38. [Accessibility](#38-accessibility)
39. [Common Usage Patterns](#39-common-usage-patterns)
40. [Troubleshooting](#40-troubleshooting)
41. [API Quick Reference](#41-api-quick-reference)
42. [Versioning and Updates](#42-versioning-and-updates)
43. [Reporting Issues](#43-reporting-issues)
44. [License](#44-license)

---

# 1. What SmartMaterial Provides

SmartMaterial contains the following public components and utilities.

### Buttons
- `SmartButton`
- `SmartIconButton`

### Containers
- `SmartCard`

### Text input and selection
- `SmartTextField`
- `SmartSearchBar`
- `SmartDropdown`
- `SmartChip`
- `SmartSwitch`
- `SmartCheckbox`
- `SmartRadioButton`
- `SmartSlider`

### Information
- `SmartBadge`

### Progress and loading
- `SmartLinearProgress`
- `SmartCircularProgress`
- `SmartLoadingDots`

### Dialogs and feedback
- `SmartDialog`
- `SmartSnackbar`
- `SmartToast`
- `SmartBottomSheet`

### Navigation
- `SmartTopAppBar`
- `SmartNavigationBar`
- `SmartNavigationRail`
- `SmartFloatingActionButton`

### Design utilities
- `SmartColors`
- `SmartDarkColors`
- `SmartTheme`
- `SmartDimensions`
- `SmartTypography`
- `SmartShapes`
- `SmartAnimations`
- `SmartState`

---

# 2. Requirements

SmartMaterial 1.0.0 is designed for:

- Android applications
- Java projects
- AndroidX-compatible projects
- Minimum SDK 23
- Compile SDK 35
- Java 11-compatible builds

SmartMaterial does **not** require the Google Material Components dependency.

---

# 3. Installation

SmartMaterial is published through JitPack.

Add JitPack to your project repositories:

```gradle
repositories {
    maven { url = uri("https://jitpack.io") }
}
```

Then add SmartMaterial:

```gradle
dependencies {
    implementation("com.github.smarthubafrika:SmartMaterial:1.0.0")
}
```

Sync Gradle.

After synchronization, import the components you need.

### Version notation

The Git tag is normally written as:

```text
v1.0.0
```

while the dependency version is:

```text
1.0.0
```

---

# 4. Imports and Packages

Components are grouped by package.

| Component | Package |
|---|---|
| SmartButton | `com.smarthub.smartmaterial.button` |
| SmartIconButton | `com.smarthub.smartmaterial.button` |
| SmartCard | `com.smarthub.smartmaterial.card` |
| SmartChip | `com.smarthub.smartmaterial.chip` |
| SmartCheckbox | `com.smarthub.smartmaterial.control` |
| SmartRadioButton | `com.smarthub.smartmaterial.control` |
| SmartDialog | `com.smarthub.smartmaterial.dialog` |
| SmartDropdown | `com.smarthub.smartmaterial.dropdown` |
| SmartFloatingActionButton | `com.smarthub.smartmaterial.navigation` |
| SmartNavigationBar | `com.smarthub.smartmaterial.navigation` |
| SmartNavigationRail | `com.smarthub.smartmaterial.navigation` |
| SmartTopAppBar | `com.smarthub.smartmaterial.navigation` |
| SmartCircularProgress | `com.smarthub.smartmaterial.progress` |
| SmartLinearProgress | `com.smarthub.smartmaterial.progress` |
| SmartLoadingDots | `com.smarthub.smartmaterial.progress` |
| SmartSearchBar | `com.smarthub.smartmaterial.search` |
| SmartBottomSheet | `com.smarthub.smartmaterial.sheet` |
| SmartSlider | `com.smarthub.smartmaterial.slider` |
| SmartSnackbar | `com.smarthub.smartmaterial.snackbar` |
| SmartToast | `com.smarthub.smartmaterial.snackbar` |
| SmartSwitch | `com.smarthub.smartmaterial.switcher` |
| SmartTextField | `com.smarthub.smartmaterial.textfield` |
| Theme utilities | `com.smarthub.smartmaterial.theme` |

Example:

```java
import com.smarthub.smartmaterial.button.SmartButton;
import com.smarthub.smartmaterial.theme.SmartColors;
```

---

# 5. Basic Usage Pattern

SmartMaterial components are Android Views or Android-style UI helpers.

The normal workflow is:

```text
Add dependency
    ↓
Import component
    ↓
Create component
    ↓
Configure component
    ↓
Add it to a layout
    ↓
Attach listeners where needed
```

Example:

```java
SmartButton button = new SmartButton(this)
        .setButtonText("Continue");

button.setOnClickListener(v -> {
    // Perform the action
});

root.addView(button);
```

You can use SmartMaterial components with standard Android layouts such as:

- `LinearLayout`
- `FrameLayout`
- `RelativeLayout`
- `ScrollView`
- `ConstraintLayout`
- Other compatible Android ViewGroups

---

# 6. Theme and Design Utilities

SmartMaterial provides small utilities for consistent application styling.

The main utility classes are:

- `SmartColors`
- `SmartDarkColors`
- `SmartTheme`
- `SmartDimensions`
- `SmartTypography`
- `SmartShapes`
- `SmartAnimations`
- `SmartState`

Use them instead of duplicating styling code throughout an application.

---

# 7. SmartButton

Package:

```text
com.smarthub.smartmaterial.button
```

`SmartButton` is a text-based action button.

## Create

```java
SmartButton button = new SmartButton(this);
```

## Set text

```java
button.setButtonText("Save");
```

## Click listener

```java
button.setOnClickListener(v -> {
    saveData();
});
```

## Customize

```java
button
        .setButtonText("Save")
        .setButtonColor(SmartColors.PRIMARY)
        .setButtonTextColor(SmartColors.ON_PRIMARY)
        .setRippleColor(SmartColors.PRIMARY_CONTAINER)
        .setCornerRadius(12);
```

## Public API

| Method | Purpose |
|---|---|
| `setButtonText(CharSequence)` | Sets button text and returns the button |
| `setButtonColor(int)` | Sets background color |
| `setButtonTextColor(int)` | Sets text color |
| `setRippleColor(int)` | Sets ripple color |
| `setCornerRadius(float)` | Sets corner radius in dp |

It also inherits normal Android `TextView` and `View` APIs.

---

# 8. SmartIconButton

Package:

```text
com.smarthub.smartmaterial.button
```

Use `SmartIconButton` for icon-based actions.

## Create

```java
SmartIconButton search =
        new SmartIconButton(this);
```

## Set icon

```java
search.setIcon(android.R.drawable.ic_menu_search);
```

## Set icon tint

```java
search.setIconTint(SmartColors.ON_SURFACE);
```

## Customize

```java
search
        .setButtonColor(SmartColors.SURFACE)
        .setIconTint(SmartColors.ON_SURFACE)
        .setRippleColor(SmartColors.PRIMARY)
        .setCornerRadius(20)
        .setIconSize(24);
```

## Accessibility

```java
search.setContentDescription("Search");
```

## Public API

| Method | Purpose |
|---|---|
| `setIcon(int)` | Sets drawable resource |
| `setIconTint(int)` | Sets icon color |
| `setButtonColor(int)` | Sets background color |
| `setRippleColor(int)` | Sets ripple color |
| `setCornerRadius(float)` | Sets corner radius in dp |
| `setIconSize(float)` | Changes icon padding/visible icon size |

---

# 9. SmartCard

Package:

```text
com.smarthub.smartmaterial.card
```

`SmartCard` is a `FrameLayout` designed for grouped content.

## Create

```java
SmartCard card = new SmartCard(this);
```

## Add content

```java
TextView title = new TextView(this);
title.setText("Product");

card.addView(title);

root.addView(card);
```

## Customize

```java
card
        .setCardColor(SmartColors.SURFACE)
        .setStrokeColor(SmartColors.OUTLINE)
        .setStrokeWidth(1)
        .setCornerRadius(16)
        .setCardElevation(2);
```

## Public API

| Method | Purpose |
|---|---|
| `setCardColor(int)` | Sets card background |
| `setStrokeColor(int)` | Sets outline color |
| `setStrokeWidth(float)` | Sets outline width in dp |
| `setCornerRadius(float)` | Sets corner radius in dp |
| `setCardElevation(float)` | Sets elevation in dp |

Because it extends `FrameLayout`, normal child-view APIs are available.

---

# 10. SmartTextField

Package:

```text
com.smarthub.smartmaterial.textfield
```

`SmartTextField` contains an Android `EditText` with a label, hint, fill and focus-aware outline.

## Create

```java
SmartTextField name =
        new SmartTextField(this);
```

## Label and hint

```java
name
        .setLabel("Full name")
        .setHint("Enter your name");
```

## Set text

```java
name.setText("John");
```

## Read text

```java
String value = name.getText();
```

## Input type

```java
name.setInputType(
        android.text.InputType.TYPE_CLASS_PHONE
);
```

## Password mode

```java
name.setPasswordMode(true);
```

Disable password mode:

```java
name.setPasswordMode(false);
```

## Customize colors

```java
name.setFillColor(SmartColors.SURFACE);

name.setStrokeColor(
        SmartColors.OUTLINE,
        SmartColors.PRIMARY
);
```

## Corner radius

```java
name.setCornerRadius(12);
```

## Access the underlying EditText

```java
EditText editText = name.getEditText();
```

## Public API

| Method | Purpose |
|---|---|
| `setLabel(CharSequence)` | Sets field label |
| `setHint(CharSequence)` | Sets input hint |
| `setText(CharSequence)` | Sets current text |
| `getText()` | Returns current text as String |
| `getEditText()` | Returns internal EditText |
| `setInputType(int)` | Sets Android input type |
| `setPasswordMode(boolean)` | Enables/disables password input |
| `setFillColor(int)` | Sets field fill |
| `setStrokeColor(int,int)` | Sets normal and focused stroke colors |
| `setCornerRadius(float)` | Sets corner radius in dp |

---

# 11. SmartChip

Package:

```text
com.smarthub.smartmaterial.chip
```

Use `SmartChip` for tags, filters and compact selections.

## Create

```java
SmartChip chip =
        new SmartChip(this)
        .setChipText("Featured");
```

## Customize

```java
chip
        .setChipColor(SmartColors.SURFACE_VARIANT)
        .setTextColorValue(SmartColors.ON_SURFACE_VARIANT)
        .setRippleColor(SmartColors.PRIMARY)
        .setCornerRadius(20);
```

## Public API

| Method | Purpose |
|---|---|
| `setChipText(CharSequence)` | Sets chip text |
| `setChipColor(int)` | Sets background |
| `setTextColorValue(int)` | Sets text color |
| `setRippleColor(int)` | Sets ripple color |
| `setCornerRadius(float)` | Sets corner radius in dp |

---

# 12. SmartSwitch

Package:

```text
com.smarthub.smartmaterial.switcher
```

Use `SmartSwitch` for on/off settings.

## Create

```java
SmartSwitch switchView =
        new SmartSwitch(this);
```

## Set state

```java
switchView.setChecked(true);
```

## Read state

```java
boolean enabled =
        switchView.isChecked();
```

## Listen for changes

```java
switchView.setOnCheckedChangeListener(
        (view, checked) -> {
            if (checked) {
                // Enabled
            } else {
                // Disabled
            }
        }
);
```

## Customize colors

```java
switchView
        .setCheckedColor(SmartColors.PRIMARY)
        .setUncheckedColor(SmartColors.OUTLINE)
        .setThumbColors(
                SmartColors.ON_PRIMARY,
                SmartColors.SURFACE
        );
```

## Public API

| Method | Purpose |
|---|---|
| `setChecked(boolean)` | Changes state |
| `isChecked()` | Reads state |
| `setOnCheckedChangeListener(...)` | Receives state changes |
| `setCheckedColor(int)` | Sets checked track color |
| `setUncheckedColor(int)` | Sets unchecked track color |
| `setThumbColors(int,int)` | Sets checked and unchecked thumb colors |

Listener interface:

```java
SmartSwitch.OnCheckedChangeListener
```

---

# 13. SmartCheckbox

Package:

```text
com.smarthub.smartmaterial.control
```

Use `SmartCheckbox` for independent multiple selections.

## Create

```java
SmartCheckbox checkbox =
        new SmartCheckbox(this);
```

## Set state

```java
checkbox.setChecked(true);
```

## Read state

```java
boolean checked =
        checkbox.isChecked();
```

## Listen

```java
checkbox.setOnCheckedChangeListener(
        (view, checked) -> {
            // Handle selection
        }
);
```

## Customize

```java
checkbox
        .setCheckedColor(SmartColors.PRIMARY)
        .setUncheckedColor(SmartColors.OUTLINE);
```

## Public API

| Method | Purpose |
|---|---|
| `setChecked(boolean)` | Changes state |
| `isChecked()` | Reads state |
| `setOnCheckedChangeListener(...)` | Receives state changes |
| `setCheckedColor(int)` | Sets checked color |
| `setUncheckedColor(int)` | Sets unchecked outline |

Listener interface:

```java
SmartCheckbox.OnCheckedChangeListener
```

---

# 14. SmartRadioButton

Package:

```text
com.smarthub.smartmaterial.control
```

Use `SmartRadioButton` for mutually exclusive choices.

## Create

```java
SmartRadioButton radio =
        new SmartRadioButton(this);
```

## Set state

```java
radio.setChecked(true);
```

## Read state

```java
boolean checked =
        radio.isChecked();
```

## Listen

```java
radio.setOnCheckedChangeListener(
        (view, checked) -> {
            // Handle state
        }
);
```

## Customize

```java
radio
        .setCheckedColor(SmartColors.PRIMARY)
        .setUncheckedColor(SmartColors.OUTLINE);
```

## Public API

| Method | Purpose |
|---|---|
| `setChecked(boolean)` | Changes state |
| `isChecked()` | Reads state |
| `setOnCheckedChangeListener(...)` | Receives state changes |
| `setCheckedColor(int)` | Sets selected color |
| `setUncheckedColor(int)` | Sets unselected outline |

Listener interface:

```java
SmartRadioButton.OnCheckedChangeListener
```

> SmartRadioButton provides the individual control. If an application needs exclusive grouping, manage the group selection in the application.

---

# 15. SmartSlider

Package:

```text
com.smarthub.smartmaterial.slider
```

SmartSlider represents a value between a configurable minimum and maximum.

## Create

```java
SmartSlider slider =
        new SmartSlider(this);
```

## Set range

```java
slider.setRange(0, 100);
```

## Set value

```java
slider.setValue(50);
```

## Read value

```java
float value =
        slider.getValue();
```

## Read range

```java
float minimum =
        slider.getMin();

float maximum =
        slider.getMax();
```

## Listen

```java
slider.setOnValueChangeListener(
        (view, value) -> {
            // Use value
        }
);
```

## Customize

```java
slider
        .setActiveColor(SmartColors.PRIMARY)
        .setInactiveColor(SmartColors.SURFACE_VARIANT);
```

## Public API

| Method | Purpose |
|---|---|
| `setValue(float)` | Sets value and clamps it to the range |
| `getValue()` | Returns current value |
| `setRange(float,float)` | Sets minimum and maximum |
| `getMin()` | Returns minimum |
| `getMax()` | Returns maximum |
| `setActiveColor(int)` | Sets active track/thumb color |
| `setInactiveColor(int)` | Sets inactive track color |
| `setOnValueChangeListener(...)` | Receives value changes |

If the maximum is lower than the minimum, `setRange()` throws `IllegalArgumentException`.

---

# 16. SmartDropdown

Package:

```text
com.smarthub.smartmaterial.dropdown
```

SmartDropdown displays a selectable list in a popup.

## Create with items

```java
SmartDropdown dropdown =
        new SmartDropdown(this)
        .setItems(
                "General",
                "Business",
                "Personal"
        );
```

## Set items from a List

```java
List<String> items =
        Arrays.asList(
                "General",
                "Business",
                "Personal"
        );

dropdown.setItems(items);
```

## Select an item

```java
dropdown.setSelectedIndex(1);
```

## Read selection

```int position = dropdown.getSelectedIndex();
String item = dropdown.getSelectedItem();
```

## Listen

```java
dropdown.setOnItemSelectedListener(
        (view, position, item) -> {
            // Handle selected item
        }
);
```

## Customize

```java
dropdown
        .setFillColor(SmartColors.SURFACE)
        .setStrokeColor(SmartColors.OUTLINE);
```

## Public API

| Method | Purpose |
|---|---|
| `setItems(String...)` | Replaces items with varargs |
| `setItems(List<String>)` | Replaces items with a list |
| `setSelectedIndex(int)` | Selects an item by position |
| `getSelectedIndex()` | Returns selected position |
| `getSelectedItem()` | Returns selected text or empty string |
| `setFillColor(int)` | Sets field fill |
| `setStrokeColor(int)` | Sets field outline |
| `setOnItemSelectedListener(...)` | Receives selection events |

If there are no items, the dropdown does not open.

---

# 17. SmartSearchBar

Package:

```text
com.smarthub.smartmaterial.search
```

SmartSearchBar provides a rounded search field with a search icon and clear action.

## Create

```java
SmartSearchBar search =
        new SmartSearchBar(this);
```

## Hint

```java
search.setHint("Search products");
```

## Set query

```java
search.setQuery("Android");
```

## Read query

```java
String query =
        search.getQuery();
```

## Listen for changes

```java
search.setOnQueryChangeListener(
        (view, query) -> {
            filterResults(query);
        }
);
```

## Access the EditText

```EditText editText =
        search.getEditText();
```

## Public API

| Method | Purpose |
|---|---|
| `setHint(CharSequence)` | Sets hint |
| `setQuery(CharSequence)` | Sets current query |
| `getQuery()` | Returns current query |
| `getEditText()` | Returns internal EditText |
| `setOnQueryChangeListener(...)` | Receives text changes |

The clear button becomes visible when the query contains text.

---

# 18. SmartBadge

Package:

```text
com.smarthub.smartmaterial.badge
```

SmartBadge is a compact information/count indicator.

## Create

```java
SmartBadge badge =
        new SmartBadge(this)
        .setBadgeText("3");
```

## Customize

```java
badge
        .setBadgeColor(SmartColors.PRIMARY)
        .setBadgeTextColor(SmartColors.ON_PRIMARY);
```

## Public API

| Method | Purpose |
|---|---|
| `setBadgeText(CharSequence)` | Sets displayed text and content description |
| `setBadgeColor(int)` | Sets badge background |
| `setBadgeTextColor(int)` | Sets badge text color |

---

# 19. SmartLinearProgress

Package:

```text
com.smarthub.smartmaterial.progress
```

SmartLinearProgress supports determinate and indeterminate progress.

## Determinate progress

Progress is represented from 0 to 1.

```java
SmartLinearProgress progress =
        new SmartLinearProgress(this);

progress.setProgress(0.65f);
```

65% is:

```text
0.65
```

50% is:

```text
0.50
```

## Read progress

```java
float value =
        progress.getProgress();
```

## Indeterminate mode

```java
progress.setIndeterminate(true);
```

Stop indeterminate mode:

```java
progress.setIndeterminate(false);
```

## Check mode

```java
boolean loading =
        progress.isIndeterminate();
```

## Customize

```java
progress
        .setProgressColor(SmartColors.PRIMARY)
        .setTrackColor(SmartColors.SURFACE_VARIANT)
        .setBarHeight(4);
```

## Public API

| Method | Purpose |
|---|---|
| `setProgress(float)` | Sets determinate progress, clamped to 0..1 |
| `getProgress()` | Returns progress |
| `setIndeterminate(boolean)` | Enables/disables animation |
| `isIndeterminate()` | Returns mode |
| `setProgressColor(int)` | Sets progress color |
| `setTrackColor(int)` | Sets track color |
| `setBarHeight(float)` | Changes layout height in dp |

---

# 20. SmartCircularProgress

Package:

```text
com.smarthub.smartmaterial.progress
```

SmartCircularProgress provides circular determinate or indeterminate progress.

## Determinate

```java
SmartCircularProgress progress =
        new SmartCircularProgress(this);

progress.setProgress(0.75f);
```

## Indeterminate

```java
progress.setIndeterminate(true);
```

## Check mode

```java
boolean loading =
        progress.isIndeterminate();
```

## Customize

```java
progress
        .setProgressColor(SmartColors.PRIMARY)
        .setTrackColor(SmartColors.SURFACE_VARIANT)
        .setStrokeWidth(4);
```

## Public API

| Method | Purpose |
|---|---|
| `setProgress(float)` | Sets determinate progress, clamped to 0..1 |
| `getProgress()` | Returns progress |
| `setIndeterminate(boolean)` | Enables/disables rotation |
| `isIndeterminate()` | Returns mode |
| `setProgressColor(int)` | Sets arc color |
| `setTrackColor(int)` | Sets track color |
| `setStrokeWidth(float)` | Sets stroke width in dp |

---

# 21. SmartLoadingDots

Package:

```text
com.smarthub.smartmaterial.progress
```

SmartLoadingDots provides an animated multi-dot loading indicator.

## Create

```java
SmartLoadingDots dots =
        new SmartLoadingDots(this);
```

## Customize dot color

```java
dots.setDotColor(SmartColors.PRIMARY);
```

## Number of dots

```java
dots.setDotCount(3);
```

The implementation clamps the number of dots between 2 and 8.

## Dot radius

```java
dots.setDotRadius(4);
```

## Spacing

```java
dots.setSpacing(8);
```

## Public API

| Method | Purpose |
|---|---|
| `setDotColor(int)` | Sets dot color |
| `setDotCount(int)` | Sets number of dots, clamped to 2..8 |
| `setDotRadius(float)` | Sets dot radius in dp |
| `setSpacing(float)` | Sets spacing in dp |

The animation starts automatically and is cancelled when the view is detached.

---

# 22. SmartDialog

Package:

```text
com.smarthub.smartmaterial.dialog
```

SmartDialog is a custom dialog with title, message, optional content and positive/negative actions.

## Create

```java
SmartDialog dialog =
        new SmartDialog(this);
```

## Title and message

```java
dialog
        .setTitle("Delete item")
        .setMessage(
                "Are you sure you want to delete this item?"
        );
```

## Positive button

```java
dialog.setPositiveButton(
        "Delete",
        v -> {
            deleteItem();
            dialog.dismiss();
        }
);
```

## Negative button

```java
dialog.setNegativeButton(
        "Cancel",
        v -> dialog.dismiss()
);
```

## Custom content

```java
EditText input = new EditText(this);

dialog.setView(input);
```

## Cancel behavior

```java
dialog.setCancelable(false);
```

## Show and dismiss

```java
dialog.show();
```

```java
dialog.dismiss();
```

## Access Android Dialog

```android.app.Dialog nativeDialog =
        dialog.getDialog();
```

## Public API

| Method | Purpose |
|---|---|
| `setTitle(CharSequence)` | Sets title |
| `setMessage(CharSequence)` | Sets message |
| `setView(View)` | Replaces custom content |
| `setPositiveButton(CharSequence, OnClickListener)` | Adds positive action |
| `setNegativeButton(CharSequence, OnClickListener)` | Adds negative action |
| `setCancelable(boolean)` | Controls cancellation |
| `show()` | Displays dialog |
| `dismiss()` | Closes dialog |
| `getDialog()` | Returns underlying Android Dialog |

---

# 23. SmartSnackbar

Package:

```text
com.smarthub.smartmaterial.snackbar
```

SmartSnackbar displays temporary contextual feedback.

## Basic

```java
SmartSnackbar.show(
        root,
        "Saved successfully"
);
```

## Custom duration

```java
SmartSnackbar.show(
        root,
        "Saved successfully",
        5000
);
```

The duration is in milliseconds.

## Public API

```java
SmartSnackbar.show(
        View anchor,
        CharSequence message
);
```

and:

```java
SmartSnackbar.show(
        View anchor,
        CharSequence message,
        long duration
);
```

If a suitable parent ViewGroup cannot be found, the implementation falls back to an Android Toast.

---

# 24. SmartToast

Package:

```text
com.smarthub.smartmaterial.snackbar
```

SmartToast provides a styled Android Toast.

## Basic

```java
SmartToast.show(
        this,
        "Saved"
);
```

## Duration

```java
SmartToast.show(
        this,
        "Saved",
        Toast.LENGTH_LONG
);
```

## Public API

```java
SmartToast.show(
        Context context,
        CharSequence message
);
```

and:

```java
SmartToast.show(
        Context context,
        CharSequence message,
        int duration
);
```

---

# 25. SmartBottomSheet

Package:

```text
com.smarthub.smartmaterial.sheet
```

SmartBottomSheet presents custom content from the bottom of the screen.

## Create

```java
SmartBottomSheet sheet =
        new SmartBottomSheet(this);
```

## Set content

```java
LinearLayout content =
        new LinearLayout(this);

sheet.setView(content);
```

## Cancellation

```java
sheet.setCancelable(true);
```

## Show

```java
sheet.show();
```

## Dismiss

```java
sheet.dismiss();
```

## Access Dialog

```android.app.Dialog nativeDialog =
        sheet.getDialog();
```

## Public API

| Method | Purpose |
|---|---|
| `setView(View)` | Replaces sheet content |
| `setCancelable(boolean)` | Controls cancellation |
| `show()` | Shows the sheet |
| `dismiss()` | Hides the sheet |
| `getDialog()` | Returns underlying Android Dialog |

---

# 26. SmartTopAppBar

Package:

```text
com.smarthub.smartmaterial.navigation
```

SmartTopAppBar provides a horizontal application header with navigation, title and action slots.

## Create

```java
SmartTopAppBar bar =
        new SmartTopAppBar(this)
        .setTitle("Settings");
```

## Navigation icon

```java
bar.setNavigationIcon(
        android.R.drawable.ic_media_previous
);
```

## Navigation click

```java
bar.setNavigationOnClickListener(v -> {
    finish();
});
```

## Add action

```java
SmartIconButton action =
        new SmartIconButton(this)
        .setIcon(android.R.drawable.ic_menu_search);

bar.addAction(action);
```

## Colors

```java
bar
        .setBarColor(SmartColors.SURFACE)
        .setTitleColor(SmartColors.ON_SURFACE);
```

## Access navigation button

```ImageButton navigation =
        bar.getNavigationButton();
```

## Public API

| Method | Purpose |
|---|---|
| `setTitle(CharSequence)` | Sets title |
| `setNavigationIcon(int)` | Sets navigation drawable |
| `setNavigationOnClickListener(OnClickListener)` | Sets navigation action |
| `addAction(View)` | Adds a 48dp action slot |
| `setBarColor(int)` | Sets bar background |
| `setTitleColor(int)` | Sets title color |
| `getNavigationButton()` | Returns navigation ImageButton |

---

# 27. SmartNavigationBar

Package:

```text
com.smarthub.smartmaterial.navigation
```

SmartNavigationBar provides bottom navigation items.

## Create

```java
SmartNavigationBar nav =
        new SmartNavigationBar(this);
```

## Add items

```java
nav
        .addItem("Home", 0, v -> {
            // Home
        })
        .addItem("Search", 0, v -> {
            // Search
        })
        .addItem("Profile", 0, v -> {
            // Profile
        });
```

The current 1.0.0 implementation accepts an icon resource parameter in `addItem()`, but the rendered item uses the component's built-in placeholder icon rather than the supplied resource. The parameter is therefore retained for API compatibility and should not be documented as a working custom icon feature yet.

## Selection

```java
nav.setSelectedIndex(1);
```

Read selection:

```java
int selected =
        nav.getSelectedIndex();
```

## Colors

```java
nav
        .setActiveColor(SmartColors.PRIMARY)
        .setInactiveColor(SmartColors.ON_SURFACE_VARIANT);
```

## Public API

| Method | Purpose |
|---|---|
| `addItem(CharSequence,int,OnClickListener)` | Adds a navigation item |
| `setSelectedIndex(int)` | Selects item |
| `getSelectedIndex()` | Returns selected item |
| `setActiveColor(int)` | Sets selected item color |
| `setInactiveColor(int)` | Sets unselected item color |

---

# 28. SmartNavigationRail

Package:

```text
com.smarthub.smartmaterial.navigation
```

SmartNavigationRail provides vertical navigation.

It is useful for larger screens, tablets and layouts where navigation is placed on the side.

## Create

```java
SmartNavigationRail rail =
        new SmartNavigationRail(this);
```

## Add items

```java
rail
        .addItem("Home", 0, v -> {
            // Home
        })
        .addItem("Search", 0, v -> {
            // Search
        });
```

As with SmartNavigationBar, the current 1.0.0 implementation accepts an icon resource argument but renders the built-in placeholder icon instead of the supplied resource.

## Selection

```java
rail.setSelectedIndex(1);
```

## Colors

```java
rail
        .setActiveColor(SmartColors.PRIMARY)
        .setInactiveColor(SmartColors.ON_SURFACE_VARIANT);
```

## Public API

| Method | Purpose |
|---|---|
| `addItem(CharSequence,int,OnClickListener)` | Adds navigation item |
| `setSelectedIndex(int)` | Selects item |
| `getSelectedIndex()` | Returns selected item |
| `setActiveColor(int)` | Sets selected item color |
| `setInactiveColor(int)` | Sets unselected item color |

---

# 29. SmartFloatingActionButton

Package:

```text
com.smarthub.smartmaterial.navigation
```

SmartFloatingActionButton is a circular image action button.

## Create

```java
SmartFloatingActionButton fab =
        new SmartFloatingActionButton(this);
```

## Icon

```java
fab.setIcon(
        android.R.drawable.ic_input_add
);
```

## Colors

```java
fab
        .setFabColor(SmartColors.PRIMARY_CONTAINER)
        .setIconTint(SmartColors.ON_PRIMARY_CONTAINER);
```

## Click listener

It inherits the standard Android click listener:

```java
fab.setOnClickListener(v -> {
    // Add item
});
```

## Public API

| Method | Purpose |
|---|---|
| `setIcon(int)` | Sets drawable resource |
| `setFabColor(int)` | Sets FAB background |
| `setIconTint(int)` | Sets icon color |

---

# 30. SmartAnimations

Package:

```text
com.smarthub.smartmaterial.animation
```

SmartAnimations contains static animation helpers.

## Press

```java
SmartAnimations.press(view);
```

This scales the view down slightly.

## Release

```java
SmartAnimations.release(view);
```

This restores the view scale.

## Fade in

```java
SmartAnimations.fadeIn(
        view,
        300
);
```

The duration is in milliseconds.

## Public API

| Method | Purpose |
|---|---|
| `press(View)` | Applies press scale animation |
| `release(View)` | Restores scale |
| `fadeIn(View,long)` | Fades a view into visibility |

---

# 31. SmartState

Package:

```text
com.smarthub.smartmaterial.theme
```

SmartState provides ripple and accessibility helpers.

## Ripple with default radius

```java
SmartState.applyRipple(
        view,
        SmartColors.PRIMARY,
        SmartColors.PRIMARY_CONTAINER
);
```

## Ripple with custom radius

```java
SmartState.applyRipple(
        view,
        SmartColors.PRIMARY,
        SmartColors.PRIMARY_CONTAINER,
        16
);
```

## Ripple with custom Drawable

```java
Drawable background =
        SmartShapes.rounded(
                this,
                SmartColors.SURFACE,
                12
        );

SmartState.applyRipple(
        view,
        background,
        SmartColors.PRIMARY
);
```

## Accessibility

```java
SmartState.accessible(
        view,
        "Open settings"
);
```

This sets a content description when supplied and marks the View as important for accessibility.

## Public API

| Method | Purpose |
|---|---|
| `applyRipple(View,int,int)` | Ripple with default 12dp radius |
| `applyRipple(View,int,int,float)` | Ripple with custom radius |
| `applyRipple(View,Drawable,int)` | Ripple around a supplied drawable |
| `accessible(View,CharSequence)` | Applies accessibility configuration |

---

# 32. Light and Dark Mode

SmartMaterial contains:

- `SmartColors` for light/default tokens
- `SmartDarkColors` for dark tokens

Use `SmartTheme` when a value should automatically follow the current UI mode.

## Check dark mode

```java
boolean dark =
        SmartTheme.isDark(this);
```

## Theme-aware colors

```java
int surface =
        SmartTheme.surface(this);

int text =
        SmartTheme.onSurface(this);

int primary =
        SmartTheme.primary(this);

int primaryContainer =
        SmartTheme.primaryContainer(this);
```

## Apply theme-aware text

```java
SmartTheme.applyText(
        textView,
        16
);
```

This sets text size, theme-aware surface text color and the default sans typeface.

## Make a View clickable

```java
SmartTheme.makeClickable(view);
```

## Content description

```java
SmartTheme.applyContentDescription(
        view,
        "Open settings"
);
```

## Public API

| Method | Purpose |
|---|---|
| `applyText(TextView,float)` | Applies text size/color/typeface |
| `dp(Context,float)` | Converts dp to pixels |
| `isDark(Context)` | Detects dark mode |
| `surface(Context)` | Returns theme-aware surface |
| `onSurface(Context)` | Returns theme-aware foreground |
| `primary(Context)` | Returns theme-aware primary |
| `primaryContainer(Context)` | Returns theme-aware primary container |
| `makeClickable(View)` | Makes View clickable/focusable |
| `applyContentDescription(View,CharSequence)` | Sets description |

---

# 33. Typography

Package:

```text
com.smarthub.smartmaterial.theme
```

SmartTypography provides simple named text styles.

Available methods:

```java
SmartTypography.displayLarge(textView);
SmartTypography.headlineLarge(textView);
SmartTypography.headlineMedium(textView);
SmartTypography.titleLarge(textView);
SmartTypography.titleMedium(textView);
SmartTypography.bodyLarge(textView);
SmartTypography.bodyMedium(textView);
SmartTypography.bodySmall(textView);
SmartTypography.labelLarge(textView);
SmartTypography.labelMedium(textView);
SmartTypography.labelSmall(textView);
```

The current sizes are:

| Style | Size |
|---|---:|
| displayLarge | 57sp |
| headlineLarge | 32sp |
| headlineMedium | 28sp |
| titleLarge | 22sp |
| titleMedium | 16sp |
| bodyLarge | 16sp |
| bodyMedium | 14sp |
| bodySmall | 12sp |
| labelLarge | 14sp |
| labelMedium | 12sp |
| labelSmall | 11sp |

Example:

```java
TextView title = new TextView(this);

SmartTypography.headlineLarge(title);

title.setText("Dashboard");
```

---

# 34. Dimensions

Package:

```text
com.smarthub.smartmaterial.theme
```

Convert dp to pixels:

```java
int padding =
        SmartDimensions.dp(this, 16);
```

Available standard constants:

| Constant | Value |
|---|---:|
| `BUTTON_HEIGHT` | 48dp |
| `CORNER_MEDIUM` | 12dp |
| `CORNER_LARGE` | 16dp |
| `CARD_ELEVATION` | 1dp |

The conversion method is:

```java
SmartDimensions.dp(
        Context context,
        float value
);
```

---

# 35. Shapes

Package:

```text
com.smarthub.smartmaterial.theme
```

## Rounded

```java
GradientDrawable background =
        SmartShapes.rounded(
                this,
                SmartColors.SURFACE,
                12
        );
```

## Outlined

```java
GradientDrawable background =
        SmartShapes.outlined(
                this,
                SmartColors.SURFACE,
                SmartColors.OUTLINE,
                12,
                1
        );
```

## Pill

```java
GradientDrawable background =
        SmartShapes.pill(
                this,
                SmartColors.PRIMARY
        );
```

## Circle

```java
GradientDrawable background =
        SmartShapes.circle(
                this,
                SmartColors.PRIMARY
        );
```

## Public API

| Method | Purpose |
|---|---|
| `rounded(Context,int,float)` | Creates rounded drawable |
| `outlined(Context,int,int,float,float)` | Creates rounded drawable with stroke |
| `pill(Context,int)` | Creates pill-shaped drawable |
| `circle(Context,int)` | Creates circular drawable |

---

# 36. XML Usage

All SmartMaterial View components provide Android XML constructors, so they can be inflated from XML.

Example:

```xml
<com.smarthub.smartmaterial.button.SmartButton
    android:id="@+id/saveButton"
    android:layout_width="match_parent"
    android:layout_height="48dp"
    android:text="Save" />
```

Then:

```java
SmartButton saveButton =
        findViewById(R.id.saveButton);
```

## XML attributes

SmartMaterial 1.0.0 declares styleable attributes for several components in `attrs.xml`.

### SmartButton

- `smartButtonColor`
- `smartButtonTextColor`
- `smartCornerRadius`
- `smartRippleColor`
- `smartContentDescription`

### SmartIconButton

- `smartButtonColor`
- `smartIconTint`
- `smartCornerRadius`
- `smartRippleColor`
- `smartContentDescription`

### SmartCard

- `smartCardColor`
- `smartStrokeColor`
- `smartStrokeWidth`
- `smartElevation`
- `smartCornerRadius`

### SmartChip

- `smartChipColor`
- `smartTextColor`
- `smartCornerRadius`
- `smartRippleColor`

The current 1.0.0 implementation actively reads these custom attributes for SmartButton, SmartIconButton, SmartCard and SmartChip.

The attribute declarations also include styleables for SmartTextField, SmartProgress, SmartSearchBar, SmartBadge, SmartSlider, SmartSwitch, SmartCheckbox and SmartRadioButton. However, their current Java implementations do not yet consume all of those custom XML values. For reliable 1.0.0 behavior, configure those properties through Java unless the component documentation specifically states otherwise.

---

# 37. Building a Complete Screen

The following example combines several SmartMaterial components.

```java
LinearLayout root =
        new LinearLayout(this);

root.setOrientation(
        LinearLayout.VERTICAL
);

root.setPadding(
        SmartDimensions.dp(this, 16),
        SmartDimensions.dp(this, 16),
        SmartDimensions.dp(this, 16),
        SmartDimensions.dp(this, 16)
);

SmartTopAppBar appBar =
        new SmartTopAppBar(this)
        .setTitle("Profile");

root.addView(
        appBar,
        new LinearLayout.LayoutParams(
                -1,
                SmartDimensions.dp(this, 56)
        )
);

SmartTextField name =
        new SmartTextField(this)
        .setLabel("Name")
        .setHint("Enter your name");

root.addView(
        name,
        new LinearLayout.LayoutParams(
                -1,
                SmartDimensions.dp(this, 72)
        )
);

SmartTextField phone =
        new SmartTextField(this)
        .setLabel("Phone")
        .setHint("Enter phone number");

root.addView(
        phone,
        new LinearLayout.LayoutParams(
                -1,
                SmartDimensions.dp(this, 72)
        )
);

SmartButton save =
        new SmartButton(this)
        .setButtonText("Save");

save.setOnClickListener(v -> {
    String nameValue = name.getText();
    String phoneValue = phone.getText();

    // Save the values.
});

LinearLayout.LayoutParams buttonParams =
        new LinearLayout.LayoutParams(
                -1,
                SmartDimensions.dp(this, 48)
        );

buttonParams.topMargin =
        SmartDimensions.dp(this, 16);

root.addView(save, buttonParams);

setContentView(root);
```

This demonstrates the normal SmartMaterial approach:

**create → configure → add → listen → use the component's value.**

---

# 38. Accessibility

Accessibility should be considered whenever SmartMaterial components are used.

## Icon-only controls

Always provide a meaningful description.

```java
search.setContentDescription("Search");
```

Good descriptions describe the action:

- Search
- Delete
- Add item
- Open settings
- Go back

Avoid descriptions such as:

- Button
- Icon
- Click here

## Custom Views

Use:

```java
SmartState.accessible(
        view,
        "Open settings"
);
```

or:

```java
SmartTheme.applyContentDescription(
        view,
        "Open settings"
);
```

## Loading indicators

Provide useful descriptions where appropriate:

```java
progress.setContentDescription(
        "Loading"
);
```

## State controls

SmartSwitch, SmartCheckbox and SmartRadioButton expose their state through their content-description behavior as the state changes.

---

# 39. Common Usage Patterns

## Form

A form can combine:

```text
SmartTextField
SmartTextField
SmartDropdown
SmartCheckbox
SmartButton
```

## Dashboard

A dashboard can combine:

```text
SmartTopAppBar
SmartCard
SmartBadge
SmartLinearProgress
SmartFloatingActionButton
```

## Search screen

Use:

```text
SmartSearchBar
SmartChip
SmartCard
SmartBadge
```

## Settings

Use:

```text
SmartTopAppBar
SmartCard
SmartSwitch
SmartCheckbox
SmartDropdown
```

## Loading screen

Use:

```text
SmartCircularProgress
SmartLoadingDots
```

## Confirmation

Use:

```text
SmartDialog
```

## Temporary feedback

Use:

```text
SmartSnackbar
SmartToast
```

---

# 40. Troubleshooting

## SmartMaterial classes cannot be resolved

Check:

1. JitPack is in your repositories.
2. The SmartMaterial dependency is present.
3. Gradle synchronization completed.
4. The import uses the correct package.
5. The requested version exists.

Example:

```gradle
implementation("com.github.smarthubafrika:SmartMaterial:1.0.0")
```

## XML component cannot be inflated

Check:

- The fully qualified package name.
- The dependency.
- The XML constructor.
- The Android namespace.
- The Gradle sync/build result.

Example:

```xml
<com.smarthub.smartmaterial.button.SmartButton
    android:layout_width="match_parent"
    android:layout_height="48dp" />
```

## Component is covered by another View

Check the parent layout and View ordering.

## Button does not respond

Make sure the button is:

- Visible
- Enabled
- Not covered by another View
- Receiving touch events

Then verify:

```java
button.setOnClickListener(v -> {
});
```

## Dropdown does not open

Make sure items have been supplied:

```java
dropdown.setItems(
        "One",
        "Two",
        "Three"
);
```

## Progress value looks wrong

Determinate progress uses a normalized value from 0 to 1.

For example:

```text
0.00 = 0%
0.25 = 25%
0.50 = 50%
0.75 = 75%
1.00 = 100%
```

## Dark mode colors look inconsistent

Prefer theme-aware helpers:

```java
SmartTheme.surface(context);
SmartTheme.onSurface(context);
SmartTheme.primary(context);
SmartTheme.primaryContainer(context);
```

rather than hardcoding light-theme colors.

---

# 41. API Quick Reference

## SmartButton

```java
setButtonText(CharSequence)
setButtonColor(int)
setButtonTextColor(int)
setRippleColor(int)
setCornerRadius(float)
```

## SmartIconButton

```java
setIcon(int)
setIconTint(int)
setButtonColor(int)
setRippleColor(int)
setCornerRadius(float)
setIconSize(float)
```

## SmartCard

```java
setCardColor(int)
setStrokeColor(int)
setStrokeWidth(float)
setCornerRadius(float)
setCardElevation(float)
```

## SmartTextField

```java
setLabel(CharSequence)
setHint(CharSequence)
setText(CharSequence)
getText()
getEditText()
setInputType(int)
setPasswordMode(boolean)
setFillColor(int)
setStrokeColor(int,int)
setCornerRadius(float)
```

## SmartChip

```java
setChipText(CharSequence)
setChipColor(int)
setTextColorValue(int)
setRippleColor(int)
setCornerRadius(float)
```

## SmartSwitch

```java
setChecked(boolean)
isChecked()
setOnCheckedChangeListener(...)
setCheckedColor(int)
setUncheckedColor(int)
setThumbColors(int,int)
```

## SmartCheckbox

```java
setChecked(boolean)
isChecked()
setOnCheckedChangeListener(...)
setCheckedColor(int)
setUncheckedColor(int)
```

## SmartRadioButton

```java
setChecked(boolean)
isChecked()
setOnCheckedChangeListener(...)
setCheckedColor(int)
setUncheckedColor(int)
```

## SmartSlider

```java
setValue(float)
getValue()
setRange(float,float)
getMin()
getMax()
setActiveColor(int)
setInactiveColor(int)
setOnValueChangeListener(...)
```

## SmartDropdown

```java
setItems(String...)
setItems(List<String>)
setSelectedIndex(int)
getSelectedIndex()
getSelectedItem()
setFillColor(int)
setStrokeColor(int)
setOnItemSelectedListener(...)
```

## SmartSearchBar

```java
setHint(CharSequence)
setQuery(CharSequence)
getQuery()
getEditText()
setOnQueryChangeListener(...)
```

## SmartBadge

```java
setBadgeText(CharSequence)
setBadgeColor(int)
setBadgeTextColor(int)
```

## SmartLinearProgress

```java
setProgress(float)
getProgress()
setIndeterminate(boolean)
isIndeterminate()
setProgressColor(int)
setTrackColor(int)
setBarHeight(float)
```

## SmartCircularProgress

```java
setProgress(float)
getProgress()
setIndeterminate(boolean)
isIndeterminate()
setProgressColor(int)
setTrackColor(int)
setStrokeWidth(float)
```

## SmartLoadingDots

```java
setDotColor(int)
setDotCount(int)
setDotRadius(float)
setSpacing(float)
```

## SmartDialog

```java
setTitle(CharSequence)
setMessage(CharSequence)
setView(View)
setPositiveButton(CharSequence, OnClickListener)
setNegativeButton(CharSequence, OnClickListener)
setCancelable(boolean)
show()
dismiss()
getDialog()
```

## SmartSnackbar

```java
show(View, CharSequence)
show(View, CharSequence, long)
```

## SmartToast

```java
show(Context, CharSequence)
show(Context, CharSequence, int)
```

## SmartBottomSheet

```java
setView(View)
setCancelable(boolean)
show()
dismiss()
getDialog()
```

## SmartTopAppBar

```java
setTitle(CharSequence)
setNavigationIcon(int)
setNavigationOnClickListener(OnClickListener)
addAction(View)
setBarColor(int)
setTitleColor(int)
getNavigationButton()
```

## SmartNavigationBar

```java
addItem(CharSequence, int, OnClickListener)
setSelectedIndex(int)
getSelectedIndex()
setActiveColor(int)
setInactiveColor(int)
```

## SmartNavigationRail

```java
addItem(CharSequence, int, OnClickListener)
setSelectedIndex(int)
getSelectedIndex()
setActiveColor(int)
setInactiveColor(int)
```

## SmartFloatingActionButton

```java
setIcon(int)
setFabColor(int)
setIconTint(int)
```

## SmartAnimations

```java
press(View)
release(View)
fadeIn(View, long)
```

## SmartState

```java
applyRipple(View, int, int)
applyRipple(View, int, int, float)
applyRipple(View, Drawable, int)
accessible(View, CharSequence)
```

## SmartTheme

```java
applyText(TextView, float)
dp(Context, float)
isDark(Context)
surface(Context)
onSurface(Context)
primary(Context)
primaryContainer(Context)
makeClickable(View)
applyContentDescription(View, CharSequence)
```

## SmartDimensions

```java
dp(Context, float)
BUTTON_HEIGHT
CORNER_MEDIUM
CORNER_LARGE
CARD_ELEVATION
```

## SmartTypography

```java
displayLarge(TextView)
headlineLarge(TextView)
headlineMedium(TextView)
titleLarge(TextView)
titleMedium(TextView)
bodyLarge(TextView)
bodyMedium(TextView)
bodySmall(TextView)
labelLarge(TextView)
labelMedium(TextView)
labelSmall(TextView)
```

## SmartShapes

```java
rounded(Context, int, float)
outlined(Context, int, int, float, float)
pill(Context, int)
circle(Context, int)
```

---

# 42. Versioning and Updates

SmartMaterial follows a versioned release model.

Example:

```text
1.0.0
```

When a newer release is available, update the dependency:

```gradle
implementation("com.github.smarthubafrika:SmartMaterial:1.1.0")
```

Always review the changelog before upgrading.

The repository also contains a `VERSION` file and `CHANGELOG.md`.

---

# 43. Reporting Issues

Before reporting an issue:

1. Confirm the SmartMaterial version.
2. Confirm the Android version.
3. Confirm the device or emulator.
4. Check the relevant section of this guide.
5. Reproduce the problem with the smallest possible example.
6. Include the error message and relevant Logcat output.

Do not include passwords, API keys, access tokens or other sensitive information in an issue.

When reporting a UI problem, include:

- Component name
- Expected behavior
- Actual behavior
- Relevant Java/XML
- Android version
- SmartMaterial version

---

# 44. License

SmartMaterial is released under the Apache License 2.0.

See the repository `LICENSE` file for the complete terms.

---

# Quick Start

For most applications, start with:

```gradle
repositories {
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.smarthubafrika:SmartMaterial:1.0.0")
}
```

Then:

```java
SmartButton button =
        new SmartButton(this)
        .setButtonText("Continue");

button.setOnClickListener(v -> {
    // Your action
});
```

For input:

```java
SmartTextField field =
        new SmartTextField(this)
        .setLabel("Name")
        .setHint("Enter your name");
```

For a card:

```java
SmartCard card =
        new SmartCard(this);
```

For theme-aware colors:

```java
int surface =
        SmartTheme.surface(this);

int primary =
        SmartTheme.primary(this);

int text =
        SmartTheme.onSurface(this);
```

The SmartMaterial workflow is:

**Install → Import → Create → Configure → Add → Listen → Use.**

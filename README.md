# SmartMaterial

A small, dependency-free Material 3 inspired Android UI library written in Java.

The library uses Android framework APIs only and has no Google Material Components dependency.

## Requirements

- Java
- minSdk 23
- No Google Material Components dependency
- Android framework APIs only

## Components

### Core
- SmartButton
- SmartCard
- SmartTextField
- SmartProgress
- SmartSnackbar
- SmartDialog
- SmartSwitch
- SmartCheckbox
- SmartRadioButton

### Surfaces and navigation
- SmartChip
- SmartDivider
- SmartIconButton
- SmartListItem
- SmartImageCard
- SmartBottomSheet
- SmartTopAppBar
- SmartTabLayout

### Input
- SmartDropdown
- SmartDatePicker

### Theme utilities
- SmartColors
- SmartTheme
- SmartMaterial

## Quick example

    SmartButton button = new SmartButton(this).setButtonText("Continue");
    button.setOnClickListener(v -> SmartSnackbar.show(v, "Button clicked"));

    SmartTextField name = new SmartTextField(this)
            .setLabel("Name")
            .setHint("Enter your name");

    SmartCheckbox terms = new SmartCheckbox(this)
            .setChecked(false)
            .setOnCheckedChangeListener((view, checked) -> {
                // Handle the change.
            });

## JitPack

Add JitPack to your root settings.gradle:

    dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            google()
            mavenCentral()
            maven { url 'https://jitpack.io' }
        }
    }

For the SmartMaterial library module, use:

    dependencies {
        implementation 'com.github.smarthubafrika.SmartMaterial:smartmaterial:1.0.1'
    }

JitPack also supports the repository-level dependency:

    dependencies {
        implementation 'com.github.smarthubafrika:SmartMaterial:1.0.1'
    }

The module-specific coordinate is the direct SmartMaterial Android library artifact.

## Design goal

SmartMaterial is Material 3 inspired, not a copy of Google's Material Components. It uses Android framework APIs and keeps the dependency surface small.

## License

Apache License 2.0.

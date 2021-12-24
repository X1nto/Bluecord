package mods.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import androidx.appcompat.app.AppCompatActivity;
import mods.activity.BlueSettingsActivity;
import mods.utils.ToastUtil;
public class FontSizePreference extends DialogPreference {
    private final int MAX_VALUE = 50;
    private final int MIN_VALUE = 1;
    private final String key;
    private NumberPicker picker;

    public FontSizePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.key = attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "key");
        setDialogTitle("Set font size (sp)");
        setNegativeButtonText("Discard");
        setPositiveButtonText("Save");
    }

    private int getValue() {
        return Prefs.getInt(this.key, 12);
    }

    private void setValue(int i) {
        Prefs.setInt(this.key, i);
    }

    @Override // android.preference.DialogPreference
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        this.picker.setMinValue(1);
        this.picker.setMaxValue(50);
        this.picker.setWrapSelectorWheel(false);
        this.picker.setOnLongPressUpdateInterval(50);
        this.picker.setValue(getValue());
    }

    @Override // android.preference.DialogPreference
    protected View onCreateDialogView() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        NumberPicker numberPicker = new NumberPicker(getContext());
        this.picker = numberPicker;
        numberPicker.setLayoutParams(layoutParams);
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.addView(this.picker);
        return frameLayout;
    }

    @Override // android.preference.DialogPreference
    protected void onDialogClosed(boolean z2) {
        if (z2) {
            this.picker.clearFocus();
            int value = this.picker.getValue();
            if (callChangeListener(Integer.valueOf(value))) {
                AppCompatActivity preferenceActivity = BlueSettingsActivity.getPreferenceActivity();
                ToastUtil.customToast(preferenceActivity, "Font size set to " + value + "sp");
                setValue(value);
            }
        }
    }

    @Override // android.preference.Preference
    protected Object onGetDefaultValue(TypedArray typedArray, int i) {
        return Integer.valueOf(typedArray.getInt(i, 1));
    }

    @Override // android.preference.Preference
    protected void onSetInitialValue(boolean z2, Object obj) {
        setValue(z2 ? getPersistedInt(1) : ((Integer) obj).intValue());
    }
}

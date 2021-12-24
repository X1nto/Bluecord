package mods.preference;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.AttributeSet;
import mods.DiscordTools;
import mods.activity.BlueSettingsActivity;
import mods.colorpicker.ColorPickerPreference;
import mods.colorpicker.ColorPickerView;
import mods.colorpicker.builder.ColorPickerDialogBuilder;
import mods.constants.PreferenceKeys;
import mods.utils.ToastUtil;
public class ColorPicker extends ColorPickerPreference {
    final String key;
    final String title;

    public ColorPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        String attributeValue = attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "key");
        this.key = attributeValue;
        this.title = attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "title");
        setDefaultValue(Integer.valueOf(Prefs.getInt(attributeValue, Color.parseColor(attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "defaultValue")))));
        setPersistent(true);
    }

    public /* synthetic */ void lambda$onClick$0$ColorPicker(DialogInterface dialogInterface, int i, Integer[] numArr) {
        setValue(i);
        Prefs.setInt(this.key, i);
        ToastUtil.customToast(BlueSettingsActivity.getPreferenceActivity(), "Color changed");
    }

    @Override // mods.colorpicker.ColorPickerPreference, android.preference.Preference
    protected void onClick() {
        Context context = getContext();
        boolean equals = "Custom Colors".equals(Prefs.getString(PreferenceKeys.COLOR_MODE, "Default"));
        if (!Prefs.getBoolean(PreferenceKeys.COLOR_AUTHORS_ENABLE, false) && (this.key.equals(PreferenceKeys.COLOR_AUTHORS_INCOMING) || this.key.equals(PreferenceKeys.COLOR_AUTHORS_OUTGOING))) {
            DiscordTools.basicAlert(context, "Hold Up!", "Please enable the \"Custom Message Name Colors\" option above before setting custom author colors!");
        } else if (!equals) {
            DiscordTools.basicAlert(context, "Hold Up!", "Please enable the \"Custom Colors\" option in the Text Color Mode menu before setting custom colors!");
        } else {
            ColorPickerDialogBuilder.with(context).setTitle(this.title).initialColor(this.selectedColor).wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE).density(this.density).showColorEdit(true).setNegativeButton("Cancel", (DialogInterface.OnClickListener) null).setPositiveButton("OK", new $$Lambda$ColorPicker$eG3S41cC3pbnNCe7GWzvVpGB4i0(this)).build().show();
        }
    }
}

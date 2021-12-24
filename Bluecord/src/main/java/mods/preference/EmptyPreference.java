package mods.preference;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;

import mods.constants.Constants;

public class EmptyPreference extends Preference {
    public EmptyPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutResource(Constants.blue_preference_layout_empty);
        setEnabled(false);
        setSelectable(false);
    }
}

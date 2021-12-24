package mods.preference;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import mods.DiscordTools;
public class SimpleDialogPreference extends Preference {
    private final String summary;
    private final String title;

    public SimpleDialogPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.title = attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "dialogTitle");
        this.summary = attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "dialogMessage");
        setOnPreferenceClickListener(new $$Lambda$SimpleDialogPreference$vZSYjwkTj4A2xuKfluvx_bjF0I(this));
    }

    public /* synthetic */ boolean lambda$new$0$SimpleDialogPreference(Preference preference) {
        DiscordTools.basicAlert(preference.getContext(), this.title, this.summary);
        return true;
    }
}

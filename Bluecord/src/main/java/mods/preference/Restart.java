package mods.preference;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import mods.DiscordTools;
import mods.activity.BlueSettingsActivity;
public class Restart extends Preference implements Preference.OnPreferenceClickListener {
    public Restart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnPreferenceClickListener(this);
    }

    @Override // android.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        DiscordTools.restartDiscord(BlueSettingsActivity.getPreferenceActivity());
        return true;
    }
}

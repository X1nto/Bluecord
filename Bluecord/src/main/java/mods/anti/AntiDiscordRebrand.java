package mods.anti;

import android.util.Log;
import com.bluecord.R;

import mods.constants.Constants;
import mods.constants.PreferenceKeys;
import mods.preference.Prefs;
public class AntiDiscordRebrand {
    public static boolean isEnabled() {
        return Prefs.getBoolean(PreferenceKeys.REMOVE_DISCORD_REBRAND_V2, false);
    }

    public static int overrideTheme(int i) {
        if (!isEnabled()) {
            return i;
        }
        if (i == Constants.STYLE_DARK) {
            return Constants.STYLE_DARK_NO_REBRAND;
        }
        if (i == Constants.STYLE_EVIL) {
            return Constants.STYLE_EVIL_NO_REBRAND;
        }
        if (i == Constants.STYLE_LIGHT) {
            return Constants.STYLE_LIGHT_NO_REBRAND;
        }
        Log.e("Bluecord", "unknown style res (" + i + ")");
        return i;
    }
}

package mods.preference;

import mods.constants.PreferenceKeys;
public class QuickAccessPrefs {
    private static boolean antiSpamEnabled;
    private static boolean statusIndicatorEnabled;

    static {
        reload();
    }

    public static boolean isAntiSpamEnabled() {
        return antiSpamEnabled;
    }

    public static boolean isBetterStatusIndicatorEnabled() {
        return statusIndicatorEnabled;
    }

    public static void reload() {
        antiSpamEnabled = Prefs.getBoolean(PreferenceKeys.ANTI_SPAM, false);
        statusIndicatorEnabled = Prefs.getBoolean(PreferenceKeys.BETTER_STATUS_INDICATOR, false);
    }
}

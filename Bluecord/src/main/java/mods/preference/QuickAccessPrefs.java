package mods.preference;

import mods.constants.PreferenceKeys;

public class QuickAccessPrefs {
    private static boolean antiSpamEnabled;
    private static boolean attachmentFileSizeEnabled;
    private static EmoteMode emoteMode;
    private static boolean showHiddenChannels;
    private static boolean statusIndicatorEnabled;

    static {
        reload();
    }

    public static EmoteMode getEmoteMode() {
        return emoteMode;
    }

    public static boolean isAntiSpamEnabled() {
        return antiSpamEnabled;
    }

    public static boolean isAttachmentFileSizeEnabled() {
        return attachmentFileSizeEnabled;
    }

    public static boolean isBetterStatusIndicatorEnabled() {
        return statusIndicatorEnabled;
    }

    public static boolean isShowHiddenChannelsEnabled() {
        return showHiddenChannels;
    }

    public static void reload() {
        antiSpamEnabled = Prefs.getBoolean(PreferenceKeys.ANTI_SPAM, false);
        statusIndicatorEnabled = Prefs.getBoolean(PreferenceKeys.BETTER_STATUS_INDICATOR, false);
        showHiddenChannels = Prefs.getBoolean(PreferenceKeys.REVEAL_HIDDEN_CHANNELS, false);
        attachmentFileSizeEnabled = Prefs.getBoolean(PreferenceKeys.SHOW_FILE_SIZES, false);
        emoteMode = new EmoteMode(Prefs.getString(PreferenceKeys.EMOTE_MODE, "Off"));
    }
}

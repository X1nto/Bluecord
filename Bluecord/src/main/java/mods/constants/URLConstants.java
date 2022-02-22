package mods.constants;

import mods.DiscordTools;
public final class URLConstants {
    private static final String BASE_URL = "https://bluesmods.com";
    private static final boolean IS_BETA = DiscordTools.getContext().getPackageName().toLowerCase().startsWith("com.bluecordbeta");

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getVersionString() {
        return Constants.VERSION_NAME + (IS_BETA ? " (Beta 2)" : "");
    }

    public static String phpLink() {
        return BASE_URL + (IS_BETA ? "/bluecord/beta.php" : "/bluecord.php");
    }
}

package mods.utils;

import java.util.regex.Pattern;
public class PatternUtils {
    private static final Pattern BLUECORD_PATTERN = Pattern.compile("bluecord", 2);
    private static final Pattern VALID_TOKEN = Pattern.compile("^mfa\\.[\\w\\-]{50,100}$|^[\\w\\-]{20,30}\\.[\\w\\-]{5,10}\\.[\\w\\-]{20,30}$");

    public static boolean isValidToken(String str) {
        return !StringUtils.isEmpty(str) && VALID_TOKEN.matcher(str).matches();
    }

    public static String removeAppName(String str) {
        return StringUtils.isEmpty(str) ? "" : BLUECORD_PATTERN.matcher(str).replaceAll("Discord");
    }
}

package mods.utils;

import android.util.Log;
import com.discord.utilities.rest.SendUtils;
public class DebugUtils {
    public static void dumpStack() {
        StringBuilder sb = new StringBuilder("Blue Stack\n------------------");
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            sb.append(stackTraceElement.toString().replace("[", "").replace("]", "").replace(",", "\n"));
        }
        Log.e("Blue Stack", sb.toString().trim());
    }

    public static void logRecursively(String str, String str2) {
        if (str2.length() > 4000) {
            Log.e(str, str2.substring(0, SendUtils.MAX_MESSAGE_CHARACTER_COUNT_PREMIUM));
            logRecursively(str, str2.substring(SendUtils.MAX_MESSAGE_CHARACTER_COUNT_PREMIUM));
            return;
        }
        Log.e(str, str2);
    }

    public static boolean showDebugMenu(boolean z2) {
        return true;
    }
}

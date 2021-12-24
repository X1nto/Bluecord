package mods.preference;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import mods.DiscordTools;
public class Prefs {
    private static final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(DiscordTools.getContext());

    public static boolean getBoolean(String str, boolean z2) {
        return getPreferences().getBoolean(str, z2);
    }

    public static float getFloat(String str, float f) {
        return getPreferences().getFloat(str, f);
    }

    public static int getInt(String str, int i) {
        return getPreferences().getInt(str, i);
    }

    public static long getLong(String str, long j) {
        return getPreferences().getLong(str, j);
    }

    public static SharedPreferences getPreferences() {
        return sp;
    }

    public static String getString(String str, String str2) {
        return getPreferences().getString(str, str2);
    }

    public static void removeValue(String str) {
        getPreferences().edit().remove(str).commit();
    }

    public static void removeValues(String... strArr) {
        if (strArr != null) {
            SharedPreferences.Editor edit = getPreferences().edit();
            for (String str : strArr) {
                if (str != null) {
                    edit.remove(str);
                }
            }
            edit.commit();
        }
    }

    public static void setBoolean(String str, boolean z2) {
        getPreferences().edit().putBoolean(str, z2).commit();
    }

    public static void setFloat(String str, float f) {
        getPreferences().edit().putFloat(str, f).commit();
    }

    public static void setInt(String str, int i) {
        getPreferences().edit().putInt(str, i).commit();
    }

    public static void setLong(String str, long j) {
        getPreferences().edit().putLong(str, j).commit();
    }

    public static void setString(String str, String str2) {
        getPreferences().edit().putString(str, str2).commit();
    }
}

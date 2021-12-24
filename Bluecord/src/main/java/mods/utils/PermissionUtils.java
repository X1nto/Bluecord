package mods.utils;

import android.app.Activity;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import mods.activity.BlueSettingsActivity;
import mods.constants.PreferenceKeys;
public class PermissionUtils {
    public static boolean hasStoragePermission() {
        return hasStoragePermission(BlueSettingsActivity.activity);
    }

    public static boolean hasStoragePermission(Activity activity) {
        boolean z2 = false;
        if (activity == null) {
            return false;
        }
        int checkSelfPermission = ActivityCompat.checkSelfPermission(activity, "android.permission.READ_EXTERNAL_STORAGE") + ActivityCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE");
        Log.e("PermissionUtils", "storage permission result = " + checkSelfPermission);
        if (checkSelfPermission == 0) {
            z2 = true;
        }
        if (!z2) {
            ActivityCompat.requestPermissions(activity, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 420);
        }
        return z2;
    }

    public static boolean needsPermissionForKey(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        return str.equals(PreferenceKeys.ANTI_DELETE_MODE) || str.equals(PreferenceKeys.ANTI_EDIT_MODE);
    }
}

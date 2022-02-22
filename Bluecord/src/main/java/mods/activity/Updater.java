package mods.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.Preference;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;

import com.bluecord.BuildConfig;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import mods.DiscordTools;
import mods.constants.Constants;
import mods.constants.URLConstants;
import mods.net.Net;
import mods.preference.Prefs;
import mods.utils.StringUtils;
import mods.utils.ToastUtil;

public class Updater extends Preference {
    private static final String UPDATE_FOUND_KEY = "21000_update_found";
    private static final long UPDATE_INTERVAL = TimeUnit.HOURS.toMillis(24);
    private static boolean hasRun = !BuildConfig.ENABLE_UPDATER; // added

    public Updater(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setSummary("Current Version: 2.1" + (URLConstants.IS_BETA ? " (Beta)" : ""));
        setOnPreferenceClickListener(new $$Lambda$Updater$OxaLdweWqQYog46KZzKCYsQZE8(context));
    }

    private static void alertUpdate(Context context, UpdateResult updateResult) {
        DiscordTools.newBuilder(context).setTitle("Update Available").setMessage(updateResult.getMessage()).setNegativeButton("Dismiss", (DialogInterface.OnClickListener) null).setPositiveButton("Download", new $$Lambda$Updater$SYkqatI9dn99rExMsXSExbJrGw8(context, updateResult)).show();
    }

    public static void checkFromLaunch(Activity activity) {
        if (!hasRun) {
            String lowerCase = activity.getApplicationInfo().loadLabel(activity.getPackageManager()).toString().toLowerCase();
            String packageName = activity.getPackageName();
            if ((!lowerCase.startsWith("bluecord") || (!packageName.equals("com.bluecord") &&!packageName.equals("com.bluecordbeta"))) && !Prefs.getBoolean("modded", false)) {
                DiscordTools
                    .newBuilder(activity)
                    .setTitle("Bluecord")
                    .setMessage(
                        "The app has been cloned, modified, or had the app name changed.\n" +
                        "\n" +
                        "This isn't a big deal, but some features may (unintentionally) break if you cloned it, and support cannot be offered for those issues.\n" +
                        "\n" +
                        "This app is BluecordOSS, and official releases are only found at https://github.com/X1nto/Bluecord"
                    )
                    .setNeutralButton("Never Show Again", $$Lambda$Updater$loU70nKtNuvEbeY7f5HdosPoNg.INSTANCE)
                    .setPositiveButton("Close", null)
                    .show();
                return;
            }
            new Thread(new $$Lambda$Updater$rRFrq4s_eKyZSpSK_i5yvKWLt5Y(activity)).start();
            hasRun = true;
        }
    }

    public static void checkFromPrefs(Context context) {
        UpdateResult result = getResult(true);
        if (!result.succeeded()) {
            DiscordTools.basicAlert(context, "Error", "Could not connect to check for an update. Please check your Internet connection and try again.");
        } else if (result.isUpdateAvailable()) {
            alertUpdate(context, result);
            markUpdate(true);
        } else {
            DiscordTools.basicAlert(context, "No Update Yet", result.getMessage());
        }
    }

    private static UpdateResult getResult(boolean z2) {
        long currentTimeMillis = System.currentTimeMillis() - getUpdatePrefs().getLong("last_check_time", -1);
        if (!z2) {
            long j = UPDATE_INTERVAL;
            if (currentTimeMillis < j) {
                Log.e("Updater", "delaying update check until " + new Date(getUpdatePrefs().getLong("last_check_time", -1) + j) + ", pulling from cache");
                return UpdateResult.parse(getUpdatePrefs().getString("update_data", null));
            }
        }
        Log.e("Updater", "checking for update");
        return UpdateResult.parse(Net.asyncRequest(URLConstants.phpLink() + "?updatecheck=" + Constants.VERSION_CODE, null));
    }

    static SharedPreferences getUpdatePrefs() {
        return DiscordTools.getContext().getSharedPreferences("update", 0);
    }

    static /* synthetic */ void lambda$alertUpdate$3(Context context, UpdateResult updateResult, DialogInterface dialogInterface, int i) {
        loadUrl(context, updateResult.getUpdateLink());
    }

    static /* synthetic */ void lambda$checkFromLaunch$1(DialogInterface dialogInterface, int i) {
        Prefs.setBoolean("modded", true);
    }

    static /* synthetic */ void lambda$checkFromLaunch$2(Activity activity) {
        UpdateResult result = getResult(false);
        if (result.succeeded()) {
            getUpdatePrefs().edit().putLong("last_check_time", System.currentTimeMillis()).apply();
            if (result.isUpdateAvailable()) {
                ToastUtil.toast("Bluecord Update Is Available!");
                markUpdate(true);
                return;
            }
            markUpdate(false);
        } else if (getUpdatePrefs().getBoolean(UPDATE_FOUND_KEY, false)) {
            ToastUtil.customToast(activity, "Bluecord Update Is Available!");
        }
    }

    static /* synthetic */ boolean lambda$new$0(Context context, Preference preference) {
        checkFromPrefs(context);
        return true;
    }

    private static void loadUrl(Context context, String str) {
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(StringUtils.isEmpty(str) ? URLConstants.getBaseUrl() : str)).addFlags(268435456));
    }

    private static void markUpdate(boolean z2) {
        getUpdatePrefs().edit().putBoolean(UPDATE_FOUND_KEY, z2).apply();
    }
}

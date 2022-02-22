package mods;

import android.annotation.SuppressLint;
import android.app.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import androidx.fragment.app.Fragment;
import com.discord.app.App;
import com.discord.app.AppActivity;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import mods.constants.Constants;
import mods.constants.PreferenceKeys;
import mods.preference.Prefs;

public class DiscordTools {
    public static void basicAlert(Context context, String str, String str2) {
        newBuilder(context).setTitle(str).setMessage(str2).setPositiveButton("Dismiss", (DialogInterface.OnClickListener) null).show();
    }

    public static void copyFile(String str, String str2) throws IOException {
        if (str.equalsIgnoreCase(str2)) return;
        var channel = new FileInputStream(str).getChannel();
        var channel2 = new FileOutputStream(str2, false).getChannel();
        channel.transferTo(0, channel.size(), channel2);
        channel2.close();
        channel.close();
    }

    public static void copyToClipboard(String str) {
        if (str != null) {
            ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData newPlainText = ClipData.newPlainText("", str);
            if (clipboardManager != null) {
                clipboardManager.setPrimaryClip(newPlainText);
            }
        }
    }

    public static boolean disableTyping() {
        return Prefs.getBoolean(PreferenceKeys.DISABLE_TYPING, false);
    }

    public static Fragment findFragmentByClass(Fragment fragment, Class<?> cls) {
        for (Fragment fragment2 : fragment.getParentFragmentManager().getFragments()) {
            Log.e("BlueFindFrag", "Found fragment: " + fragment2.getClass().getName());
            if (cls.isInstance(fragment2)) {
                return fragment2;
            }
        }
        return null;
    }

    public static String formatDate(long j) {
        return new SimpleDateFormat(ThemingTools.getDateFormat()).format(new Date(j));
    }

    public static String getAppDataDir() throws PackageManager.NameNotFoundException {
        return getApplication().getPackageManager().getPackageInfo(getApplication().getPackageName(), 0).applicationInfo.dataDir;
    }

    public static Application getApplication() {
        return App.app;
    }

    public static Context getContext() {
        return App.app.getApplicationContext();
    }

    public static Locale getCurrentLocale() {
        return Build.VERSION.SDK_INT >= 24 ? getContext().getResources().getConfiguration().getLocales().get(0) : getContext().getResources().getConfiguration().locale;
    }

    public static int getOrientation() {
        return getApplication().getResources().getConfiguration().orientation;
    }

    public static boolean isInPortrait() {
        return getOrientation() == 1;
    }

    public static AlertDialog.Builder newBuilder(Context context) {
        return new AlertDialog.Builder(context, 4).setIcon(Constants.bluecord_logo_big);
    }

    public static ProgressDialog newProgressDialog(Context context) {
        var dialog = new ProgressDialog(context, 4);
        dialog.setIcon(Constants.bluecord_logo_big);
        return dialog;
    }

    public static void promptRestart(Context context) {
        newBuilder(context)
            .setMessage("Restart to apply changes?")
            .setPositiveButton("Yes", (dialog, i) -> restartDiscord(context)) // $$Lambda$DiscordTools$b2FeP2T9xu7yfeMvVbeXECQK7uU
            .setNegativeButton("No", null)
            .show();
    }

    @SuppressLint({ "WrongConstant", "UnspecifiedImmutableFlag" })
    public static void restartDiscord(Context context) {
        ((AlarmManager) context.getSystemService(Context.ALARM_SERVICE)).setExact(AlarmManager.ELAPSED_REALTIME, 1500, PendingIntent.getActivity(context, 0, new Intent(context, AppActivity.Main.class), 134217728));
        Process.killProcess(Process.myPid());
    }

    public static void restoreToken(Context context, String str, boolean z2) {
        Prefs.getPreferences().edit().putString("STORE_AUTHED_TOKEN", str).putBoolean(PreferenceKeys.WAS_TOKEN_LOGIN, true).commit();
        if (z2) {
            restartDiscord(context);
        }
    }
}

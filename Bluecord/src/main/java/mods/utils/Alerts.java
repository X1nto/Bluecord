package mods.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import mods.DiscordTools;

public class Alerts {
    public static void alertDevMenuWarning(Fragment fragment) {
        DiscordTools.basicAlert(fragment.requireContext(), "Warning", "These are experimental tests written by Discord.\n\nModifying them can cause the app to crash or introduce unintended side effects.\n\nUse at your own risk.");
    }

    public static void alertNitroClick(Context context) {
        DiscordTools.newBuilder(context).setTitle("Discord Nitro").setMessage("Since this is a modified application, Google Play services do not work, meaning you cannot buy Nitro from the app.\n\nDownload Discord from the Play Store and log in there to purchase Nitro.").setNegativeButton("Exit", (DialogInterface.OnClickListener) null).setPositiveButton("Play Store", new $$Lambda$Alerts$K63pxTka425AnkNxJ_rV9Cmzot4(context)).show();
    }

    static /* synthetic */ void lambda$alertNitroClick$0(Context context, DialogInterface dialogInterface, int i) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.discord")));
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.discord")));
        }
    }
}

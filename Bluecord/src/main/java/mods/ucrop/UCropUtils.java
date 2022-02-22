package mods.ucrop;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import b.q.a.a;
import com.yalantis.ucrop.UCropActivity;
import com.yalantis.ucrop.model.AspectRatio;
import java.io.File;
import mods.DiscordTools;
import mods.activity.BlueSettingsActivity;
import mods.constants.PreferenceKeys;
import mods.preference.Prefs;

public class UCropUtils {
    public static void crop(Activity activity, Uri uri, Uri uri2, int i, boolean z2, int i2, int i3, AspectRatio... aspectRatioArr) {
        UCropBuilder uCropBuilder = new UCropBuilder();
        uCropBuilder.setCompressionQuality(i2);
        uCropBuilder.setFreeStyleCropEnabled(z2);
        uCropBuilder.withMaxResultSize(1000, 1000);
        if (i3 >= 0 && aspectRatioArr != null && aspectRatioArr.length > 0) {
            uCropBuilder.setAspectRatioOptions(i3, aspectRatioArr);
        }
        a aVar = new a(uri, uri2);
        aVar.b.putAll(uCropBuilder.getOptions());
        startUCrop(activity, aVar, i);
    }

    public static void cropCustomBackground(Activity activity, Uri uri) {
        Exception e;
        try {
            File croppedImagePath = getCroppedImagePath(true);
            float f = Prefs.getFloat(PreferenceKeys.OPTIMAL_CHAT_BG_WIDTH, -1.0f);
            float f2 = Prefs.getFloat(PreferenceKeys.OPTIMAL_CHAT_BG_HEIGHT, -1.0f);
            UCropBuilder uCropBuilder = new UCropBuilder();
            if (f <= 0.0f || f2 <= 0.0f || f2 <= f) {
                uCropBuilder.setAspectRatioOptions(0, new AspectRatio("Portrait", 9.0f, 15.0f), new AspectRatio("Landscape", 15.0f, 9.0f), new AspectRatio("Square", 1.0f, 1.0f));
            } else {
                uCropBuilder.setAspectRatioOptions(0, new AspectRatio("Optimal", f, f2), new AspectRatio("Portrait", 9.0f, 15.0f), new AspectRatio("Landscape", 15.0f, 9.0f), new AspectRatio("Square", 1.0f, 1.0f));
            }
            uCropBuilder.withMaxResultSize(1000, 1000);
            try {
                a aVar = new a(uri, Uri.fromFile(croppedImagePath));
                aVar.b.putAll(uCropBuilder.getOptions());
                try {
                    startUCrop(activity, aVar, BlueSettingsActivity.REQUEST_CODE_SET_BACKGROUND_UCROP);
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Exception e3) {
                e = e3;
                e.printStackTrace();
            }
        } catch (Exception e4) {
            e = e4;
            e.printStackTrace();
        }
    }

    public static File getCroppedImagePath(boolean z2) throws Exception {
        File file = new File(DiscordTools.getAppDataDir(), z2 ? "blue_backgrounds_temp" : "blue_backgrounds");
        file.mkdirs();
        File file2 = new File(file, String.format("%s.jpg", "chat_bg_normal"));
        if (!file2.exists()) {
            file2.createNewFile();
        }
        return file2;
    }

    public static Throwable getError(Intent intent) {
        return (Throwable) intent.getSerializableExtra(UCropConstants.EXTRA_ERROR);
    }

    public static Uri getOutput(Intent intent) {
        return (Uri) intent.getParcelableExtra(UCropConstants.EXTRA_OUTPUT_URI);
    }

    private static void startUCrop(Activity activity, a aVar, int i) {
        aVar.a.setClass(activity, UCropActivity.class);
        aVar.a.putExtras(aVar.b);
        activity.startActivityForResult(aVar.a, i);
    }
}

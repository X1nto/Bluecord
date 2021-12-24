package mods.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import mods.DiscordTools;
import mods.constants.Constants;

public class ToastUtil {
    private static final Handler handler = new Handler(Looper.getMainLooper());
    private static Toast lastToast;

    private static void cancel() {
        Toast toast = lastToast;
        if (toast != null) {
            toast.cancel();
        }
    }

    public static void customToast(Activity activity, String str) {
        if (activity == null || activity.getLayoutInflater() == null) {
            toast(str);
        } else {
            handler.post(new $$Lambda$ToastUtil$_w98IyEkxXF2kaxC4Kg862vLlk(activity, str));
        }
    }

    static /* synthetic */ void lambda$customToast$1(Activity activity, String str) {
        try {
            cancel();
            View inflate = activity.getLayoutInflater().inflate(Constants.blue_toast_layout, (ViewGroup) null);
            @SuppressLint("ResourceType") TextView textView = (TextView) inflate.findViewById(16908308);
            textView.setText(str);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            Toast toast = new Toast(activity);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(inflate);
            toast.show();
            lastToast = toast;
        } catch (Exception e) {
            e.printStackTrace();
            showBasicToast(str);
        }
    }

    static /* synthetic */ void lambda$toast$0(String str) {
        showBasicToast(str);
    }

    private static void showBasicToast(String str) {
        cancel();
        Toast makeText = Toast.makeText(DiscordTools.getApplication(), str, Toast.LENGTH_LONG);
        makeText.show();
        lastToast = makeText;
    }

    public static void toast(String str) {
        handler.post(new $$Lambda$ToastUtil$lpWbsx5CrpncVGnSx24LXXd14ZM(str));
    }
}

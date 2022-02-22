package mods.preference;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.preference.Preference;
import android.util.AttributeSet;
import mods.DiscordTools;
import mods.activity.BlueSettingsActivity;
import mods.constants.PreferenceKeys;
import mods.ucrop.UCropUtils;
import mods.utils.PermissionUtils;
import mods.utils.ToastUtil;
public class Background extends Preference {
    public Background(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnPreferenceClickListener(new $$Lambda$Background$FiNQmyHCUwWhjAhzbHVk1IrNigE(this));
    }

    static /* synthetic */ void lambda$new$0(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            BlueSettingsActivity.getPreferenceActivity().startActivityForResult(Intent.createChooser(new Intent().setType("image/*").setAction("android.intent.action.PICK"), "Select an image..."), 10);
        } else if (i == 1) {
            UCropUtils.cropCustomBackground(BlueSettingsActivity.getPreferenceActivity(), Uri.parse("https://picsum.photos/800/1200.jpg"));
        }
    }

    public /* synthetic */ boolean lambda$new$1$Background(Preference preference) {
        if (PermissionUtils.hasStoragePermission()) {
            if (!Prefs.getBoolean(PreferenceKeys.BACKGROUND_ENABLED, false)) {
                ToastUtil.customToast(BlueSettingsActivity.getPreferenceActivity(), "Enable toggle before choosing a background!");
                return true;
            }
            DiscordTools.newBuilder(getContext()).setTitle("[Chat background] Pick an action").setItems(new String[]{"Custom (pick from your files)", "Random (image from unsplash.com)"}, $$Lambda$Background$c2NPxK3D8ztd2Bs8YKzSLHpK9dY.INSTANCE).setPositiveButton("Exit", (DialogInterface.OnClickListener) null).show();
        }
        return true;
    }
}

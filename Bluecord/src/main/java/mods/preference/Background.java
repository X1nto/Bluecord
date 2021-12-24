package mods.preference;

import android.content.Context;
import android.content.Intent;
import android.preference.Preference;
import android.util.AttributeSet;
import mods.activity.BlueSettingsActivity;
import mods.utils.PermissionUtils;
public class Background extends Preference {
    public Background(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnPreferenceClickListener($$Lambda$Background$oJJNghE32tR1wo3dwBMqu_qJik0.INSTANCE);
    }

    static /* synthetic */ boolean lambda$new$0(Preference preference) {
        if (!PermissionUtils.hasStoragePermission()) {
            return true;
        }
        BlueSettingsActivity.getPreferenceActivity().startActivityForResult(Intent.createChooser(new Intent().setType("image/*").setAction("android.intent.action.PICK"), "Select an image..."), 10);
        return true;
    }
}

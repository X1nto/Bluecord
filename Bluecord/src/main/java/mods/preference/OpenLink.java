package mods.preference;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.preference.Preference;
import android.util.AttributeSet;
import mods.constants.URLConstants;
import mods.net.Net;
public class OpenLink extends Preference {
    public OpenLink(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnPreferenceClickListener(new $$Lambda$OpenLink$ZN_v6Blg4H3AM4_gpw3GW9O9BRw(this, attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "key"), attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "defaultValue")));
    }

    public /* synthetic */ boolean lambda$new$0$OpenLink(String str, String str2, Preference preference) {
        String asyncRequest = Net.asyncRequest(URLConstants.phpLink() + "?" + str, null);
        getContext().startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse(asyncRequest == null ? str2 : asyncRequest)));
        return true;
    }
}

package mods.preference;

import android.content.Context;
import android.content.DialogInterface;
import android.preference.Preference;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.widget.ScrollView;
import android.widget.TextView;
import mods.DiscordTools;
import mods.constants.URLConstants;
import mods.net.Net;
public class URIPreference extends Preference {
    public URIPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnPreferenceClickListener(new $$Lambda$URIPreference$tNSqoMILBDlILVMV_tepR_Rv4kg(attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "key"), attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "title")));
    }

    static /* synthetic */ boolean lambda$new$0(String str, String str2, Preference preference) {
        String asyncRequest = Net.asyncRequest(URLConstants.phpLink() + "?" + str, null);
        ScrollView scrollView = new ScrollView(preference.getContext());
        TextView textView = new TextView(preference.getContext());
        textView.setText(Html.fromHtml(asyncRequest == null ? "Check your internet connection, and try again." : asyncRequest.replace("\n", "<br>")));
        textView.setPadding(20, 20, 20, 20);
        textView.setTextSize(16.0f);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        scrollView.addView(textView);
        DiscordTools.newBuilder(preference.getContext()).setTitle(str2).setView(scrollView).setPositiveButton("Dismiss", (DialogInterface.OnClickListener) null).create().show();
        return true;
    }
}

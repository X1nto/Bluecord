package mods.preference;

import android.preference.Preference;
/* compiled from: lambda */
/* renamed from: mods.preference.-$$Lambda$OpenLink$ZN_v6Blg4H3AM4_gpw3GW9O9BRw  reason: invalid class name */
public final /* synthetic */ class $$Lambda$OpenLink$ZN_v6Blg4H3AM4_gpw3GW9O9BRw implements Preference.OnPreferenceClickListener {
    public final /* synthetic */ OpenLink f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ $$Lambda$OpenLink$ZN_v6Blg4H3AM4_gpw3GW9O9BRw(OpenLink openLink, String str, String str2) {
        this.f$0 = openLink;
        this.f$1 = str;
        this.f$2 = str2;
    }

    @Override // android.preference.Preference.OnPreferenceClickListener
    public final boolean onPreferenceClick(Preference preference) {
        return this.f$0.lambda$new$0$OpenLink(this.f$1, this.f$2, preference);
    }
}

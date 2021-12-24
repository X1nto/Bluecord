package mods.preference;

import android.preference.Preference;
/* compiled from: lambda */
/* renamed from: mods.preference.-$$Lambda$URIPreference$tNSqoMILBDlILVMV_tepR_Rv4kg  reason: invalid class name */
public final /* synthetic */ class $$Lambda$URIPreference$tNSqoMILBDlILVMV_tepR_Rv4kg implements Preference.OnPreferenceClickListener {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ $$Lambda$URIPreference$tNSqoMILBDlILVMV_tepR_Rv4kg(String str, String str2) {
        this.f$0 = str;
        this.f$1 = str2;
    }

    @Override // android.preference.Preference.OnPreferenceClickListener
    public final boolean onPreferenceClick(Preference preference) {
        return URIPreference.lambda$new$0(this.f$0, this.f$1, preference);
    }
}

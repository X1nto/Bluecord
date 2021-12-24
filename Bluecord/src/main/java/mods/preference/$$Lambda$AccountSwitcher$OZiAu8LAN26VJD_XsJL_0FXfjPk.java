package mods.preference;

import android.content.Context;
import android.preference.Preference;
/* compiled from: lambda */
/* renamed from: mods.preference.-$$Lambda$AccountSwitcher$OZiAu8LAN26VJD_XsJL_0FXfjPk  reason: invalid class name */
public final /* synthetic */ class $$Lambda$AccountSwitcher$OZiAu8LAN26VJD_XsJL_0FXfjPk implements Preference.OnPreferenceClickListener {
    public final /* synthetic */ AccountSwitcher f$0;
    public final /* synthetic */ Context f$1;

    public /* synthetic */ $$Lambda$AccountSwitcher$OZiAu8LAN26VJD_XsJL_0FXfjPk(AccountSwitcher accountSwitcher, Context context) {
        this.f$0 = accountSwitcher;
        this.f$1 = context;
    }

    @Override // android.preference.Preference.OnPreferenceClickListener
    public final boolean onPreferenceClick(Preference preference) {
        return this.f$0.lambda$new$1$AccountSwitcher(this.f$1, preference);
    }
}

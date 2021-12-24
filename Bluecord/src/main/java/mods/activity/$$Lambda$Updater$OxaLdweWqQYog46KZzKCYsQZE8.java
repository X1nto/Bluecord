package mods.activity;

import android.content.Context;
import android.preference.Preference;
/* compiled from: lambda */
/* renamed from: mods.activity.-$$Lambda$Updater$O-xaLdweWqQYog46KZzKCYsQZE8  reason: invalid class name */
public final /* synthetic */ class $$Lambda$Updater$OxaLdweWqQYog46KZzKCYsQZE8 implements Preference.OnPreferenceClickListener {
    public final /* synthetic */ Context f$0;

    public /* synthetic */ $$Lambda$Updater$OxaLdweWqQYog46KZzKCYsQZE8(Context context) {
        this.f$0 = context;
    }

    @Override // android.preference.Preference.OnPreferenceClickListener
    public final boolean onPreferenceClick(Preference preference) {
        return Updater.lambda$new$0(this.f$0, preference);
    }
}

package mods.preference;

import android.content.Context;
import android.preference.Preference;
/* compiled from: lambda */
/* renamed from: mods.preference.-$$Lambda$FeatureRequest$AyHlU3w7mYIR69VjJHjaVpUgeug  reason: invalid class name */
public final /* synthetic */ class $$Lambda$FeatureRequest$AyHlU3w7mYIR69VjJHjaVpUgeug implements Preference.OnPreferenceClickListener {
    public final /* synthetic */ Context f$0;

    public /* synthetic */ $$Lambda$FeatureRequest$AyHlU3w7mYIR69VjJHjaVpUgeug(Context context) {
        this.f$0 = context;
    }

    @Override // android.preference.Preference.OnPreferenceClickListener
    public final boolean onPreferenceClick(Preference preference) {
        return FeatureRequest.lambda$new$1(this.f$0, preference);
    }
}

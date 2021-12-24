package mods.preference;

import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
/* compiled from: lambda */
/* renamed from: mods.preference.-$$Lambda$BackgroundBlurPreference$3qsQCcvrRmJwe84gMdspjOoZYbo  reason: invalid class name */
public final /* synthetic */ class $$Lambda$BackgroundBlurPreference$3qsQCcvrRmJwe84gMdspjOoZYbo implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ BackgroundBlurPreference f$0;
    public final /* synthetic */ SeekBar f$1;
    public final /* synthetic */ TextView f$2;

    public /* synthetic */ $$Lambda$BackgroundBlurPreference$3qsQCcvrRmJwe84gMdspjOoZYbo(BackgroundBlurPreference backgroundBlurPreference, SeekBar seekBar, TextView textView) {
        this.f$0 = backgroundBlurPreference;
        this.f$1 = seekBar;
        this.f$2 = textView;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
        this.f$0.lambda$new$0$BackgroundBlurPreference(this.f$1, this.f$2, compoundButton, z2);
    }
}

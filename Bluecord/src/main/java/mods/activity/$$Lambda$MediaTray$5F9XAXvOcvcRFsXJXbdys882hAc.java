package mods.activity;

import android.content.DialogInterface;
/* compiled from: lambda */
/* renamed from: mods.activity.-$$Lambda$MediaTray$5F9XAXvOcvcRFsXJXbdys882hAc  reason: invalid class name */
public final /* synthetic */ class $$Lambda$MediaTray$5F9XAXvOcvcRFsXJXbdys882hAc implements DialogInterface.OnMultiChoiceClickListener {
    public final /* synthetic */ boolean[] f$0;

    public /* synthetic */ $$Lambda$MediaTray$5F9XAXvOcvcRFsXJXbdys882hAc(boolean[] zArr) {
        this.f$0 = zArr;
    }

    @Override // android.content.DialogInterface.OnMultiChoiceClickListener
    public final void onClick(DialogInterface dialogInterface, int i, boolean z2) {
        MediaTray.lambda$deleteCommand$4(this.f$0, dialogInterface, i, z2);
    }
}

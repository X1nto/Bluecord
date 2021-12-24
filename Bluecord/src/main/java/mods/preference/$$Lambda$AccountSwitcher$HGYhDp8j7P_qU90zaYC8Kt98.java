package mods.preference;

import android.content.DialogInterface;
/* compiled from: lambda */
/* renamed from: mods.preference.-$$Lambda$AccountSwitcher$HGYhD-p8j7P_qU9-0zaYC8Kt-98  reason: invalid class name */
public final /* synthetic */ class $$Lambda$AccountSwitcher$HGYhDp8j7P_qU90zaYC8Kt98 implements DialogInterface.OnMultiChoiceClickListener {
    public final /* synthetic */ boolean[] f$0;

    public /* synthetic */ $$Lambda$AccountSwitcher$HGYhDp8j7P_qU90zaYC8Kt98(boolean[] zArr) {
        this.f$0 = zArr;
    }

    @Override // android.content.DialogInterface.OnMultiChoiceClickListener
    public final void onClick(DialogInterface dialogInterface, int i, boolean z2) {
        AccountSwitcher.lambda$deleteBackups$2(this.f$0, dialogInterface, i, z2);
    }
}

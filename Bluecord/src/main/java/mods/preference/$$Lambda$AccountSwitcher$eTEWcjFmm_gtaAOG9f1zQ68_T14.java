package mods.preference;

import android.content.Context;
import android.content.DialogInterface;
/* compiled from: lambda */
/* renamed from: mods.preference.-$$Lambda$AccountSwitcher$eTEWcjFmm_gtaAOG9f1zQ68_T14  reason: invalid class name */
public final /* synthetic */ class $$Lambda$AccountSwitcher$eTEWcjFmm_gtaAOG9f1zQ68_T14 implements DialogInterface.OnClickListener {
    public final /* synthetic */ Context f$0;

    public /* synthetic */ $$Lambda$AccountSwitcher$eTEWcjFmm_gtaAOG9f1zQ68_T14(Context context) {
        this.f$0 = context;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        AccountSwitcher.lambda$deleteBackups$3(this.f$0, dialogInterface, i);
    }
}

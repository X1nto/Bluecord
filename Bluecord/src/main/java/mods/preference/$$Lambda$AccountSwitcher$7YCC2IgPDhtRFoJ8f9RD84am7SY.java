package mods.preference;

import android.content.Context;
import android.content.DialogInterface;
import mods.preference.AccountSwitcher;
/* compiled from: lambda */
/* renamed from: mods.preference.-$$Lambda$AccountSwitcher$7YCC2IgPDhtRFoJ8f9RD84am7SY  reason: invalid class name */
public final /* synthetic */ class $$Lambda$AccountSwitcher$7YCC2IgPDhtRFoJ8f9RD84am7SY implements DialogInterface.OnClickListener {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ AccountSwitcher.AccountBackup f$1;

    public /* synthetic */ $$Lambda$AccountSwitcher$7YCC2IgPDhtRFoJ8f9RD84am7SY(Context context, AccountSwitcher.AccountBackup accountBackup) {
        this.f$0 = context;
        this.f$1 = accountBackup;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        AccountSwitcher.lambda$restoreBackup$6(this.f$0, this.f$1, dialogInterface, i);
    }
}

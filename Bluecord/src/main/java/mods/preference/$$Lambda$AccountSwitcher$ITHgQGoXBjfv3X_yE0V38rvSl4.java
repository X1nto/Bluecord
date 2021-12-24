package mods.preference;

import android.content.Context;
import android.content.DialogInterface;
import java.util.ArrayList;
/* compiled from: lambda */
/* renamed from: mods.preference.-$$Lambda$AccountSwitcher$ITHgQGoXBjfv3X_yE0V-38rvSl4  reason: invalid class name */
public final /* synthetic */ class $$Lambda$AccountSwitcher$ITHgQGoXBjfv3X_yE0V38rvSl4 implements DialogInterface.OnClickListener {
    public final /* synthetic */ ArrayList f$0;
    public final /* synthetic */ Context f$1;

    public /* synthetic */ $$Lambda$AccountSwitcher$ITHgQGoXBjfv3X_yE0V38rvSl4(ArrayList arrayList, Context context) {
        this.f$0 = arrayList;
        this.f$1 = context;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        AccountSwitcher.lambda$restoreBackup$7(this.f$0, this.f$1, dialogInterface, i);
    }
}

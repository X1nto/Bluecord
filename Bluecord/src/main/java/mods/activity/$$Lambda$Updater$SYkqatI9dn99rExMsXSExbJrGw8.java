package mods.activity;

import android.content.Context;
import android.content.DialogInterface;
/* compiled from: lambda */
/* renamed from: mods.activity.-$$Lambda$Updater$SYkqatI9dn99rExMsXSExbJrGw8  reason: invalid class name */
public final /* synthetic */ class $$Lambda$Updater$SYkqatI9dn99rExMsXSExbJrGw8 implements DialogInterface.OnClickListener {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ UpdateResult f$1;

    public /* synthetic */ $$Lambda$Updater$SYkqatI9dn99rExMsXSExbJrGw8(Context context, UpdateResult updateResult) {
        this.f$0 = context;
        this.f$1 = updateResult;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        Updater.lambda$alertUpdate$3(this.f$0, this.f$1, dialogInterface, i);
    }
}

package mods.preference;

import android.content.Context;
import android.content.DialogInterface;
/* compiled from: lambda */
/* renamed from: mods.preference.-$$Lambda$AccountSwitcher$XDMy8LxQ1rqXcQVKcOOcrR8g0LI  reason: invalid class name */
public final /* synthetic */ class $$Lambda$AccountSwitcher$XDMy8LxQ1rqXcQVKcOOcrR8g0LI implements DialogInterface.OnClickListener {
    public final /* synthetic */ AccountSwitcher f$0;
    public final /* synthetic */ Context f$1;

    public /* synthetic */ $$Lambda$AccountSwitcher$XDMy8LxQ1rqXcQVKcOOcrR8g0LI(AccountSwitcher accountSwitcher, Context context) {
        this.f$0 = accountSwitcher;
        this.f$1 = context;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f$0.lambda$new$0$AccountSwitcher(this.f$1, dialogInterface, i);
    }
}

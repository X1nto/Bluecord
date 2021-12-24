package mods.activity;

import android.content.DialogInterface;
import com.discord.app.AppFragment;
/* compiled from: lambda */
/* renamed from: mods.activity.-$$Lambda$LoginPageOptions$PYPObRGyIWROwE1DduQ74BnbZdU  reason: invalid class name */
public final /* synthetic */ class $$Lambda$LoginPageOptions$PYPObRGyIWROwE1DduQ74BnbZdU implements DialogInterface.OnClickListener {
    public final /* synthetic */ AppFragment f$0;

    public /* synthetic */ $$Lambda$LoginPageOptions$PYPObRGyIWROwE1DduQ74BnbZdU(AppFragment appFragment) {
        this.f$0 = appFragment;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        LoginPageOptions.lambda$init$0(this.f$0, dialogInterface, i);
    }
}

package mods.preference;

import android.content.Context;
import android.content.DialogInterface;
import com.discord.models.user.MeUser;
/* compiled from: lambda */
/* renamed from: mods.preference.-$$Lambda$AccountSwitcher$SVONS3r_xhzFcLNn3TwITXjLtjA  reason: invalid class name */
public final /* synthetic */ class $$Lambda$AccountSwitcher$SVONS3r_xhzFcLNn3TwITXjLtjA implements DialogInterface.OnClickListener {
    public final /* synthetic */ MeUser f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Context f$3;

    public /* synthetic */ $$Lambda$AccountSwitcher$SVONS3r_xhzFcLNn3TwITXjLtjA(MeUser meUser, String str, String str2, Context context) {
        this.f$0 = meUser;
        this.f$1 = str;
        this.f$2 = str2;
        this.f$3 = context;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        AccountSwitcher.lambda$createBackup$5(this.f$0, this.f$1, this.f$2, this.f$3, dialogInterface, i);
    }
}

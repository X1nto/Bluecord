package mods.preference;

import android.content.Context;
import android.content.DialogInterface;
import java.util.ArrayList;
/* compiled from: lambda */
/* renamed from: mods.preference.-$$Lambda$AccountSwitcher$xO_oX9Qr5TfxBx1JzzzKRJ0tIv4  reason: invalid class name */
public final /* synthetic */ class $$Lambda$AccountSwitcher$xO_oX9Qr5TfxBx1JzzzKRJ0tIv4 implements DialogInterface.OnClickListener {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ Context f$2;

    public /* synthetic */ $$Lambda$AccountSwitcher$xO_oX9Qr5TfxBx1JzzzKRJ0tIv4(boolean[] zArr, ArrayList arrayList, Context context) {
        this.f$0 = zArr;
        this.f$1 = arrayList;
        this.f$2 = context;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        AccountSwitcher.lambda$deleteBackups$4(this.f$0, this.f$1, this.f$2, dialogInterface, i);
    }
}

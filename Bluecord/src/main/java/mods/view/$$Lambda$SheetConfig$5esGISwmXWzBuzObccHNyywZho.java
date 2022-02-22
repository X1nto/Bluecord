package mods.view;

import android.content.DialogInterface;
import androidx.fragment.app.FragmentActivity;
/* compiled from: lambda */
/* renamed from: mods.view.-$$Lambda$SheetConfig$5esGISwmXWzBuzO-bccHNyywZho  reason: invalid class name */
public final /* synthetic */ class $$Lambda$SheetConfig$5esGISwmXWzBuzObccHNyywZho implements DialogInterface.OnClickListener {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ FragmentActivity f$2;

    public /* synthetic */ $$Lambda$SheetConfig$5esGISwmXWzBuzObccHNyywZho(String str, String str2, FragmentActivity fragmentActivity) {
        this.f$0 = str;
        this.f$1 = str2;
        this.f$2 = fragmentActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        SheetConfig.lambda$configureGuildSheet$8(this.f$0, this.f$1, this.f$2, dialogInterface, i);
    }
}

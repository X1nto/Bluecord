package mods.view;

import android.content.DialogInterface;
import androidx.fragment.app.FragmentActivity;
/* compiled from: lambda */
/* renamed from: mods.view.-$$Lambda$SheetConfig$zWy0znaEdSPHsmkpE0MX28SnXBM  reason: invalid class name */
/* loaded from: classes4.dex */
public final /* synthetic */ class $$Lambda$SheetConfig$zWy0znaEdSPHsmkpE0MX28SnXBM implements DialogInterface.OnClickListener {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ FragmentActivity f$2;

    public /* synthetic */ $$Lambda$SheetConfig$zWy0znaEdSPHsmkpE0MX28SnXBM(String str, String str2, FragmentActivity fragmentActivity) {
        this.f$0 = str;
        this.f$1 = str2;
        this.f$2 = fragmentActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        SheetConfig.lambda$configureGuildSheet$7(this.f$0, this.f$1, this.f$2, dialogInterface, i);
    }
}

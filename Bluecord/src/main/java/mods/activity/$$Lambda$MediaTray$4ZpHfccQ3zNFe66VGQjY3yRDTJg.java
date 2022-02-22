package mods.activity;

import android.content.DialogInterface;
/* compiled from: lambda */
/* renamed from: mods.activity.-$$Lambda$MediaTray$4ZpHfccQ3zNFe66VGQjY3yRDTJg  reason: invalid class name */
/* loaded from: classes4.dex */
public final /* synthetic */ class $$Lambda$MediaTray$4ZpHfccQ3zNFe66VGQjY3yRDTJg implements DialogInterface.OnMultiChoiceClickListener {
    public final /* synthetic */ boolean[] f$0;

    public /* synthetic */ $$Lambda$MediaTray$4ZpHfccQ3zNFe66VGQjY3yRDTJg(boolean[] zArr) {
        this.f$0 = zArr;
    }

    @Override // android.content.DialogInterface.OnMultiChoiceClickListener
    public final void onClick(DialogInterface dialogInterface, int i, boolean z2) {
        MediaTray.lambda$deleteCommand$7(this.f$0, dialogInterface, i, z2);
    }
}

package mods.activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
/* compiled from: lambda */
/* renamed from: mods.activity.-$$Lambda$MediaTray$mCL8HTEQv7blRxFEDMUn58iQ_-A  reason: invalid class name */
/* loaded from: classes4.dex */
public final /* synthetic */ class $$Lambda$MediaTray$mCL8HTEQv7blRxFEDMUn58iQ_A implements DialogInterface.OnClickListener {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ SharedPreferences f$2;
    public final /* synthetic */ Object[] f$3;

    public /* synthetic */ $$Lambda$MediaTray$mCL8HTEQv7blRxFEDMUn58iQ_A(int i, boolean[] zArr, SharedPreferences sharedPreferences, Object[] objArr) {
        this.f$0 = i;
        this.f$1 = zArr;
        this.f$2 = sharedPreferences;
        this.f$3 = objArr;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        MediaTray.lambda$deleteCommand$8(this.f$0, this.f$1, this.f$2, this.f$3, dialogInterface, i);
    }
}

package mods.activity;

import androidx.fragment.app.FragmentActivity;
/* compiled from: lambda */
/* renamed from: mods.activity.-$$Lambda$MediaTray$ZfxKJ4b1Qr9Ex54GaP4MEqD9B3A  reason: invalid class name */
/* loaded from: classes4.dex */
public final /* synthetic */ class $$Lambda$MediaTray$ZfxKJ4b1Qr9Ex54GaP4MEqD9B3A implements Runnable {
    public final /* synthetic */ MediaTray f$0;
    public final /* synthetic */ FragmentActivity f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ $$Lambda$MediaTray$ZfxKJ4b1Qr9Ex54GaP4MEqD9B3A(MediaTray mediaTray, FragmentActivity fragmentActivity, String str, String str2) {
        this.f$0 = mediaTray;
        this.f$1 = fragmentActivity;
        this.f$2 = str;
        this.f$3 = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.lambda$addCommand$6$MediaTray(this.f$1, this.f$2, this.f$3);
    }
}

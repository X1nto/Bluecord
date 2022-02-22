package mods.view;

import androidx.fragment.app.FragmentActivity;
/* compiled from: lambda */
/* renamed from: mods.view.-$$Lambda$SheetConfig$u0DzA_ix2xBsQdgOrP6Jit-qa_8  reason: invalid class name */
/* loaded from: classes4.dex */
public final /* synthetic */ class $$Lambda$SheetConfig$u0DzA_ix2xBsQdgOrP6Jitqa_8 implements Runnable {
    public final /* synthetic */ FragmentActivity f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ $$Lambda$SheetConfig$u0DzA_ix2xBsQdgOrP6Jitqa_8(FragmentActivity fragmentActivity, String str, String str2) {
        this.f$0 = fragmentActivity;
        this.f$1 = str;
        this.f$2 = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SheetConfig.lambda$configureGuildSheet$6(this.f$0, this.f$1, this.f$2);
    }
}

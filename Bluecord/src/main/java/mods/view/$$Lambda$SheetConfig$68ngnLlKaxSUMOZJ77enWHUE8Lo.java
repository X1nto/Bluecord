package mods.view;

import androidx.fragment.app.FragmentActivity;
/* compiled from: lambda */
/* renamed from: mods.view.-$$Lambda$SheetConfig$68ngnLlKaxSUMOZJ77enWHUE8Lo  reason: invalid class name */
public final /* synthetic */ class $$Lambda$SheetConfig$68ngnLlKaxSUMOZJ77enWHUE8Lo implements Runnable {
    public final /* synthetic */ FragmentActivity f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ $$Lambda$SheetConfig$68ngnLlKaxSUMOZJ77enWHUE8Lo(FragmentActivity fragmentActivity, String str, String str2) {
        this.f$0 = fragmentActivity;
        this.f$1 = str;
        this.f$2 = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SheetConfig.lambda$configureGuildSheet$7(this.f$0, this.f$1, this.f$2);
    }
}

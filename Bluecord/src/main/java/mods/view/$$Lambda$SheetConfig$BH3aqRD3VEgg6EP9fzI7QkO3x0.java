package mods.view;

import androidx.appcompat.widget.AppCompatImageView;
/* compiled from: lambda */
/* renamed from: mods.view.-$$Lambda$SheetConfig$BH3aqRD3VEgg6EP9fzI7QkO3-x0  reason: invalid class name */
public final /* synthetic */ class $$Lambda$SheetConfig$BH3aqRD3VEgg6EP9fzI7QkO3x0 implements Runnable {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ AppCompatImageView f$1;

    public /* synthetic */ $$Lambda$SheetConfig$BH3aqRD3VEgg6EP9fzI7QkO3x0(int i, AppCompatImageView appCompatImageView) {
        this.f$0 = i;
        this.f$1 = appCompatImageView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SheetConfig.lambda$modifyStatusIndicator$0(this.f$0, this.f$1);
    }
}

package mods.activity;

import android.app.Activity;
/* compiled from: lambda */
/* renamed from: mods.activity.-$$Lambda$p9UeKBysYU6UI3zESHP681Bv18A  reason: invalid class name */
public final /* synthetic */ class $$Lambda$p9UeKBysYU6UI3zESHP681Bv18A implements Runnable {
    public final /* synthetic */ Activity f$0;

    public /* synthetic */ $$Lambda$p9UeKBysYU6UI3zESHP681Bv18A(Activity activity) {
        this.f$0 = activity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.recreate();
    }
}

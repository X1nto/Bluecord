package mods.utils;

import android.app.Activity;
/* compiled from: lambda */
/* renamed from: mods.utils.-$$Lambda$ToastUtil$_-w98IyEkxXF2kaxC4Kg862vLlk  reason: invalid class name */
public final /* synthetic */ class $$Lambda$ToastUtil$_w98IyEkxXF2kaxC4Kg862vLlk implements Runnable {
    public final /* synthetic */ Activity f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ $$Lambda$ToastUtil$_w98IyEkxXF2kaxC4Kg862vLlk(Activity activity, String str) {
        this.f$0 = activity;
        this.f$1 = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ToastUtil.lambda$customToast$1(this.f$0, this.f$1);
    }
}

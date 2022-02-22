package mods.utils.translate;

import android.app.Activity;
import mods.utils.translate.Translate;
/* compiled from: lambda */
/* renamed from: mods.utils.translate.-$$Lambda$Translate$1$hIpYzBg8pQcjUc2g8sO9eK2fpyw  reason: invalid class name */
public final /* synthetic */ class $$Lambda$Translate$1$hIpYzBg8pQcjUc2g8sO9eK2fpyw implements Runnable {
    public final /* synthetic */ Activity f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ $$Lambda$Translate$1$hIpYzBg8pQcjUc2g8sO9eK2fpyw(Activity activity, String str) {
        this.f$0 = activity;
        this.f$1 = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Translate.AnonymousClass1.lambda$onResult$1(this.f$0, this.f$1);
    }
}

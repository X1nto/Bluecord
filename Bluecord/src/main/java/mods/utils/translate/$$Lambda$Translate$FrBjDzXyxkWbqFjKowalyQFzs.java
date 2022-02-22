package mods.utils.translate;

import android.app.Activity;
/* compiled from: lambda */
/* renamed from: mods.utils.translate.-$$Lambda$Translate$-FrBjDzXyxkWbqFjKowalyQ-Fzs  reason: invalid class name */
public final /* synthetic */ class $$Lambda$Translate$FrBjDzXyxkWbqFjKowalyQFzs implements Runnable {
    public final /* synthetic */ Activity f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ $$Lambda$Translate$FrBjDzXyxkWbqFjKowalyQFzs(Activity activity, String str) {
        this.f$0 = activity;
        this.f$1 = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Translate.lambda$showTranslateDialog$1(this.f$0, this.f$1);
    }
}

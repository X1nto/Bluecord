package mods.activity;

import android.content.SharedPreferences;
/* compiled from: lambda */
/* renamed from: mods.activity.-$$Lambda$MediaTray$j2WdHXyyZNqXbr4usBhOYeJWqoo  reason: invalid class name */
public final /* synthetic */ class $$Lambda$MediaTray$j2WdHXyyZNqXbr4usBhOYeJWqoo implements Runnable {
    public final /* synthetic */ MediaTray f$0;
    public final /* synthetic */ CharSequence[] f$1;
    public final /* synthetic */ boolean[] f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ SharedPreferences f$4;
    public final /* synthetic */ Object[] f$5;

    public /* synthetic */ $$Lambda$MediaTray$j2WdHXyyZNqXbr4usBhOYeJWqoo(MediaTray mediaTray, CharSequence[] charSequenceArr, boolean[] zArr, int i, SharedPreferences sharedPreferences, Object[] objArr) {
        this.f$0 = mediaTray;
        this.f$1 = charSequenceArr;
        this.f$2 = zArr;
        this.f$3 = i;
        this.f$4 = sharedPreferences;
        this.f$5 = objArr;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.lambda$deleteCommand$6$MediaTray(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}

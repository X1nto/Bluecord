package mods.view;

import android.view.View;
/* compiled from: lambda */
/* renamed from: mods.view.-$$Lambda$SheetConfig$f0O-28rl4NxGFUDM_X6RxV6gkao  reason: invalid class name */
/* loaded from: classes4.dex */
public final /* synthetic */ class $$Lambda$SheetConfig$f0O28rl4NxGFUDM_X6RxV6gkao implements View.OnClickListener {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ View f$2;

    public /* synthetic */ $$Lambda$SheetConfig$f0O28rl4NxGFUDM_X6RxV6gkao(String str, String str2, View view) {
        this.f$0 = str;
        this.f$1 = str2;
        this.f$2 = view;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SheetConfig.lambda$openUrlAsAttachment$9(this.f$0, this.f$1, this.f$2, view);
    }
}

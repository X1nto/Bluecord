package mods.view;

import android.view.View;
/* compiled from: lambda */
/* renamed from: mods.view.-$$Lambda$SheetConfig$CjVdaYJev4eAHHLeypTsOK7KpWw  reason: invalid class name */
public final /* synthetic */ class $$Lambda$SheetConfig$CjVdaYJev4eAHHLeypTsOK7KpWw implements View.OnClickListener {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ View f$2;

    public /* synthetic */ $$Lambda$SheetConfig$CjVdaYJev4eAHHLeypTsOK7KpWw(String str, String str2, View view) {
        this.f$0 = str;
        this.f$1 = str2;
        this.f$2 = view;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SheetConfig.lambda$openUrlAsAttachment$8(this.f$0, this.f$1, this.f$2, view);
    }
}

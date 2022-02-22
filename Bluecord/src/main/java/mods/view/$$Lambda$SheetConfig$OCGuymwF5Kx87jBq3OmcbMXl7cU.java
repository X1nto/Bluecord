package mods.view;

import android.view.View;
/* compiled from: lambda */
/* renamed from: mods.view.-$$Lambda$SheetConfig$OCGuymwF5Kx87jBq3OmcbMXl7cU  reason: invalid class name */
public final /* synthetic */ class $$Lambda$SheetConfig$OCGuymwF5Kx87jBq3OmcbMXl7cU implements View.OnClickListener {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ View f$2;

    public /* synthetic */ $$Lambda$SheetConfig$OCGuymwF5Kx87jBq3OmcbMXl7cU(String str, String str2, View view) {
        this.f$0 = str;
        this.f$1 = str2;
        this.f$2 = view;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SheetConfig.lambda$openUrlAsAttachment$10(this.f$0, this.f$1, this.f$2, view);
    }
}

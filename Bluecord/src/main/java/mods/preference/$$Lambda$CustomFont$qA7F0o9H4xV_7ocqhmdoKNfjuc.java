package mods.preference;

import android.content.DialogInterface;
import java.io.File;
/* compiled from: lambda */
/* renamed from: mods.preference.-$$Lambda$CustomFont$qA7F0o9H4xV_7ocqhmdoKNfj-uc  reason: invalid class name */
public final /* synthetic */ class $$Lambda$CustomFont$qA7F0o9H4xV_7ocqhmdoKNfjuc implements DialogInterface.OnClickListener {
    public final /* synthetic */ File[] f$0;

    public /* synthetic */ $$Lambda$CustomFont$qA7F0o9H4xV_7ocqhmdoKNfjuc(File[] fileArr) {
        this.f$0 = fileArr;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        CustomFont.lambda$loadFontFromFile$4(this.f$0, dialogInterface, i);
    }
}

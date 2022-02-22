package mods.activity;

import android.content.DialogInterface;
import android.widget.EditText;
/* compiled from: lambda */
/* renamed from: mods.activity.-$$Lambda$MediaTray$jeyrW8ev6-DJ_4Riv3Z_fAvysis  reason: invalid class name */
/* loaded from: classes4.dex */
public final /* synthetic */ class $$Lambda$MediaTray$jeyrW8ev6DJ_4Riv3Z_fAvysis implements DialogInterface.OnClickListener {
    public final /* synthetic */ MediaTray f$0;
    public final /* synthetic */ EditText f$1;
    public final /* synthetic */ EditText f$2;

    public /* synthetic */ $$Lambda$MediaTray$jeyrW8ev6DJ_4Riv3Z_fAvysis(MediaTray mediaTray, EditText editText, EditText editText2) {
        this.f$0 = mediaTray;
        this.f$1 = editText;
        this.f$2 = editText2;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f$0.lambda$addCommand$5$MediaTray(this.f$1, this.f$2, dialogInterface, i);
    }
}

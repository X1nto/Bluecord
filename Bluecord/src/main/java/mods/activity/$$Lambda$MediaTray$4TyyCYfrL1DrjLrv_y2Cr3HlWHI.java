package mods.activity;

import android.view.View;
import android.widget.AdapterView;
/* compiled from: lambda */
/* renamed from: mods.activity.-$$Lambda$MediaTray$4TyyCYfrL1DrjLrv_y2Cr3HlWHI  reason: invalid class name */
public final /* synthetic */ class $$Lambda$MediaTray$4TyyCYfrL1DrjLrv_y2Cr3HlWHI implements AdapterView.OnItemClickListener {
    public final /* synthetic */ MediaTray f$0;

    public /* synthetic */ $$Lambda$MediaTray$4TyyCYfrL1DrjLrv_y2Cr3HlWHI(MediaTray mediaTray) {
        this.f$0 = mediaTray;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.f$0.lambda$onTextChanged$0$MediaTray(adapterView, view, i, j);
    }
}

package mods.view;

import android.view.View;
import androidx.fragment.app.Fragment;
/* compiled from: lambda */
/* renamed from: mods.view.-$$Lambda$SheetConfig$FS5lf0dtSLKBnvrcjfdHAS0gzmc  reason: invalid class name */
public final /* synthetic */ class $$Lambda$SheetConfig$FS5lf0dtSLKBnvrcjfdHAS0gzmc implements View.OnClickListener {
    public final /* synthetic */ Fragment f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ $$Lambda$SheetConfig$FS5lf0dtSLKBnvrcjfdHAS0gzmc(Fragment fragment, String str, String str2) {
        this.f$0 = fragment;
        this.f$1 = str;
        this.f$2 = str2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SheetConfig.lambda$configureGuildSheet$9(this.f$0, this.f$1, this.f$2, view);
    }
}

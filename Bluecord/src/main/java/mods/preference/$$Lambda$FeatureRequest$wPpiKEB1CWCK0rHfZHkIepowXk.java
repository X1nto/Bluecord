package mods.preference;

import android.content.DialogInterface;
import android.preference.Preference;
import android.widget.CheckBox;
import android.widget.EditText;
/* compiled from: lambda */
/* renamed from: mods.preference.-$$Lambda$FeatureRequest$wPpiKEB1CWCK0rHfZHkIepowX-k  reason: invalid class name */
public final /* synthetic */ class $$Lambda$FeatureRequest$wPpiKEB1CWCK0rHfZHkIepowXk implements DialogInterface.OnClickListener {
    public final /* synthetic */ EditText f$0;
    public final /* synthetic */ CheckBox f$1;
    public final /* synthetic */ Preference f$2;

    public /* synthetic */ $$Lambda$FeatureRequest$wPpiKEB1CWCK0rHfZHkIepowXk(EditText editText, CheckBox checkBox, Preference preference) {
        this.f$0 = editText;
        this.f$1 = checkBox;
        this.f$2 = preference;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        FeatureRequest.lambda$new$0(this.f$0, this.f$1, this.f$2, dialogInterface, i);
    }
}

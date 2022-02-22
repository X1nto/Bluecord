package mods.utils.translate;

import android.app.Activity;
import android.content.DialogInterface;
import android.widget.EditText;
/* compiled from: lambda */
/* renamed from: mods.utils.translate.-$$Lambda$Translate$EtgKOZa_m0IG6UpYE7jBjBmYKEw  reason: invalid class name */
public final /* synthetic */ class $$Lambda$Translate$EtgKOZa_m0IG6UpYE7jBjBmYKEw implements DialogInterface.OnClickListener {
    public final /* synthetic */ EditText f$0;
    public final /* synthetic */ SimpleSpinner f$1;
    public final /* synthetic */ Activity f$2;

    public /* synthetic */ $$Lambda$Translate$EtgKOZa_m0IG6UpYE7jBjBmYKEw(EditText editText, SimpleSpinner simpleSpinner, Activity activity) {
        this.f$0 = editText;
        this.f$1 = simpleSpinner;
        this.f$2 = activity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        Translate.lambda$showTranslateDialog$0(this.f$0, this.f$1, this.f$2, dialogInterface, i);
    }
}

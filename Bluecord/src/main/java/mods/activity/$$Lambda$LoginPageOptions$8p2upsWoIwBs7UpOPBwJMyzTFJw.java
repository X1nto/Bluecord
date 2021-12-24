package mods.activity;

import android.content.DialogInterface;
import android.widget.EditText;
import com.discord.app.AppFragment;
/* compiled from: lambda */
/* renamed from: mods.activity.-$$Lambda$LoginPageOptions$8p2upsWoIwBs7UpOPBwJMyzTFJw  reason: invalid class name */
public final /* synthetic */ class $$Lambda$LoginPageOptions$8p2upsWoIwBs7UpOPBwJMyzTFJw implements DialogInterface.OnClickListener {
    public final /* synthetic */ EditText f$0;
    public final /* synthetic */ AppFragment f$1;

    public /* synthetic */ $$Lambda$LoginPageOptions$8p2upsWoIwBs7UpOPBwJMyzTFJw(EditText editText, AppFragment appFragment) {
        this.f$0 = editText;
        this.f$1 = appFragment;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        LoginPageOptions.lambda$init$1(this.f$0, this.f$1, dialogInterface, i);
    }
}

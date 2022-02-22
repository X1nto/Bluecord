package mods.view;

import android.view.View;
import com.discord.models.user.User;
/* compiled from: lambda */
/* renamed from: mods.view.-$$Lambda$SheetConfig$xtsjctXjymN-Obe_HUaOPeuw_z8  reason: invalid class name */
/* loaded from: classes4.dex */
public final /* synthetic */ class $$Lambda$SheetConfig$xtsjctXjymNObe_HUaOPeuw_z8 implements View.OnClickListener {
    public final /* synthetic */ User f$0;

    public /* synthetic */ $$Lambda$SheetConfig$xtsjctXjymNObe_HUaOPeuw_z8(User user) {
        this.f$0 = user;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SheetConfig.lambda$configureUserSheet$5(this.f$0, view);
    }
}

package mods.view;

import android.view.View;
import com.discord.models.user.User;
/* compiled from: lambda */
/* renamed from: mods.view.-$$Lambda$SheetConfig$u0B5FrvhvP45TVMdkuZQAVgFwRE  reason: invalid class name */
public final /* synthetic */ class $$Lambda$SheetConfig$u0B5FrvhvP45TVMdkuZQAVgFwRE implements View.OnClickListener {
    public final /* synthetic */ User f$0;

    public /* synthetic */ $$Lambda$SheetConfig$u0B5FrvhvP45TVMdkuZQAVgFwRE(User user) {
        this.f$0 = user;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SheetConfig.lambda$configureUserSheet$6(this.f$0, view);
    }
}

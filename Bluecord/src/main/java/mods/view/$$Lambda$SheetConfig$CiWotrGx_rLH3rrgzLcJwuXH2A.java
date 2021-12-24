package mods.view;

import android.view.View;
import com.discord.models.user.User;
/* compiled from: lambda */
/* renamed from: mods.view.-$$Lambda$SheetConfig$CiWotrGx_rLH3r-rgzLcJwuXH2A  reason: invalid class name */
public final /* synthetic */ class $$Lambda$SheetConfig$CiWotrGx_rLH3rrgzLcJwuXH2A implements View.OnClickListener {
    public final /* synthetic */ User f$0;

    public /* synthetic */ $$Lambda$SheetConfig$CiWotrGx_rLH3rrgzLcJwuXH2A(User user) {
        this.f$0 = user;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SheetConfig.lambda$configureUserSheet$4(this.f$0, view);
    }
}

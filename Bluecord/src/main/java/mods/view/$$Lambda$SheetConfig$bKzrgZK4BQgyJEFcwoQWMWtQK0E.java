package mods.view;

import android.view.View;
import com.discord.app.AppBottomSheet;
import com.discord.models.message.Message;
/* compiled from: lambda */
/* renamed from: mods.view.-$$Lambda$SheetConfig$bKzrgZK4BQgyJEFcwoQWMWtQK0E  reason: invalid class name */
public final /* synthetic */ class $$Lambda$SheetConfig$bKzrgZK4BQgyJEFcwoQWMWtQK0E implements View.OnClickListener {
    public final /* synthetic */ AppBottomSheet f$0;
    public final /* synthetic */ Message f$1;

    public /* synthetic */ $$Lambda$SheetConfig$bKzrgZK4BQgyJEFcwoQWMWtQK0E(AppBottomSheet appBottomSheet, Message message) {
        this.f$0 = appBottomSheet;
        this.f$1 = message;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SheetConfig.lambda$configureChatList$2(this.f$0, this.f$1, view);
    }
}

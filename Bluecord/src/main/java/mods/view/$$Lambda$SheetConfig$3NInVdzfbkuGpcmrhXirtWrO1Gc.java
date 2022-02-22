package mods.view;

import android.view.View;
import com.discord.app.AppBottomSheet;
import com.discord.models.message.Message;
/* compiled from: lambda */
/* renamed from: mods.view.-$$Lambda$SheetConfig$3NInVdzfbkuGpcmrhXirtWrO1Gc  reason: invalid class name */
public final /* synthetic */ class $$Lambda$SheetConfig$3NInVdzfbkuGpcmrhXirtWrO1Gc implements View.OnClickListener {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ Message f$1;
    public final /* synthetic */ AppBottomSheet f$2;

    public /* synthetic */ $$Lambda$SheetConfig$3NInVdzfbkuGpcmrhXirtWrO1Gc(String str, Message message, AppBottomSheet appBottomSheet) {
        this.f$0 = str;
        this.f$1 = message;
        this.f$2 = appBottomSheet;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SheetConfig.lambda$configureChatList$3(this.f$0, this.f$1, this.f$2, view);
    }
}

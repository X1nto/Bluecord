package mods.view;

import android.view.View;
import com.discord.app.AppBottomSheet;
import com.discord.models.message.Message;
/* compiled from: lambda */
/* renamed from: mods.view.-$$Lambda$SheetConfig$s3L7HTEsQ3iTnGUrtNpqz9n4Y7c  reason: invalid class name */
/* loaded from: classes4.dex */
public final /* synthetic */ class $$Lambda$SheetConfig$s3L7HTEsQ3iTnGUrtNpqz9n4Y7c implements View.OnClickListener {
    public final /* synthetic */ AppBottomSheet f$0;
    public final /* synthetic */ Message f$1;

    public /* synthetic */ $$Lambda$SheetConfig$s3L7HTEsQ3iTnGUrtNpqz9n4Y7c(AppBottomSheet appBottomSheet, Message message) {
        this.f$0 = appBottomSheet;
        this.f$1 = message;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SheetConfig.lambda$configureChatList$1(this.f$0, this.f$1, view);
    }
}

package mods.view;

import android.view.View;
import com.discord.api.channel.Channel;
import com.discord.app.AppBottomSheet;
import com.discord.models.message.Message;
/* compiled from: lambda */
/* renamed from: mods.view.-$$Lambda$SheetConfig$Y3uGp1UtFygk7uyhpNjlCCftKTg  reason: invalid class name */
public final /* synthetic */ class $$Lambda$SheetConfig$Y3uGp1UtFygk7uyhpNjlCCftKTg implements View.OnClickListener {
    public final /* synthetic */ Channel f$0;
    public final /* synthetic */ Message f$1;
    public final /* synthetic */ AppBottomSheet f$2;

    public /* synthetic */ $$Lambda$SheetConfig$Y3uGp1UtFygk7uyhpNjlCCftKTg(Channel channel, Message message, AppBottomSheet appBottomSheet) {
        this.f$0 = channel;
        this.f$1 = message;
        this.f$2 = appBottomSheet;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SheetConfig.lambda$configureChatList$0(this.f$0, this.f$1, this.f$2, view);
    }
}

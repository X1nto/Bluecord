package mods.view;

import android.view.View;
import com.discord.api.channel.Channel;
import com.discord.widgets.channels.list.WidgetChannelsList;
import com.discord.widgets.channels.list.WidgetChannelsListItemChannelActions;
/* compiled from: lambda */
/* renamed from: mods.view.-$$Lambda$ChatPins$z12mdO-nbq-l5sfzVrIEgjfA6uc  reason: invalid class name */
public final /* synthetic */ class $$Lambda$ChatPins$z12mdOnbql5sfzVrIEgjfA6uc implements View.OnClickListener {
    public final /* synthetic */ WidgetChannelsListItemChannelActions f$0;
    public final /* synthetic */ WidgetChannelsList f$1;
    public final /* synthetic */ Channel f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ $$Lambda$ChatPins$z12mdOnbql5sfzVrIEgjfA6uc(WidgetChannelsListItemChannelActions widgetChannelsListItemChannelActions, WidgetChannelsList widgetChannelsList, Channel channel, boolean z2) {
        this.f$0 = widgetChannelsListItemChannelActions;
        this.f$1 = widgetChannelsList;
        this.f$2 = channel;
        this.f$3 = z2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        ChatPins.lambda$configureUI$0(this.f$0, this.f$1, this.f$2, this.f$3, view);
    }
}

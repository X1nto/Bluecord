package mods.utils;

import com.discord.api.channel.Channel;
import com.discord.api.message.Message;
import com.discord.models.user.MeUser;
import com.discord.models.user.User;
import com.discord.stores.StoreLurking;
import com.discord.stores.StoreStream;
import com.discord.utilities.rest.RestAPI;
import com.discord.utilities.rx.ObservableExtensionsKt;
import com.discord.utilities.time.ClockFactory;

import java.util.concurrent.TimeUnit;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import mods.DiscordTools;
import rx.Observable;

public class StoreUtils {
    private static final long MAX_DIFFERENCE = TimeUnit.MINUTES.toMillis(2);

    public static Channel getChannelById(long j) {
        return StoreStream.getChannels().getChannel(j);
    }

    public static Long getCurrentChannelId() {
        if (StoreStream.getChat().getInteractionState() == null) return null;
        return StoreStream.getChat().getInteractionState().getChannelId();
    }

    public static Long getCurrentLastMessageId() {
        if (StoreStream.getChat().getInteractionState() == null) return null;
        return StoreStream.getChat().getInteractionState().getLastMessageId();
    }

    public static long getId(Object obj) {
        if (obj instanceof User) {
            return obj instanceof MeUser ? ((MeUser) obj).getId() : ((User) obj).getId();
        }
        if (obj instanceof com.discord.api.user.User) {
            return ((com.discord.api.user.User) obj).getId();
        }
        if (obj instanceof Channel) {
            return ((Channel) obj).h();
        }
        return -1;
    }

    public static MeUser getSelf() {
        return StoreStream.getUsers().getMe();
    }

    public static long getServerSyncedTime() {
        return ClockFactory.get().currentTimeMillis();
    }

    public static boolean isChannelDm(Channel channel) {
        return channel != null && channel.A() == 1;
    }

    public static boolean isEligibleForAntiSpam(Message message) {
        return !StringUtils.isEmpty(message.i()) && !isMessageEdited(message) && isChannelDm(getChannelById(message.g())) && Math.abs(getServerSyncedTime() - SnowflakeUtils.timestampForMessage(message)) < MAX_DIFFERENCE;
    }

    public static boolean isMessageEdited(Message message) {
        return (message == null || message.j() == null || message.j().g() == 0) ? false : true;
    }

    public static boolean isMessageEdited(com.discord.models.message.Message message) {
        return (message == null || message.getEditedTimestamp() == null || message.getEditedTimestamp().g() == 0) ? false : true;
    }

    static /* synthetic */ Unit lambda$subscribe$0(Object obj) {
        return null;
    }

    public static void leaveGroupDM(long j) {
        subscribe(RestAPI.Companion.getApi().deleteChannel(j));
    }

    public static void leaveGuild(long j) {
        StoreLurking.postLeaveGuild$default(StoreStream.getLurking(), j, null, null, 6, null);
    }

    private static void subscribe(Observable<?> observable) {
        try {
            ObservableExtensionsKt.appSubscribe$default(ObservableExtensionsKt.restSubscribeOn$default(observable, false, 1, null), DiscordTools.getContext(), "javaClass", (Function1) null, $$Lambda$StoreUtils$Xc0cEzITpeIEb0upIDItAkx2BlU.INSTANCE, (Function1) null, (Function0) null, (Function0) null, 116, (Object) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

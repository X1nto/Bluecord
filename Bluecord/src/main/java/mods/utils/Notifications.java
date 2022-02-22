package mods.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.discord.stores.StoreChat;
import mods.DiscordTools;
import mods.constants.Constants;

public class Notifications {
    private static final String CHANNEL_ID = "BLUE_NOTIFICATIONS";
    private static boolean isSetup;
    private static final Object setupLock = new Object();

    public static void cancel(StoreChat.InteractionState interactionState) {
        cancel(interactionState.getChannelId());
    }

    public static void cancel(Long l) {
        setup();
        NotificationManagerCompat.from(DiscordTools.getContext()).cancel(l.hashCode());
    }

    public static void notify(Long l, String str, String str2) {
        setup();
        Context context = DiscordTools.getContext();
        NotificationManagerCompat.from(context).notify(
            l.hashCode(),
            new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(Constants.bluecord_logo_big)
                .setContentTitle(str)
                .setContentText(str2)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(str2))
                .setPriority(0)
                .setAutoCancel(true)
                .build()
        );
    }

    private static void setup() {
        Object obj = setupLock;
        synchronized (obj) {
            if (isSetup) {
                return;
            }
        }
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Bluecord Notifications", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("All notifications added by the Bluecord mod");
            ((NotificationManager) DiscordTools.getContext().getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        }
        synchronized (obj) {
            isSetup = true;
        }
    }
}

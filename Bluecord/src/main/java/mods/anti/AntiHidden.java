package mods.anti;

import android.content.Context;
import android.util.Log;
import androidx.fragment.app.Fragment;
import com.discord.api.channel.Channel;
import mods.DiscordTools;
import mods.constants.PreferenceKeys;
import mods.preference.Prefs;
import mods.preference.QuickAccessPrefs;
import mods.utils.RefreshUtils;
import mods.utils.SnowflakeUtils;
import mods.utils.StringUtils;

public class AntiHidden {
    private static final String TAG = "AntiHidden";

    public static boolean handleHiddenChannel(Channel channel) {
        if (channel == null) return false;
        try {
            if (!QuickAccessPrefs.isShowHiddenChannelsEnabled()) return false;
            channel.setHidden(true);
            String name = channel.m();
            if (name != null && !name.endsWith(" (HIDDEN)")) channel.setName(name + " (HIDDEN)");
            return true;
        } catch (Throwable th) {
            Log.e(TAG, "handleHiddenChannel() failed", th);
            return false;
        }
    }

    public static boolean handleHiddenChannelClick(Context context, Channel channel) {
        if (channel != null) {
            try {
                Log.e(TAG, "Channel clicked: " + channel.m() + " (hidden=" + channel.isHidden() + ")");
            } catch (Throwable th) {
                Log.e(TAG, "handleHiddenChannelClick() failed", th);
                return false;
            }
        }
        if (channel == null || !channel.isHidden() || !Prefs.getBoolean(PreferenceKeys.REVEAL_HIDDEN_CHANNELS, false)) {
            return false;
        }
        String name = channel.m();
        String topic = channel.z();
        long lastMessage = channel.i();
        if (lastMessage != 0) lastMessage = SnowflakeUtils.toTimestamp(lastMessage);
        String sb = "Info for hidden channel #" +
            name.replace(" (HIDDEN)", "") +
            ":\n\nTopic: " +
            (StringUtils.isEmpty(topic) ? "none" : topic) +
            "\n\nLast Message Sent: " +
            (lastMessage == 0 ? "Never" : DiscordTools.formatDate(lastMessage)) +
            "\n\nPlease note that reading the actual messages in the channel is not, and will not be possible.";
        DiscordTools.basicAlert(context, "Hidden Channel", sb);
        return true;
    }

    public static boolean handleHiddenChannelClick(Fragment fragment, Channel channel) {
        return handleHiddenChannelClick(fragment.getContext(), channel);
    }

    public static void handleVisibleChannel(Channel channel) {
        if (channel != null) {
            try {
                if (channel.isHidden()) {
                    channel.setHidden(false);
                    String name = channel.m();
                    if (name != null && name.endsWith(" (HIDDEN)")) {
                        channel.setName(name.substring(0, name.length() - 9));
                        RefreshUtils.refreshView();
                    }
                }
            } catch (Throwable th) {
                Log.e(TAG, "handleVisibleChannel() failed", th);
            }
        }
    }
}

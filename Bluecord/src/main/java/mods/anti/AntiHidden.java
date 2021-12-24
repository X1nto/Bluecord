package mods.anti;

import android.content.Context;
import androidx.fragment.app.Fragment;
import com.discord.api.channel.Channel;
import mods.DiscordTools;
import mods.constants.PreferenceKeys;
import mods.preference.Prefs;
import mods.utils.RefreshUtils;
import mods.utils.SnowflakeUtils;
import mods.utils.StringUtils;
public class AntiHidden {
    public static boolean handleHiddenChannel(Channel channel) {
        if (!Prefs.getBoolean(PreferenceKeys.REVEAL_HIDDEN_CHANNELS, false)) {
            return false;
        }
        channel.setHidden(true);
        String m = channel.m();
        if (m != null && !m.endsWith(" (HIDDEN)")) {
            channel.setName(m + " (HIDDEN)");
        }
        return true;
    }

    public static boolean handleHiddenChannelClick(Fragment fragment, Channel channel) {
        if (!channel.isHidden() || !Prefs.getBoolean(PreferenceKeys.REVEAL_HIDDEN_CHANNELS, false)) {
            return false;
        }
        String m = channel.m();
        String z2 = channel.z();
        long i = channel.i();
        if (i != 0) {
            i = SnowflakeUtils.toTimestamp(i);
        }
        Context context = fragment.getContext();
        StringBuilder sb = new StringBuilder();
        sb.append("Info for hidden channel #");
        sb.append(m.replace(" (HIDDEN)", ""));
        sb.append(":\n\nTopic: ");
        sb.append(StringUtils.isEmpty(z2) ? "none" : z2);
        sb.append("\n\nLast Message Sent: ");
        sb.append(i == 0 ? "Never" : DiscordTools.formatDate(i));
        sb.append("\n\nPlease note that reading the actual messages in the channel is not, and will not be possible.");
        DiscordTools.basicAlert(context, "Hidden Channel", sb.toString());
        return true;
    }

    public static void handleUnhiddenChannel(Channel channel) {
        if (channel.isHidden()) {
            channel.setHidden(false);
            String m = channel.m();
            if (m != null && m.endsWith(" (HIDDEN)")) {
                channel.setName(m.substring(0, m.length() - 9));
                RefreshUtils.refreshView();
            }
        }
    }
}

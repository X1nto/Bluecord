package mods.utils;

import android.text.TextUtils;
import com.discord.api.channel.Channel;
import com.discord.api.user.User;
import com.discord.models.domain.ModelInvite;
import com.discord.models.message.Message;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import mods.DiscordTools;

public class StringUtils {
    public static String convertToTimeBehind(Date date) {
        Date date2 = new Date(StoreUtils.getServerSyncedTime());
        if (date.getTime() <= 0 || date2.getTime() <= 0) {
            return "Unknown";
        }
        long seconds = TimeUnit.MILLISECONDS.toSeconds(date2.getTime() - date.getTime());
        long minutes = TimeUnit.MILLISECONDS.toMinutes(date2.getTime() - date.getTime());
        long hours = TimeUnit.MILLISECONDS.toHours(date2.getTime() - date.getTime());
        long days = TimeUnit.MILLISECONDS.toDays(date2.getTime() - date.getTime());
        String str = "";
        if (days > 0) {
            long j = hours % 24;
            StringBuilder sb = new StringBuilder();
            sb.append(days);
            sb.append(" ");
            sb.append(plural("day", days));
            if (j != 0) {
                str = " and " + j + " " + plural("hour", j);
            }
            sb.append(str);
            sb.append(" ago");
            return sb.toString();
        } else if (hours > 0) {
            long j2 = minutes % 60;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(hours);
            sb2.append(" ");
            sb2.append(plural("hour", hours));
            if (j2 != 0) {
                str = " and " + j2 + " " + plural("minute", j2);
            }
            sb2.append(str);
            sb2.append(" ago");
            return sb2.toString();
        } else if (minutes == 0) {
            return seconds + " " + plural("second", seconds) + " ago";
        } else {
            return minutes + " " + plural("minute", minutes) + " ago";
        }
    }

    public static String fixAccountName(String str) {
        if (str == null || !str.contains("#")) {
            return str;
        }
        try {
            int lastIndexOf = str.lastIndexOf("#");
            String substring = str.substring(0, lastIndexOf);
            int parseInt = Integer.parseInt(str.substring(lastIndexOf + 1));
            return substring + "#" + formatDiscriminator(parseInt);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    private static String formatDiscriminator(int i) {
        return String.format(Locale.US, "%04d", Integer.valueOf(i));
    }

    public static String getUsernameWithDiscriminator(User user) {
        return user.getUsername() + "#" + user.f();
    }

    public static String getUsernameWithDiscriminator(com.discord.models.user.User user) {
        return user.getUsername() + "#" + formatDiscriminator(user.getDiscriminator());
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static String mock(String str) {
        String lowerCase = str.trim().toLowerCase();
        StringBuilder sb = new StringBuilder();
        boolean z2 = false;
        for (int i = 0; i < lowerCase.length(); i++) {
            char charAt = lowerCase.charAt(i);
            if (Character.isLetter(charAt)) {
                if (z2) {
                    charAt = Character.toUpperCase(charAt);
                    z2 = false;
                } else {
                    z2 = true;
                }
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    public static String plural(String str, long j) {
        if (j == 1) {
            return str;
        }
        return str + "s";
    }

    public static String quoteMessage(Channel channel, Message message) {
        String trim = message.getContent().trim();
        if (trim.isEmpty()) {
            return "";
        }
        if (trim.length() > 1800) {
            trim = trim.substring(0, ModelInvite.Settings.HALF_HOUR);
        }
        String[] split = trim.split("\n");
        for (int i = 0; i < split.length; i++) {
            String str = split[i];
            if (!str.trim().startsWith("> ")) {
                str = "> " + str;
            }
            split[i] = str;
        }
        String join = TextUtils.join("\n", split);
        if (!StoreUtils.isChannelDm(channel)) {
            join = "```" + getUsernameWithDiscriminator(message.getAuthor()).replace("```", "`​``") + " | " + DiscordTools.formatDate(SnowflakeUtils.toTimestamp(message.getId())) + "```\n" + join;
        }
        return join + "\n\n";
    }
}

package mods.utils;

import com.discord.api.message.Message;
import com.discord.models.user.User;

public class SnowflakeUtils {
    private static final long EPOCH = 1420070400000L;

    public static long getAccountCreationTime(User user) {
        return toTimestamp(user.getId());
    }

    public static long timestampForMessage(Message message) {
        return toTimestamp(message.o());
    }

    public static long toSnowflakeId(long j) {
        return (j - 1420070400000L) << 22;
    }

    public static long toTimestamp(long j) {
        return (j >> 22) + 1420070400000L;
    }
}

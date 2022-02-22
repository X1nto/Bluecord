package com.discord.utilities.presence;

import com.discord.api.presence.ClientStatuses;

public final class PresenceUtils {
    public static final PresenceUtils INSTANCE = new PresenceUtils();

    public final boolean isWeb(ClientStatuses clientStatuses) { return isWeb(clientStatuses); }
}

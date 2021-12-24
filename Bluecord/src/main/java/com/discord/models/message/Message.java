package com.discord.models.message;

import com.discord.api.message.attachment.MessageAttachment;
import com.discord.api.user.User;
import com.discord.api.utcdatetime.UtcDateTime;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public final class Message {
    public boolean deleted;

    public Message(com.discord.api.message.Message message) {}

    public final List<MessageAttachment> getAttachments() {
        return Collections.emptyList();
    }

    public final User getAuthor() {
        return null;
    }

    public final long getChannelId() {
        return 0;
    }

    public final String getContent() {
        return "";
    }

    public final UtcDateTime getEditedTimestamp() {
        return null;
    }

    public final long getId() {
        return 0;
    }

    public final boolean hasAttachments() {
        return false;
    }

    public final boolean isLocalApplicationCommand() {
        return false;
    }
}

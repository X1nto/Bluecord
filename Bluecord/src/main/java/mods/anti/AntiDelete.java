package mods.anti;

import com.discord.api.message.attachment.MessageAttachment;
import com.discord.models.message.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mods.constants.PreferenceKeys;
import mods.preference.Prefs;
import mods.utils.FileLogger;
import mods.utils.RefreshUtils;
import mods.utils.StringUtils;
public class AntiDelete {
    public static List<Message> parseDeletedMessages(Map<Long, Message> map, List<Long> list, List<Message> list2) {
        String string = Prefs.getString(PreferenceKeys.ANTI_DELETE_MODE, "Off");
        if (string.startsWith("Block Delete")) {
            boolean equals = string.equals("Block Delete + Log");
            try {
                for (Long id : list) {
                    if (id == null) return list2;
                    Message message = map.get(id);
                    if (message != null) {
                        message.deleted = true;
                        if (list2 == null) list2 = new ArrayList<>(5);
                        RefreshUtils.invalidateMessage(id);
                        list2.add(message);
                        if (equals) {
                            if (!StringUtils.isEmpty(message.getContent())) {
                                FileLogger.writeWithProfileInfo(message, "messages", message.getContent(), "Deleted Messages", "deleted");
                            } else if (message.hasAttachments()) {
                                for (MessageAttachment messageAttachment : message.getAttachments()) {
                                    String sb = messageAttachment.filename +
                                        (messageAttachment.proxyUrl != null ? " (" + messageAttachment.proxyUrl + ")" : "");
                                    FileLogger.writeWithProfileInfo(message, "attachments", sb, "Deleted Messages", "deleted");
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list2;
    }
}

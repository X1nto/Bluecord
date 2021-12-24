package mods.anti;

import android.content.Context;
import android.util.Log;
import c.a.t.b.a.a;
import com.discord.models.message.Message;
import com.discord.utilities.textprocessing.node.EditedMessageNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import mods.constants.PreferenceKeys;
import mods.preference.Prefs;
import mods.utils.FileLogger;
import mods.utils.LRUCache;
import mods.utils.StoreUtils;
import mods.view.PrependEditNode;
public class AntiEdit {
    private static final LRUCache<Long, List<String>> editedMessages = new LRUCache<>(250);

    public static void appendEdits(Context context, Message message, List<Object> list) {
        List<String> list2;
        if (StoreUtils.isMessageEdited(message) && (list2 = editedMessages.get(message.getId())) != null) {
            ArrayList<String> arrayList = new ArrayList(list2);
            Collections.reverse(arrayList);
            for (String str : arrayList) {
                list.add(0, new PrependEditNode(context, str));
                list.add(1, new EditedMessageNode(context));
                list.add(2, new a("\n"));
            }
        }
    }

    public static void parseEditedMessage(Map<Long, Message> map, com.discord.api.message.Message message) {
        if (map != null && StoreUtils.isMessageEdited(message)) {
            String string = Prefs.getString(PreferenceKeys.ANTI_EDIT_MODE, "Off");
            if (!"Off".equalsIgnoreCase(string)) {
                Message message2 = map.get(Long.valueOf(message.o()));
                if (message2 == null) {
                    Log.e("AntiEdit", "edited message not found in cache:\n" + message.toString());
                    return;
                }
                String content = message2.getContent();
                String i = message.i();
                Log.e("AntiEdit", "edited message found:\n" + content);
                synchronized (AntiEdit.class) {
                    LRUCache<Long, List<String>> lRUCache = editedMessages;
                    List<String> list = lRUCache.get(Long.valueOf(message.o()));
                    if (list == null) {
                        ArrayList arrayList = new ArrayList(5);
                        arrayList.add(content);
                        lRUCache.put(Long.valueOf(message.o()), arrayList);
                    } else {
                        if (list.size() >= 5) {
                            list.remove(0);
                        }
                        list.add(content);
                    }
                }
                if ("Show Edit + Log".equalsIgnoreCase(string)) {
                    Message message3 = new Message(message);
                    FileLogger.writeWithProfileInfo(message3, "messages", "'" + content + "' was changed to '" + i + "'", "Edited Messages", "edited");
                }
            }
        }
    }
}

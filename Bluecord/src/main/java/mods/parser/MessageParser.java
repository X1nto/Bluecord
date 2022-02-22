package mods.parser;

import android.util.Log;
import com.discord.api.message.Message;
import com.discord.models.user.MeUser;
import mods.constants.PreferenceKeys;
import mods.preference.Prefs;
import mods.utils.StoreUtils;

public class MessageParser {
    private static final String TAG = "MessageParser";

    private static void antiGroupAdd(Message message) {
        MeUser self;
        Integer E = message.E();
        if (E != null && E.intValue() == 1 && Prefs.getBoolean(PreferenceKeys.DO_NOT_ADD, false) && (self = StoreUtils.getSelf()) != null && message.e().getId() != self.getId()) {
            Log.e("Bluecord Dnd", "attempting to leave server " + message.g() + "\n\n" + message);
            StoreUtils.leaveGroupDM(message.g());
        }
    }

    public static void handleIncoming(Object obj) {
        if (obj instanceof Message) {
            antiGroupAdd((Message) obj);
        }
    }
}

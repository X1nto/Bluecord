package mods.utils;

import android.os.Environment;
import com.discord.models.message.Message;
import com.discord.widgets.chat.input.autocomplete.AutocompleteViewModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import mods.DiscordTools;
public class FileLogger {
    private static void writeInformation(String str, String str2, String str3) {
        try {
            File file = new File(Environment.getExternalStorageDirectory() + "/Bluecord/" + str3 + AutocompleteViewModel.COMMAND_DISCOVER_TOKEN);
            file.mkdirs();
            File file2 = new File(file.getAbsolutePath() + AutocompleteViewModel.COMMAND_DISCOVER_TOKEN + str + ".txt");
            if (!file2.exists()) {
                file2.createNewFile();
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file2, true), StandardCharsets.UTF_8);
            try {
                outputStreamWriter.append((CharSequence) str2);
                outputStreamWriter.append((CharSequence) "\r\n");
                outputStreamWriter.flush();
                outputStreamWriter.close();
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeWithProfileInfo(Message message, String str, String str2, String str3, String str4) {
        writeInformation(str, "[" + DiscordTools.formatDate(StoreUtils.getServerSyncedTime()) + "]: A " + str.substring(0, str.length() - 1) + " from " + message.getAuthor().getUsername() + "#" + message.getAuthor().f() + " was " + str4 + " (" + str2 + ")", str3);
    }
}

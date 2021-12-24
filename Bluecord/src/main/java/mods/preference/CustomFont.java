package mods.preference;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.preference.Preference;
import android.util.AttributeSet;
import com.discord.models.domain.ModelAuditLogEntry;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import mods.DiscordTools;
import mods.activity.BlueSettingsActivity;
import mods.constants.PreferenceKeys;
import mods.utils.PermissionUtils;
import mods.utils.ToastUtil;
public class CustomFont extends Preference {
    public CustomFont(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnPreferenceClickListener($$Lambda$CustomFont$gCsMATLZcxNQDRQY9M3wWWEvRU.INSTANCE);
    }

    private static String fix(String str) {
        return (str.contains("Blacksword") || str.contains("Lemon") || str.contains("velvet") || str.contains("Waltograph")) ? str.replace(".ttf", ".otf") : str;
    }

    static /* synthetic */ int lambda$loadFontFromFile$3(File file, File file2) {
        if (file == null || file2 == null) {
            return 0;
        }
        return file.getName().compareTo(file2.getName());
    }

    static /* synthetic */ void lambda$loadFontFromFile$4(File[] fileArr, DialogInterface dialogInterface, int i) {
        Prefs.setString(PreferenceKeys.CUSTOM_FONT_TYPE, "Custom");
        Prefs.setString(PreferenceKeys.CUSTOM_FONT_PATH, fileArr[i].getAbsolutePath());
        ToastUtil.customToast(BlueSettingsActivity.getPreferenceActivity(), "Font changed successfully");
    }

    static /* synthetic */ void lambda$new$0(Context context, DialogInterface dialogInterface, int i) {
        if (i == 0) {
            preInstall(context);
        } else if (i == 1) {
            loadFontFromFile(context);
        } else if (i == 2) {
            Prefs.setString(PreferenceKeys.CUSTOM_FONT_TYPE, "Default");
            Prefs.setString(PreferenceKeys.CUSTOM_FONT_PATH, null);
            ToastUtil.customToast(BlueSettingsActivity.getPreferenceActivity(), "Font reset to default. Restart for change to take effect");
        }
    }

    static /* synthetic */ boolean lambda$new$1(Preference preference) {
        Context context = preference.getContext();
        DiscordTools.newBuilder(context).setTitle("Pick an option").setItems(new String[]{"Pre-installed fonts", "Load font from file", "Reset To Default"}, new $$Lambda$CustomFont$7kmBjylNrU6oHSdCruxkLTT2gHc(context)).setPositiveButton("Exit", (DialogInterface.OnClickListener) null).show();
        return true;
    }

    static /* synthetic */ void lambda$preInstall$2(String[] strArr, DialogInterface dialogInterface, int i) {
        if (i == 0) {
            Prefs.setString(PreferenceKeys.CUSTOM_FONT_TYPE, "Default");
            Prefs.setString(PreferenceKeys.CUSTOM_FONT_PATH, null);
            return;
        }
        Prefs.setString(PreferenceKeys.CUSTOM_FONT_TYPE, fix("fonts/" + strArr[i] + ".ttf"));
    }

    private static void loadFontFromFile(Context context) {
        if (PermissionUtils.hasStoragePermission()) {
            try {
                File file = new File(Environment.getExternalStorageDirectory() + "/Bluecord/Fonts/");
                file.mkdirs();
                File[] listFiles = file.listFiles();
                if (listFiles == null) {
                    ToastUtil.customToast(BlueSettingsActivity.getPreferenceActivity(), "Something went wrong.");
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (File file2 : listFiles) {
                    if (file2 != null) {
                        if (file2.isFile()) {
                            if (file2.getPath().contains(".ttf") || file2.getPath().contains(".otf")) {
                                arrayList.add(file2);
                            }
                        }
                    }
                }
                Collections.sort(arrayList, $$Lambda$CustomFont$lURVnxDzXUKcSBJUFzNkB3b2U.INSTANCE);
                if (arrayList.isEmpty()) {
                    DiscordTools.basicAlert(context, "No Fonts Found", "No fonts could be located. To add custom font files, please place them in the following folder:\n\nInternal Storage/Bluecord/Fonts/\n\nNOTE: the file must have a .ttf or .otf extension for them to show up in this list!");
                    return;
                }
                File[] fileArr = (File[]) arrayList.toArray(new File[0]);
                String[] strArr = new String[listFiles.length];
                for (int i = 0; i < listFiles.length; i++) {
                    strArr[i] = fileArr[i].getName();
                }
                DiscordTools.newBuilder(context).setTitle("Pick a custom font").setItems(strArr, new $$Lambda$CustomFont$qA7F0o9H4xV_7ocqhmdoKNfjuc(fileArr)).setPositiveButton("Exit", (DialogInterface.OnClickListener) null).show();
            } catch (Exception e) {
                e.printStackTrace();
                ToastUtil.customToast(BlueSettingsActivity.getPreferenceActivity(), "Something went wrong.");
            }
        }
    }

    private static void preInstall(Context context) {
        String[] strArr = {"Default", "Angry", "Autumn", "Blacksword", "Cartoon", "Caviar", "Celeste", "Coffee", "Comic Sans", "Courier", "28 Days Later", "Google", "Gothic", "Impact", "Instagram", "Lemon / Milk", "Luna", "Misfit", "Moon", "Nickelodeon", "Olde English", "Orange", "Roboto (Normal)", "Roboto (Black)", "Roboto (Black + Italic)", "Roboto (Bold)", "Roboto (Bold + Condensed)", "Roboto (Bold + Condensed + Italic)", "Roboto (Bold + Italic)", "Roboto (Condensed)", "Roboto (Condensed + Italic)", "Roboto (Italic)", "Roboto (Light)", "Roboto (Light + Italic)", "Roboto (Medium)", "Roboto (Medium + Italic)", "Roboto (Thin)", "Roboto (Thin + Italic)", "Trajan", "Ubuntu", "VCR", "Velvet", "Waltograph"};
        String[] strArr2 = {"Default", "Angry", "Autumn", "Blacksword", "Cartoon", "Caviar", "Celeste", "Coffee", "Comic", "cour", "Days", "Google", "Gothic", "Impact", "Instagram", "Lemon", "Luna", "Misfit2", "Moon", ModelAuditLogEntry.CHANGE_KEY_NICK, "olde", "Orange", "Roboto-Regular", "Roboto-Black", "Roboto-BlackItalic", "Roboto-Bold", "Roboto-BoldCondensed", "Roboto-BoldCondensedItalic", "Roboto-BoldItalic", "Roboto-Condensed", "Roboto-CondensedItalic", "Roboto-Italic", "Roboto-Light", "Roboto-LightItalic", "Roboto-Medium", "Roboto-MediumItalic", "Roboto-Thin", "Roboto-ThinItalic", "Trajan", "Ubuntu", "VCR", "velvet", "Waltograph"};
        String string = Prefs.getString(PreferenceKeys.CUSTOM_FONT_TYPE, "Default");
        int indexOf = Arrays.asList(strArr2).indexOf(string.startsWith("fonts/") ? string.substring(6, string.length() - 4) : string);
        if (indexOf < 0) {
            indexOf = 0;
        }
        DiscordTools.newBuilder(context).setTitle("Choose a font").setSingleChoiceItems(strArr, indexOf, new $$Lambda$CustomFont$U0lfU89CmyyimU2Ey_4A76_6F0(strArr2)).setNeutralButton("Close", (DialogInterface.OnClickListener) null).show();
    }
}

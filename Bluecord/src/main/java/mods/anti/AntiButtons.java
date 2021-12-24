package mods.anti;

import android.view.View;
import android.view.ViewGroup;
import com.discord.databinding.WidgetUserSheetBinding;
import mods.constants.PreferenceKeys;
import mods.preference.Prefs;
public class AntiButtons {
    public static void hideCallButton(View view) {
        if (Prefs.getBoolean(PreferenceKeys.HIDE_CALL_BUTTONS, false)) {
            view.setVisibility(View.GONE);
        }
    }

    public static void hideCallButton(WidgetUserSheetBinding widgetUserSheetBinding) {
        if (Prefs.getBoolean(PreferenceKeys.HIDE_CALL_BUTTONS, false)) {
            widgetUserSheetBinding.i.setVisibility(View.GONE);
            widgetUserSheetBinding.L.setVisibility(View.GONE);
        }
    }

    public static boolean hideCallButton(boolean z2) {
        if (z2) {
            return !Prefs.getBoolean(PreferenceKeys.HIDE_CALL_BUTTONS, false);
        }
        return false;
    }

    public static void hideInviteButton(View view) {
        if (Prefs.getBoolean(PreferenceKeys.HIDE_INVITE_BUTTON, false)) {
            view.setPadding(0, 0, 0, 0);
            view.setLayoutParams(new ViewGroup.LayoutParams(0, 12));
            view.setVisibility(View.GONE);
        }
    }
}

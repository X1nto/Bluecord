package mods.view;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.TextView;
import com.discord.models.message.Message;
import com.discord.models.user.MeUser;
import com.discord.utilities.guilds.GuildConstantsKt;
import mods.ThemingTools;
import mods.constants.PreferenceKeys;
import mods.preference.Prefs;
import mods.utils.StoreUtils;
public class Colors {
    private static int animateSpeed;
    private static boolean animateTextMessages;
    private static boolean animateTypingBox;
    private static final int[] animatedColors = {Color.parseColor("#e5e5ea"), Color.parseColor("#fea7b9"), Color.parseColor("#cd9aec"), Color.parseColor("#b5b8f8"), Color.parseColor("#87beff"), Color.parseColor("#97f2c3"), Color.parseColor("#bbe061"), Color.parseColor("#f9e560"), Color.parseColor("#ffb43f"), Color.parseColor("#cfa075"), Color.parseColor("#e5e5ea")};
    private static boolean customAuthorTextColors;
    private static int deletedMessageColor;
    private static int incomingAuthorTextColor;
    private static int incomingTextColor;
    private static int outgoingAuthorTextColor;
    private static int outgoingTextColor;
    private static String textColorMode;

    public static void animateEditText(EditText editText) {
        if (animateTypingBox) {
            ValueAnimator valueAnimator = new ValueAnimator();
            valueAnimator.setIntValues(animatedColors);
            valueAnimator.setEvaluator(new ArgbEvaluator());
            valueAnimator.addUpdateListener(new $$Lambda$Colors$WzQfqEsSi62CDePbkRZBbrsgsDY(editText));
            valueAnimator.setRepeatMode(2);
            valueAnimator.setRepeatCount(-1);
            valueAnimator.setDuration((long) animateSpeed);
            valueAnimator.start();
        }
    }

    public static void animateTextView(TextView textView) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(animatedColors);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.addUpdateListener(new $$Lambda$Colors$uec_VH7JPOn01z1R1YEiFjhv49Y(textView));
        valueAnimator.setRepeatMode(2);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setDuration((long) animateSpeed);
        valueAnimator.start();
    }

    public static int getAuthorTextColor(Message message, int i) {
        return customAuthorTextColors ? isMessageOutgoing(message) ? outgoingAuthorTextColor : incomingAuthorTextColor : i;
    }

    public static int getBarBackground() {
        return Color.parseColor(ThemingTools.isDarkModeOn() ? "#ff28292c" : "#ff999999");
    }

    static int getDefaultEditedColor() {
        return Color.parseColor(ThemingTools.isDarkModeOn() ? "#ff72767d" : "#ff747f8d");
    }

    public static int getDeletedMessageColor() {
        return deletedMessageColor;
    }

    public static int getThemeBackground() {
        return Color.parseColor(ThemingTools.isDarkModeOn() ? "#ff2f3136" : "#ffffffff");
    }

    public static void init() {
        textColorMode = Prefs.getString(PreferenceKeys.COLOR_MODE, "Default");
        incomingTextColor = Prefs.getInt(PreferenceKeys.COLOR_TEXT_INCOMING, -1);
        outgoingTextColor = Prefs.getInt(PreferenceKeys.COLOR_TEXT_OUTGOING, -1);
        deletedMessageColor = Prefs.getInt(PreferenceKeys.COLOR_DELETED_MESSAGE, Color.parseColor("#e00404"));
        customAuthorTextColors = Prefs.getBoolean(PreferenceKeys.COLOR_AUTHORS_ENABLE, false);
        incomingAuthorTextColor = Prefs.getInt(PreferenceKeys.COLOR_AUTHORS_INCOMING, -1);
        outgoingAuthorTextColor = Prefs.getInt(PreferenceKeys.COLOR_AUTHORS_OUTGOING, -1);
        animateTypingBox = Prefs.getBoolean(PreferenceKeys.COLOR_ANIMATE_TYPING, false);
        animateTextMessages = Prefs.getBoolean(PreferenceKeys.COLOR_ANIMATE_MESSAGE, false);
        int i = Prefs.getInt(PreferenceKeys.RAINBOW_CYCLE_SPEED, 3);
        if (i == 0) {
            animateSpeed = 10000;
        } else if (i == 1) {
            animateSpeed = 7500;
        } else if (i == 2) {
            animateSpeed = 5000;
        } else if (i == 3) {
            animateSpeed = GuildConstantsKt.MAX_GUILD_MEMBERS_NOTIFY_ALL_MESSAGES;
        } else if (i != 4) {
            animateSpeed = 5000;
        } else {
            animateSpeed = 1000;
        }
    }

    private static boolean isMessageOutgoing(Message message) {
        MeUser self = StoreUtils.getSelf();
        return self != null && self.getId() == message.getAuthor().getId();
    }

    static /* synthetic */ void lambda$animateEditText$0(EditText editText, ValueAnimator valueAnimator) {
        editText.setTextColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    static /* synthetic */ void lambda$animateTextView$1(TextView textView, ValueAnimator valueAnimator) {
        textView.setTextColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    public static void setMessageTextColor(TextView textView, Message message, int i) {
        boolean isMessageOutgoing = isMessageOutgoing(message);
        if (animateTextMessages) {
            animateTextView(textView);
        } else if ("Custom Colors".equals(textColorMode)) {
            textView.setTextColor(isMessageOutgoing ? outgoingTextColor : incomingTextColor);
        } else if ("Match Role Color".equals(textColorMode)) {
            textView.setTextColor(i);
        }
    }
}

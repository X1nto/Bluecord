package mods;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.graphics.*;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import b.f.g.f.a;
import b.f.g.f.c;
import com.discord.api.channel.Channel;
import com.discord.api.message.reaction.MessageReactionUpdate;
import com.discord.models.domain.ModelUserSettings;
import com.discord.models.domain.emoji.Emoji;
import com.discord.models.message.Message;
import com.discord.models.user.MeUser;
import com.discord.models.user.User;
import com.discord.stores.StoreStream;
import com.discord.utilities.icon.IconUtils;
import com.discord.utilities.textprocessing.node.EmojiNode;
import com.discord.widgets.chat.list.actions.WidgetChatListActions;
import com.discord.widgets.emoji.EmojiSheetViewModel;

import java.util.*;

import mods.activity.CrashHandler;
import mods.activity.Updater;
import mods.constants.*;
import mods.preference.*;
import mods.utils.StoreUtils;
import mods.utils.StringUtils;
import mods.utils.ToastUtil;
import mods.view.Colors;
import mods.view.DoubleClick;
import mods.view.DoubleClickListener;

public class ThemingTools {
    private static Typeface typeface;

    /* renamed from: mods.ThemingTools$1  reason: invalid class name */
    static class AnonymousClass1 implements DoubleClickListener {
        final Message message;

        AnonymousClass1(Message message) {
            this.message = message;
        }

        @Override // mods.view.DoubleClickListener
        public void onDoubleClick(View view) {
            MeUser self = StoreUtils.getSelf();
            if (self == null || self.getId() != message.getAuthor().getId()) {
                Channel channelById = StoreUtils.getChannelById(this.message.getChannelId());
                if (channelById != null) {
                    WidgetChatListActions.access$replyMessage(new WidgetChatListActions(), this.message, channelById);
                } else {
                    Log.e("Bluecord", "could not find com.discord.api.Channel from message");
                }
            } else {
                WidgetChatListActions.access$editMessage(new WidgetChatListActions(), this.message);
            }
        }

        @Override // mods.view.DoubleClickListener
        public void onSingleClick(View view) {}
    }

    public static int addRoundedCorners() {
        return Prefs.getBoolean(PreferenceKeys.SQUARE_PICTURES, false) ? 3 : 0;
    }

    public static String addTag(String str, User user) {
        if (!Prefs.getBoolean(PreferenceKeys.SHOW_TAG, false)) {
            return str;
        }
        return str + " (" + StringUtils.getUsernameWithDiscriminator(user) + ")";
    }

    public static boolean blockReactionRemove(MessageReactionUpdate messageReactionUpdate) {
        return (messageReactionUpdate == null || StoreUtils.getSelf() == null || messageReactionUpdate.d() == StoreUtils.getSelf().getId() || !Prefs.getBoolean(PreferenceKeys.HIDE_REACTION_DELETE, false)) ? false : true;
    }

    public static String changeIconSize(String str) {
        if (!Prefs.getBoolean(PreferenceKeys.RESIZE_ICONS, false)) {
            return str;
        }
        return str + "?size=512";
    }

    public static String clientSpoof() {
        return Prefs.getBoolean(PreferenceKeys.PC_CLIENT_SPOOF, false) ? "Discord Client" : "Discord Android";
    }

    public static void copyEmoteUrl(View view, Object obj) {
        @SuppressLint("ResourceType") View findViewById = view.findViewById(16908321);
        findViewById.setVisibility(View.VISIBLE);
        findViewById.setOnClickListener(new $$Lambda$ThemingTools$oLp1qFJBHiTc59XTHm_xF8Mi490(obj));
    }

    public static int dipToPx(float f) {
        return (int) ((DiscordTools.getContext().getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public static boolean disableReplyMentions() {
        return Prefs.getBoolean(PreferenceKeys.DISABLE_REPLY_MENTIONS, false);
    }

    public static void doubleClickReply(View view, Message message, View.OnClickListener onClickListener, boolean z2) {
        if (z2) {
            view.setOnClickListener(onClickListener);
        } else {
            view.setOnClickListener(new DoubleClick(new AnonymousClass1(message)));
        }
    }

    public static String exactStamps(long j) {
        if (!Prefs.getBoolean(PreferenceKeys.EXACT_TIMESTAMPS, false)) {
            return null;
        }
        return DiscordTools.formatDate(j);
    }

    public static int fixIconRadius(int i) {
        if (Prefs.getBoolean(PreferenceKeys.SQUARE_PICTURES, false)) {
            return 0;
        }
        return i;
    }

    public static String getCredits() {
        try {
            PackageInfo packageInfo = DiscordTools.getContext().getPackageManager().getPackageInfo(DiscordTools.getContext().getPackageName(), 0);
            return String.format(
                "Bluecord v%s\nBased on Discord %s (%s)\n~Made with love by Blue~",
                URLConstants.getVersionString(),
                packageInfo.versionName,
                packageInfo.versionCode
            );
        } catch (Exception e) {
            Log.e("ThemingTools", "cannot locate own package?", e);
            return "Bluecord v" + URLConstants.getVersionString() + "\n~Made with love by Blue~";
        }
    }

    public static String getDateFormat() {
        int parseInt = Integer.parseInt(Prefs.getString(PreferenceKeys.TIMESTAMP_FORMAT, "0"));
        if (parseInt == 0) {
            return "M/d/yy, hh:mm:ss a";
        }
        if (parseInt == 1) {
            return "d/M/yy, hh:mm:ss a";
        }
        if (parseInt == 2) {
            return "M/d/yy, HH:mm:ss";
        }
        if (parseInt == 3) {
            return "d/M/yy, HH:mm:ss";
        }
        throw new UnsupportedOperationException();
    }

    public static String getPseudoNitroTextUrl(Emoji emoji) {
        if (!emoji.isActuallyAvailable()) {
            EmoteMode emoteMode = QuickAccessPrefs.getEmoteMode();
            if (emoteMode.isNewNitroSpoof()) {
                String uniqueId = emoji.getUniqueId();
                boolean startsWith = emoji.getMessageContentReplacement().startsWith("<a:");
                int emoteSizePx = emoteMode.getEmoteSizePx();
                String str = startsWith ? IconUtils.ANIMATED_IMAGE_EXTENSION : "png";
                return "https://cdn.discordapp.com/emojis/" + uniqueId + "." + str + "?v=1&size=" + emoteSizePx + "&quality=lossless";
            } else if (emoteMode.isOldNitroSpoof()) {
                String messageContentReplacement = emoji.getMessageContentReplacement();
                return messageContentReplacement.startsWith("<a:") ? messageContentReplacement.replace("<a:", "<\u200ba:") : messageContentReplacement.replace("<:", "<\u200b:");
            }
        }
        return emoji.getChatInputText();
    }

    public static RectF getViewBounds(View view) {
        var location = new int[2];
        view.getLocationOnScreen(location);
        int i = location[0];
        int i2 = location[1];
        return new RectF((float) i, (float) i2, (float) (view.getWidth() + i), (float) (view.getHeight() + i2));
    }

    public static int hideBlocked() {
        return Prefs.getBoolean(PreferenceKeys.HIDE_BLOCKED, false) ? Constants.blue_hide_blocked : Constants.widget_chat_list_adapter_item_blocked;
    }

    public static void init(Activity activity, boolean z2) {
        CrashHandler.setup();
        Colors.init();
        QuickAccessPrefs.reload();
        if (!z2) {
            Updater.checkFromLaunch(activity);
        }
        if (Prefs.getBoolean(PreferenceKeys.WAS_TOKEN_LOGIN, false)) {
            ToastUtil.customToast(activity, "Token login was successful");
            Prefs.setBoolean(PreferenceKeys.WAS_TOKEN_LOGIN, false);
        }
        String string = Prefs.getString(PreferenceKeys.CUSTOM_FONT_TYPE, "Default");
        typeface = null;
        if (string.equals("Default")) {
            return;
        }
        if (string.equals("Custom")) {
            String string2 = Prefs.getString(PreferenceKeys.CUSTOM_FONT_PATH, null);
            if (string2 != null) {
                try {
                    typeface = Typeface.createFromFile(string2);
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtil.toast("Failed to set font, custom font has been disabled.\n\nTry setting it again.");
                    Prefs.removeValues(PreferenceKeys.CUSTOM_FONT_TYPE, PreferenceKeys.CUSTOM_FONT_PATH);
                }
            }
        } else {
            typeface = Typeface.createFromAsset(activity.getAssets(), string);
        }
    }

    public static boolean isDarkModeOn() {
        return !ModelUserSettings.THEME_LIGHT.equalsIgnoreCase(StoreStream.Companion.getUserSettingsSystem().getTheme());
    }

    static /* synthetic */ void lambda$copyEmoteUrl$1(Object obj, View view) {
        if (obj instanceof EmojiSheetViewModel.ViewState.EmojiCustom) {
            EmojiNode.EmojiIdAndType.Custom component1 = ((EmojiSheetViewModel.ViewState.EmojiCustom) obj).component1();
            long id2 = component1.getId();
            boolean isAnimated = component1.isAnimated();
            int emoteSizePx = QuickAccessPrefs.getEmoteMode().getEmoteSizePx();
            String str = isAnimated ? IconUtils.ANIMATED_IMAGE_EXTENSION : "png";
            DiscordTools.copyToClipboard("https://cdn.discordapp.com/emojis/" + id2 + "." + str + "?v=1&size=" + emoteSizePx + "&quality=lossless");
            ToastUtil.toast("Copied to clipboard");
        } else if (obj instanceof EmojiSheetViewModel.ViewState.EmojiUnicode) {
            DiscordTools.copyToClipboard(((EmojiSheetViewModel.ViewState.EmojiUnicode) obj).getEmojiUnicode().getMessageContentReplacement());
            ToastUtil.toast("Copied to clipboard");
        }
    }

    static /* synthetic */ void lambda$setWelcomeText$0(TextView textView, ValueAnimator valueAnimator) {
        textView.setTextColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    public static boolean pseudoNitro() {
        return QuickAccessPrefs.getEmoteMode().isNitroSpoofEnabled();
    }

    public static String removeAnimatedIcons(String str) {
        return (!Prefs.getBoolean(PreferenceKeys.REMOVE_ANIMATED_ICONS, false) || (!str.equals(IconUtils.ANIMATED_IMAGE_EXTENSION) && !str.equals("webp"))) ? str : "png";
    }

    public static int removeGiftButton(int i) {
        if (Prefs.getBoolean(PreferenceKeys.REMOVE_GIFT_BUTTON, false)) {
            return 8;
        }
        return i;
    }

    public static List<Emoji> removeLockedEmotes(List<Emoji> list, boolean z2) {
        if (list == null || list.isEmpty() || !QuickAccessPrefs.getEmoteMode().hideLockedEmotes()) {
            return list;
        }
        if (z2) return new ArrayList<>();
        ListIterator<Emoji> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (!listIterator.next().isActuallyAvailable()) {
                listIterator.remove();
            }
        }
        return list;
    }

    public static boolean revealSpoilers() {
        return Prefs.getBoolean(PreferenceKeys.REVEAL_SPOILERS, false);
    }

    public static CharSequence setBluecordVersion(CharSequence charSequence) {
        if (charSequence == null || !"{BLUECORD_VERSION}".equalsIgnoreCase(charSequence.toString())) {
            return charSequence;
        }
        return "Version " + URLConstants.getVersionString();
    }

    public static boolean setBoldFont(TextView textView) {
        Typeface typeface2;
        if (textView == null || (typeface2 = typeface) == null) {
            return false;
        }
        textView.setTypeface(typeface2);
        return true;
    }

    public static void setFont(EditText editText) {
        Typeface typeface2;
        if (editText != null && (typeface2 = typeface) != null) {
            editText.setTypeface(typeface2);
        }
    }

    public static void setFont(TextView textView) {
        Typeface typeface2;
        if (textView != null && (typeface2 = typeface) != null) {
            textView.setTypeface(typeface2);
        }
    }

    public static void setMarqueeNames(TextView textView) {
        if (textView != null && Prefs.getBoolean(PreferenceKeys.MARQUEE_NAMES, false)) {
            textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            textView.setSelected(true);
            textView.setSingleLine(true);
            textView.setMarqueeRepeatLimit(-1);
        }
    }

    public static void setTrayText(EditText editText) {
        setFont(editText);
        Colors.animateEditText(editText);
    }

    public static void setWelcomeText(TextView textView, CharSequence charSequence) {
        if (charSequence.toString().equalsIgnoreCase("Welcome To Bluecord")) {
            ValueAnimator valueAnimator = new ValueAnimator();
            valueAnimator.setIntValues(Color.parseColor("#6d41ba"), Color.parseColor("#963cab"), Color.parseColor("#41a0ba"), Color.parseColor("#43d164"), Color.parseColor("#6d41ba"));
            valueAnimator.setEvaluator(new ArgbEvaluator());
            valueAnimator.addUpdateListener(new $$Lambda$ThemingTools$EJVg_W7d0wdL8u6Hyyba3lhqTg0(textView));
            valueAnimator.setRepeatCount(-1);
            valueAnimator.setDuration(7500L);
            valueAnimator.start();
        }
        textView.setText(charSequence);
    }

    public static boolean shouldPersistPrefs() {
        return Prefs.getBoolean(PreferenceKeys.PERSIST_PREFS, true);
    }

    public static boolean shouldShowEmote(Emoji emoji) {
        if (QuickAccessPrefs.getEmoteMode().hideLockedEmotes()) {
            return emoji.isActuallyAvailable();
        }
        return true;
    }

    public static boolean showEmbedLinks() {
        return Prefs.getBoolean(PreferenceKeys.ALWAYS_SHOW_EMBED_LINKS, false);
    }

    public static boolean showSquarePics() {
        return Prefs.getBoolean(PreferenceKeys.SQUARE_PICTURES, false);
    }

    public static void showSquarePicsDamnIt(a aVar, AttributeSet attributeSet) {
        c cVar;
        try {
            if (!Prefs.getBoolean(PreferenceKeys.SQUARE_PICTURES, false)) {
                return;
            }
            if (attributeSet != null) {
                if (!"true".equals(attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "key")) && (cVar = aVar.r) != null) {
                    cVar.b = false;
                    float[] fArr = cVar.c;
                    if (fArr == null) {
                        fArr = new float[8];
                        cVar.c = fArr;
                    }
                    Arrays.fill(fArr, (float) dipToPx(2.0f));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

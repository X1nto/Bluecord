package mods.view;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Environment;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.browser.trusted.sharing.ShareTarget;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.discord.api.channel.Channel;
import com.discord.api.message.attachment.MessageAttachment;
import com.discord.app.AppBottomSheet;
import com.discord.databinding.UserProfileHeaderViewBinding;
import com.discord.databinding.WidgetChatListActionsBinding;
import com.discord.models.member.GuildMember;
import com.discord.models.message.Message;
import com.discord.models.presence.Presence;
import com.discord.models.user.User;
import com.discord.utilities.icon.IconUtils;
import com.discord.utilities.presence.PresenceUtils;
import com.discord.widgets.chat.input.autocomplete.AutocompleteViewModel;
import com.discord.widgets.chat.list.actions.WidgetChatListActions;
import com.discord.widgets.chat.list.adapter.WidgetChatListAdapterItemAttachment;
import com.discord.widgets.guilds.profile.WidgetGuildProfileSheetViewModel;
import com.discord.widgets.user.profile.UserProfileHeaderView;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import javax.net.ssl.HttpsURLConnection;
import mods.DiscordTools;
import mods.ThemingTools;
import mods.activity.MediaTray;
import mods.constants.Constants;
import mods.constants.PreferenceKeys;
import mods.preference.Prefs;
import mods.preference.QuickAccessPrefs;
import mods.utils.PermissionUtils;
import mods.utils.SnowflakeUtils;
import mods.utils.StoreUtils;
import mods.utils.StringUtils;
import mods.utils.ToastUtil;
import mods.utils.translate.Translate;

public class SheetConfig {
    private static final String TAG = "SheetConfig";

    public static void addUserDetails(User user, GuildMember guildMember, SpannableStringBuilder spannableStringBuilder) {
        String string = Prefs.getString(PreferenceKeys.DAYS_ON_DISCORD, "Days since creation");
        if (!"Off".equalsIgnoreCase(string)) {
            StringBuilder sb = new StringBuilder("\n");
            long accountCreationTime = SnowflakeUtils.getAccountCreationTime(user);
            boolean z2 = (guildMember == null || guildMember.getJoinedAt() == null) ? false : true;
            if ("Days since creation".equalsIgnoreCase(string)) {
                sb.append("Account created ");
                sb.append(StringUtils.convertToTimeBehind(new Date(accountCreationTime)));
                if (z2) {
                    sb.append("\nJoined server ");
                    sb.append(StringUtils.convertToTimeBehind(new Date(guildMember.getJoinedAt().g())));
                }
            } else {
                sb.append("Account created at: ");
                sb.append(DiscordTools.formatDate(accountCreationTime));
                if (z2) {
                    sb.append("\nJoined server at: ");
                    sb.append(DiscordTools.formatDate(guildMember.getJoinedAt().g()));
                }
            }
            int length = spannableStringBuilder.length();
            spannableStringBuilder.append((CharSequence) sb);
            int length2 = spannableStringBuilder.length();
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(14, true), length, length2, 33);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(ThemingTools.isDarkModeOn() ? -3355444 : ViewCompat.MEASURED_STATE_MASK), length, length2, 33);
        }
    }

    public static void configureChatList(AppBottomSheet appBottomSheet, WidgetChatListActionsBinding widgetChatListActionsBinding, WidgetChatListActions.Model model) {
        TextView textView = widgetChatListActionsBinding.c;
        TextView textView2 = widgetChatListActionsBinding.getRoot().findViewById(Constants.BLUE_ID_1);
        TextView textView3 = widgetChatListActionsBinding.getRoot().findViewById(Constants.BLUE_ID_2);
        Channel channel = model.getChannel();
        Message message = model.getMessage();
        String content = message.getContent();
        if (content == null) {
            textView2.setVisibility(View.GONE);
            textView3.setVisibility(View.GONE);
        } else {
            textView2.setOnClickListener(new $$Lambda$SheetConfig$PQbKU1tzt2M8mFhr0GgHTFnov0(channel, message, appBottomSheet));
            textView3.setOnClickListener(new $$Lambda$SheetConfig$bKzrgZK4BQgyJEFcwoQWMWtQK0E(appBottomSheet, message));
        }
        if (textView.getVisibility() == View.GONE && !message.isLocalApplicationCommand()) {
            if (!StringUtils.isEmpty(content) || message.hasAttachments()) {
                textView.setText("Copy attachment URLs");
                textView.setVisibility(View.VISIBLE);
                textView.setOnClickListener(new $$Lambda$SheetConfig$3NInVdzfbkuGpcmrhXirtWrO1Gc(content, message, appBottomSheet));
            }
        }
    }

    public static void configureGuildSheet(Fragment fragment, WidgetGuildProfileSheetViewModel.ViewState.Loaded loaded, View view, String str, String str2) {
        Log.e(TAG, "icon = " + str + ", bannerIcon = " + str2);
        String maxUrlResolution = maxUrlResolution(str);
        String maxUrlResolution2 = maxUrlResolution(str2);
        openUrlAsAttachment(view.findViewById(Constants.ID_GUILD_PROFILE_SHEET_ICON), loaded.getGuildName(), maxUrlResolution);
        openUrlAsAttachment(view.findViewById(Constants.ID_GUILD_PROFILE_SHEET_BANNER), loaded.getGuildName(), maxUrlResolution2);
        @SuppressLint("ResourceType") TextView textView = (TextView) view.findViewById(16908321);
        textView.setVisibility(View.VISIBLE);
        textView.setOnClickListener(new $$Lambda$SheetConfig$FS5lf0dtSLKBnvrcjfdHAS0gzmc(fragment, maxUrlResolution, maxUrlResolution2));
    }

    @SuppressLint("ResourceType")
    public static void configureUserSheet(View view, User user, UserProfileHeaderView userProfileHeaderView) {
        UserProfileHeaderViewBinding access$getBinding$p = UserProfileHeaderView.access$getBinding$p(userProfileHeaderView);
        String maxUrlResolution = maxUrlResolution(IconUtils.getForUser(user, true, (int) view.getResources().getDimension(Constants.DIMEN_SMALL_AVATAR)));
        String maxUrlResolution2 = maxUrlResolution(userProfileHeaderView.bannerUrl);
        if (access$getBinding$p != null) {
            String lowerCase = user.getUsername().toLowerCase();
            openUrlAsAttachment(access$getBinding$p.getRoot().findViewById(Constants.ID_AVATAR), lowerCase, maxUrlResolution);
            openUrlAsAttachment(access$getBinding$p.getRoot().findViewById(Constants.ID_BANNER), lowerCase, maxUrlResolution2);
        }
        TextView textView = (TextView) view.findViewById(16908321);
        textView.setVisibility(0);
        textView.setOnClickListener(new $$Lambda$SheetConfig$xjrUh65EeaimPAjo_uSfP1GzmJQ(maxUrlResolution));
        TextView textView2 = (TextView) view.findViewById(16908323);
        textView2.setVisibility(0);
        textView2.setOnClickListener(new $$Lambda$SheetConfig$c3vPGMnWdSUkENfip2oNxJ0S2Eo(maxUrlResolution2));
        TextView textView3 = (TextView) view.findViewById(16908293);
        textView3.setOnClickListener(new $$Lambda$SheetConfig$u0B5FrvhvP45TVMdkuZQAVgFwRE(user));
        textView3.setVisibility(0);
    }

    private static void download(String str, String str2) throws IOException {
        if (!StringUtils.isEmpty(str)) {
            String str3 = ".gif";
            boolean contains = str.contains(str3);
            StringBuilder sb = new StringBuilder();
            sb.append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
            sb.append(AutocompleteViewModel.COMMAND_DISCOVER_TOKEN);
            sb.append(System.currentTimeMillis() / 1000);
            sb.append("-");
            sb.append(str2);
            if (!contains) {
                str3 = ".png";
            }
            sb.append(str3);
            File file = new File(sb.toString());
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            Log.e(TAG, "Downloading '" + str + "'");
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
            httpsURLConnection.setConnectTimeout(30000);
            httpsURLConnection.setReadTimeout(30000);
            httpsURLConnection.setRequestMethod(ShareTarget.METHOD_GET);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(httpsURLConnection.getInputStream());
            FileOutputStream fileOutputStream = new FileOutputStream(file, false);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    bufferedInputStream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return;
                }
            }
        }
    }

    public static void fixAttachmentUrl(MessageAttachment messageAttachment) {
        if (messageAttachment != null) {
            if (!StringUtils.isEmpty(messageAttachment.proxyUrl) && messageAttachment.proxyUrl.startsWith("https://media.discordapp.net/attachments/")) {
                messageAttachment.proxyUrl = "https://cdn.discordapp.com/attachments/" + messageAttachment.proxyUrl.substring(41);
            }
            if (!StringUtils.isEmpty(messageAttachment.url) && messageAttachment.url.startsWith("https://media.discordapp.net/attachments/")) {
                messageAttachment.url = "https://cdn.discordapp.com/attachments/" + messageAttachment.url.substring(41);
            }
        }
    }

    public static String fixFormattedUrl(Uri uri) {
        if (uri == null || !uri.toString().contains("?size=")) {
            return null;
        }
        return uri.toString();
    }

    static /* synthetic */ void lambda$configureChatList$1(Channel channel, Message message, AppBottomSheet appBottomSheet, View view) {
        String quoteMessage = StringUtils.quoteMessage(channel, message);
        if (quoteMessage.isEmpty()) {
            ToastUtil.toast("You can't quote messages without text");
        } else {
            MediaTray.setTrayText(quoteMessage);
        }
        appBottomSheet.dismiss();
    }

    static /* synthetic */ void lambda$configureChatList$2(AppBottomSheet appBottomSheet, Message message, View view) {
        Translate.showTranslateDialog(appBottomSheet.getActivity(), message);
        appBottomSheet.dismiss();
    }

    static /* synthetic */ void lambda$configureChatList$3(String str, Message message, AppBottomSheet appBottomSheet, View view) {
        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isEmpty(str)) {
            sb.append("Message text:\n");
            sb.append(str);
            sb.append("\n\n");
        }
        if (message.hasAttachments()) {
            sb.append("Attachments:\n");
            for (MessageAttachment messageAttachment : message.getAttachments()) {
                sb.append("\n");
                sb.append(messageAttachment.proxyUrl);
            }
        }
        ToastUtil.toast("Attachment URLs copied to clipboard");
        DiscordTools.copyToClipboard(sb.toString().trim());
        appBottomSheet.dismiss();
    }

    static /* synthetic */ void lambda$configureGuildSheet$7(FragmentActivity fragmentActivity, String str, String str2) {
        if (!PermissionUtils.hasStoragePermission(fragmentActivity)) {
            return;
        }
        if (!StringUtils.isEmpty(str) || !StringUtils.isEmpty(str2)) {
            ToastUtil.toast("Downloading, please wait...");
            try {
                download(str, "icon");
                download(str2, "banner");
                ToastUtil.toast("Icons saved to your Downloads folder");
            } catch (IOException e) {
                Log.e(TAG, "Failed to download. guildUrl = '" + str + "' , ' bannerUrl = '" + str2 + "'", e);
                ToastUtil.toast("Downloading the icons failed. Check your Internet connection and retry.");
            }
        } else {
            ToastUtil.toast("Guild doesn't have an icon and doesn't have a banner, nothing to download.");
        }
    }

    static /* synthetic */ void lambda$configureGuildSheet$8(String str, String str2, FragmentActivity fragmentActivity, DialogInterface dialogInterface, int i) {
        if (i == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Icon URL:\n");
            String str3 = "None";
            sb.append(StringUtils.isEmpty(str) ? str3 : str);
            sb.append("\n\nBanner URL:\n");
            if (!StringUtils.isEmpty(str2)) {
                str3 = str2;
            }
            sb.append(str3);
            DiscordTools.copyToClipboard(sb.toString());
            ToastUtil.toast("Copied to clipboard");
            return;
        }
        new Thread(new $$Lambda$SheetConfig$68ngnLlKaxSUMOZJ77enWHUE8Lo(fragmentActivity, str, str2)).start();
    }

    static /* synthetic */ void lambda$configureGuildSheet$9(Fragment fragment, String str, String str2, View view) {
        FragmentActivity requireActivity = fragment.requireActivity();
        DiscordTools.newBuilder(requireActivity).setTitle("Guild Icon / Banner").setItems(new String[]{"Copy to clipboard", "Download"}, new $$Lambda$SheetConfig$5esGISwmXWzBuzObccHNyywZho(str, str2, requireActivity)).setPositiveButton("Exit", (DialogInterface.OnClickListener) null).show();
    }

    static /* synthetic */ void lambda$configureUserSheet$4(String str, View view) {
        if (!StringUtils.isEmpty(str)) {
            DiscordTools.copyToClipboard(str);
            ToastUtil.toast("URL copied to clipboard");
            return;
        }
        ToastUtil.toast("User does not have a profile picture or you're not connected to Discord");
    }

    static /* synthetic */ void lambda$configureUserSheet$5(String str, View view) {
        if (!StringUtils.isEmpty(str)) {
            DiscordTools.copyToClipboard(str);
            ToastUtil.toast("Banner URL copied to clipboard");
            return;
        }
        ToastUtil.toast("User does not have a banner picture or you're not connected to Discord");
    }

    static /* synthetic */ void lambda$configureUserSheet$6(User user, View view) {
        DiscordTools.copyToClipboard(StringUtils.getUsernameWithDiscriminator(user));
        ToastUtil.toast("Name + Tag copied to clipboard");
    }

    static /* synthetic */ void lambda$modifyStatusIndicator$0(int i, AppCompatImageView appCompatImageView) {
        switch (i) {
            case Constants.IC_STATUS_DND:
                appCompatImageView.setImageResource(Constants.IC_SCREEN_14DP);
                appCompatImageView.setColorFilter(Color.parseColor("#ed4245"), PorterDuff.Mode.SRC_ATOP);
                return;
            case Constants.IC_STATUS_IDLE:
                appCompatImageView.setImageResource(Constants.IC_SCREEN_14DP);
                appCompatImageView.setColorFilter(Color.parseColor("#faa61a"), PorterDuff.Mode.SRC_ATOP);
                return;
            case Constants.IC_STATUS_INVISIBLE:
            default:
                return;
            case Constants.IC_STATUS_ONLINE:
                appCompatImageView.setImageResource(Constants.IC_SCREEN_14DP);
                appCompatImageView.setColorFilter(Color.parseColor("#3ba55c"), PorterDuff.Mode.SRC_ATOP);
        }
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x0019: APUT  (r1v1 java.lang.Object[]), (1 ??[boolean, int, float, short, byte, char]), (r2v3 java.lang.String) */
    static /* synthetic */ void lambda$openUrlAsAttachment$10(String str, String str2, View view, View view2) {
        MessageAttachment messageAttachment = new MessageAttachment();
        Object[] objArr = new Object[2];
        objArr[0] = str;
        objArr[1] = str2.contains("/a_") ? IconUtils.ANIMATED_IMAGE_EXTENSION : "png";
        messageAttachment.filename = String.format("%s.%s", objArr);
        messageAttachment.id = SnowflakeUtils.toSnowflakeId(StoreUtils.getServerSyncedTime());
        messageAttachment.url = str2;
        messageAttachment.proxyUrl = str2;
        WidgetChatListAdapterItemAttachment.Companion companion = WidgetChatListAdapterItemAttachment.Companion;
        WidgetChatListAdapterItemAttachment.Companion.access$navigateToAttachment(WidgetChatListAdapterItemAttachment.Companion, view.getContext(), messageAttachment);
    }

    private static String maxUrlResolution(String str) {
        if (str == null) {
            return "";
        }
        boolean contains = str.contains("/a_");
        if (str.contains("?")) {
            String substring = str.substring(0, str.lastIndexOf("?"));
            if (contains) {
                substring = substring.substring(0, substring.lastIndexOf(".")) + ".gif";
            }
            return substring + "?size=2048";
        } else if (str.endsWith(".webp")) {
            StringBuilder sb = new StringBuilder();
            sb.append(str.substring(0, str.length() - 5));
            sb.append(".");
            sb.append(contains ? IconUtils.ANIMATED_IMAGE_EXTENSION : "jpg");
            sb.append("?size=2048");
            return sb.toString();
        } else {
            return str + "?size=2048";
        }
    }

    public static boolean modifyStatusIndicator(AppCompatImageView appCompatImageView, Presence presence, int i) {
        if (appCompatImageView == null) {
            return false;
        }
        if (i != Constants.IC_STATUS_ONLINE && i != Constants.IC_STATUS_IDLE && i != Constants.IC_STATUS_DND) {
            if (appCompatImageView.getColorFilter() != null) {
                appCompatImageView.clearColorFilter();
            }
            return false;
        } else if (!QuickAccessPrefs.isBetterStatusIndicatorEnabled() || presence == null || !PresenceUtils.INSTANCE.isWeb(presence.getClientStatuses())) {
            return false;
        } else {
            appCompatImageView.post(new $$Lambda$SheetConfig$BH3aqRD3VEgg6EP9fzI7QkO3x0(i, appCompatImageView));
            return true;
        }
    }

    private static void openUrlAsAttachment(View view, String str, String str2) {
        if (view != null && !StringUtils.isEmpty(str2)) {
            view.setOnClickListener(new $$Lambda$SheetConfig$OCGuymwF5Kx87jBq3OmcbMXl7cU(str, str2, view));
        }
    }
}

package mods.view;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.discord.api.channel.Channel;
import com.discord.databinding.WidgetChannelsListItemChannelPrivateBinding;
import com.discord.utilities.view.text.SimpleDraweeSpanTextView;
import com.discord.widgets.channels.list.WidgetChannelsList;
import com.discord.widgets.channels.list.WidgetChannelsListAdapter;
import com.discord.widgets.channels.list.WidgetChannelsListItemChannelActions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import mods.DiscordTools;
import mods.ThemingTools;
import mods.utils.StoreUtils;
import mods.utils.ToastUtil;
public class ChatPins {
    private static final List<Long> PINNED_IDS = new ArrayList<>();
    private static final String TAG = "ChatPins";

    static {
        Set<String> stringSet = DiscordTools.getContext().getSharedPreferences("PinnedChats", 0).getStringSet("v1", null);
        if (stringSet != null && stringSet.size() > 0) {
            for (String str : stringSet) {
                try {
                    PINNED_IDS.add(Long.parseLong(str));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void commitChanges(WidgetChannelsList widgetChannelsList, Long l) {
        List<Long> list = PINNED_IDS;
        HashSet hashSet = new HashSet(list.size(), 1.0f);
        for (Long l2 : list) {
            hashSet.add(Long.toString(l2.longValue()));
        }
        DiscordTools.getContext().getSharedPreferences("PinnedChats", 0).edit().putStringSet("v1", hashSet).commit();
        widgetChannelsList.onViewBoundOrOnResume();
        WidgetChannelsListAdapter access$getAdapter$p = WidgetChannelsList.access$getAdapter$p(widgetChannelsList);
        if (access$getAdapter$p != null) {
            access$getAdapter$p.notifyDataSetChanged();
        }
        widgetChannelsList.scrollToTop();
        WidgetChannelsList.access$setSelectedGuildId$p(widgetChannelsList, l);
    }

    public static void configurePrivateChannelItem(WidgetChannelsListItemChannelPrivateBinding widgetChannelsListItemChannelPrivateBinding, View view, Channel channel) {
        TextView textView = widgetChannelsListItemChannelPrivateBinding.f;
        SimpleDraweeSpanTextView simpleDraweeSpanTextView = widgetChannelsListItemChannelPrivateBinding.d;
        TextView textView2 = widgetChannelsListItemChannelPrivateBinding.g;
        if (isPinned(channel)) {
            @SuppressLint("ResourceType") LinearLayout linearLayout = (LinearLayout) view.findViewById(16908308);
            textView2.setText("Pinned");
            textView2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            textView2.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView2.getLayoutParams();
            layoutParams.setMarginStart(0);
            layoutParams.setMarginEnd(ThemingTools.dipToPx(3.0f));
            textView2.setLayoutParams(layoutParams);
            linearLayout.removeView(textView2);
            linearLayout.addView(textView2, 0);
        }
        ThemingTools.setFont(textView);
        ThemingTools.setFont(simpleDraweeSpanTextView);
        ThemingTools.setMarqueeNames(textView);
        ThemingTools.setMarqueeNames(simpleDraweeSpanTextView);
    }

    public static void configureUI(WidgetChannelsListItemChannelActions widgetChannelsListItemChannelActions, View view, Channel channel) {
        if (DiscordTools.findFragmentByClass(widgetChannelsListItemChannelActions, WidgetChannelsList.class) == null) {
            Log.e(TAG, "unable to find required fragment in stack");
            return;
        }
        WidgetChannelsList widgetChannelsList = (WidgetChannelsList) DiscordTools.findFragmentByClass(widgetChannelsListItemChannelActions, WidgetChannelsList.class);
        if (widgetChannelsList != null) {
            @SuppressLint("ResourceType") View findViewById = view.findViewById(16908313);
            if (!(findViewById instanceof TextView)) {
                Log.e(TAG, "unable to locate textview");
                return;
            }
            TextView textView = (TextView) findViewById;
            textView.setVisibility(View.VISIBLE);
            boolean isPinned = isPinned(channel);
            textView.setText(isPinned ? "Unpin Chat" : "Pin Chat");
            textView.setOnClickListener(new $$Lambda$ChatPins$z12mdOnbql5sfzVrIEgjfA6uc(widgetChannelsListItemChannelActions, widgetChannelsList, channel, isPinned));
        }
    }

    public static boolean isPinned(Object obj) {
        List<Long> list = PINNED_IDS;
        if (list.size() == 0) {
            return false;
        }
        long id2 = StoreUtils.getId(obj);
        return id2 > 0 && list.contains(Long.valueOf(id2));
    }

    static /* synthetic */ void lambda$configureUI$0(WidgetChannelsListItemChannelActions widgetChannelsListItemChannelActions, WidgetChannelsList widgetChannelsList, Channel channel, boolean z2, View view) {
        widgetChannelsListItemChannelActions.dismiss();
        setPinned(widgetChannelsList, Long.valueOf(channel.h()), !z2);
    }

    private static void setPinned(WidgetChannelsList widgetChannelsList, Long l, boolean z2) {
        synchronized (ChatPins.class) {
            if (z2) {
                PINNED_IDS.add(l);
                ToastUtil.toast("Chat pinned");
            } else {
                PINNED_IDS.remove(l);
                ToastUtil.toast("Chat unpinned");
            }
            commitChanges(widgetChannelsList, l);
        }
    }

    public static List<Channel> sort(List<Channel> list, Comparator<Channel> comparator) {
        if (PINNED_IDS.isEmpty() || list.isEmpty()) {
            return list;
        }
        ArrayList arrayList = new ArrayList(list);
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            Channel channel = (Channel) arrayList.get(i);
            if (PINNED_IDS.contains(Long.valueOf(channel.h()))) {
                arrayList.remove(channel);
                arrayList2.add(channel);
            }
        }
        Collections.sort(arrayList2, comparator);
        arrayList2.addAll(arrayList);
        arrayList.clear();
        return arrayList2;
    }
}

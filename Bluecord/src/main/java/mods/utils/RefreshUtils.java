package mods.utils;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.discord.widgets.chat.list.WidgetChatList;
import com.discord.widgets.chat.list.adapter.WidgetChatListAdapter;
import com.discord.widgets.chat.list.entries.ChatListEntry;
import com.discord.widgets.chat.list.entries.MessageEntry;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class RefreshUtils {
    private static final String TAG = "RefreshUtils";
    public static WidgetChatList WIDGET_CHAT_LIST = null;
    private static final ReentrantLock lock = new ReentrantLock();

    public static void invalidateMessage(long j) {
        WidgetChatList widgetChatList = WIDGET_CHAT_LIST;
        if (widgetChatList == null) {
            Log.e(TAG, "invalidateMessage() failed");
            return;
        }
        WidgetChatListAdapter access$getAdapter$p = WidgetChatList.access$getAdapter$p(widgetChatList);
        List<ChatListEntry> internalData = access$getAdapter$p.getInternalData();
        if (internalData != null) {
            for (int i = 0; i < internalData.size(); i++) {
                ChatListEntry chatListEntry = internalData.get(i);
                if ((chatListEntry instanceof MessageEntry) && ((MessageEntry) chatListEntry).getMessage().getId() == j) {
                    Log.e(TAG, "message " + j + " invalidated");
                    access$getAdapter$p.notifyItemChanged(i);
                    return;
                }
            }
        }
    }

    static /* synthetic */ void lambda$refreshView$0() {
        ReentrantLock reentrantLock = lock;
        if (reentrantLock.tryLock()) {
            try {
                try {
                    FragmentManager fragmentManager = WIDGET_CHAT_LIST.getFragmentManager();
                    FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                    if (Build.VERSION.SDK_INT >= 24) {
                        beginTransaction.detach(WIDGET_CHAT_LIST).commitNow();
                        fragmentManager.beginTransaction().attach(WIDGET_CHAT_LIST).commitNow();
                    } else {
                        beginTransaction.detach(WIDGET_CHAT_LIST).attach(WIDGET_CHAT_LIST).commit();
                    }
                    Log.e(TAG, "refreshView() - success");
                    reentrantLock.unlock();
                } catch (Exception e) {
                    Log.e(TAG, "refreshView() - failed", e);
                    lock.unlock();
                }
            } catch (Throwable th) {
                lock.unlock();
                throw th;
            }
        }
    }

    public static void refreshView() {
        WidgetChatList widgetChatList = WIDGET_CHAT_LIST;
        if (widgetChatList != null && !widgetChatList.isStateSaved() && WIDGET_CHAT_LIST.getFragmentManager() != null) {
            new Handler(Looper.getMainLooper()).post($$Lambda$RefreshUtils$jWEnmoZ2J3EzzLL0CNRy7AYUR98.INSTANCE);
        }
    }
}

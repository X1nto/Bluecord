package mods.utils;

import android.view.View;
import android.widget.TextView;
import com.discord.views.UsernameView;

import mods.constants.Constants;

public class DevBadge {
    private static long[] badgeList = new long[0];

    public static void add(UsernameView usernameView, long j) {
        if (needsBadge(j)) {
            TextView textView = usernameView.i.b;
            textView.setVisibility(View.VISIBLE);
            textView.setText("Bluecord Dev");
            textView.setCompoundDrawablesWithIntrinsicBounds(Constants.VERIFIED_DEV_BADGE, 0, 0, 0);
        }
    }

    public static boolean needsBadge(long j) {
        for (long j2 : badgeList) {
            if (j == j2) {
                return true;
            }
        }
        return false;
    }

    public static boolean needsBadge(Object obj) {
        return needsBadge(StoreUtils.getId(obj));
    }

    public static void setBadgeList(long[] jArr) {
        badgeList = jArr;
    }
}

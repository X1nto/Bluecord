package mods.net;

import android.content.DialogInterface;
import androidx.fragment.app.FragmentActivity;
import com.adjust.sdk.Constants;
import java.net.URLEncoder;
import java.util.Random;
import mods.DiscordTools;
import mods.utils.ToastUtil;
import org.json.JSONArray;
import org.json.JSONObject;
public class Urban {
    public static void getDefinition(FragmentActivity fragmentActivity, String str) {
        new Thread(new $$Lambda$Urban$HnOjwi9V5TxwmUvYyOnNkTDX0(str, fragmentActivity)).start();
    }

    static /* synthetic */ void lambda$getDefinition$0(String str, DialogInterface dialogInterface, int i) {
        DiscordTools.copyToClipboard(str);
        ToastUtil.toast("Copied to clipboard");
    }

    static /* synthetic */ void lambda$getDefinition$1(FragmentActivity fragmentActivity, String str) {
        DiscordTools.newBuilder(fragmentActivity).setTitle("Urban Dictionary").setMessage(str).setNeutralButton("Copy", new $$Lambda$Urban$MTEvktNnGKrnxM0HWsiUtREOMjw(str)).setPositiveButton("Exit", (DialogInterface.OnClickListener) null).show();
    }

    static /* synthetic */ void lambda$getDefinition$2(String str, FragmentActivity fragmentActivity) {
        try {
            String trim = str.replace("\n", "").trim();
            if (trim.isEmpty()) {
                ToastUtil.toast("Invalid query");
                return;
            }
            String nonAsyncRequest = Net.nonAsyncRequest("https://api.urbandictionary.com/v0/define?term=" + URLEncoder.encode(trim, Constants.ENCODING), null);
            if (nonAsyncRequest == null) {
                ToastUtil.toast("Something went wrong, check your connection or search term and retry.");
                return;
            }
            JSONArray optJSONArray = new JSONObject(nonAsyncRequest).optJSONArray("list");
            if (optJSONArray != null) {
                if (optJSONArray.length() != 0) {
                    fragmentActivity.runOnUiThread(new $$Lambda$Urban$wJ5_rItbtRPLrpuTlmVlsYmE(fragmentActivity, "Result for " + trim + ":\n\n" + optJSONArray.getJSONObject(new Random().nextInt(optJSONArray.length())).optString("definition", "").replace("[", "").replace("]", "")));
                    return;
                }
            }
            ToastUtil.toast("'" + trim + "' has no results.");
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtil.toast("Something went wrong.");
        }
    }
}

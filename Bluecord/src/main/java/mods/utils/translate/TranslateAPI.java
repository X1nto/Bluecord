package mods.utils.translate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import com.adjust.sdk.Constants;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import mods.DiscordTools;
import mods.net.Net;
import mods.utils.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class TranslateAPI extends AsyncTask<Void, Void, String> {
    private final ITranslateCallback callback;
    private final String lang;
    private final ProgressDialog pd;
    private final String text;

    private TranslateAPI(Activity activity, ITranslateCallback iTranslateCallback, String str, String str2) {
        this.pd = DiscordTools.newProgressDialog(activity);
        this.callback = iTranslateCallback;
        this.lang = str;
        this.text = str2;
    }

    private static LinkedHashMap<String, String> getHeaders(boolean z2) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("Host", z2 ? "clients5.google.com" : "translate.googleapis.com");
        linkedHashMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36");
        linkedHashMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        return linkedHashMap;
    }

    private static String primaryMethod(String str, String str2) {
        try {
            String nonAsyncRequest = Net.nonAsyncRequest("https://clients5.google.com/translate_a/t?client=dict-chrome-ex&sl=auto&tl=" + str + "&q=" + URLEncoder.encode(str2, Constants.ENCODING), null, getHeaders(true));
            System.out.println(nonAsyncRequest);
            if (nonAsyncRequest.startsWith("[")) {
                return new JSONArray(nonAsyncRequest).getString(0);
            }
            if (!nonAsyncRequest.startsWith("{")) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            JSONArray jSONArray = new JSONObject(nonAsyncRequest).getJSONArray("sentences");
            for (int i = 0; i < jSONArray.length(); i++) {
                sb.append(jSONArray.getJSONObject(i).optString("trans", ""));
                sb.append(" ");
            }
            return sb.toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String secondaryMethod(String str, String str2) {
        try {
            JSONArray jSONArray = new JSONArray(Net.nonAsyncRequest("https://translate.googleapis.com/translate_a/single?client=gtx&sl=auto&tl=" + str + "&ie=UTF-8&dt=t&q=" + URLEncoder.encode(str2, Constants.ENCODING), null, getHeaders(false))).getJSONArray(0);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < jSONArray.length(); i++) {
                sb.append(jSONArray.getJSONArray(i).getString(0));
                sb.append(" ");
            }
            return sb.toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void translate(Activity activity, String str, String str2, ITranslateCallback iTranslateCallback) {
        new TranslateAPI(activity, iTranslateCallback, str, str2).execute(new Void[0]);
    }

    /* access modifiers changed from: protected */
    public String doInBackground(Void... voidArr) {
        String primaryMethod = primaryMethod(this.lang, this.text);
        if (primaryMethod != null) {
            Log.e(getClass().getName(), "First method successful");
            return primaryMethod;
        }
        Log.e(getClass().getName(), "First method blocked, trying second method...");
        return secondaryMethod(this.lang, this.text);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String str) {
        if (this.pd.isShowing()) {
            this.pd.dismiss();
        }
        if (StringUtils.isEmpty(str)) {
            this.callback.onError();
        } else {
            this.callback.onResult(str);
        }
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        this.pd.setTitle("Loading...");
        this.pd.show();
    }
}

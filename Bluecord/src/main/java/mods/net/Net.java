package mods.net;

import android.os.AsyncTask;
import androidx.browser.trusted.sharing.ShareTarget;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HttpsURLConnection;

public class Net extends AsyncTask<Void, Void, String> {
    private final LinkedHashMap<String, String> headers;
    private final String postData;
    private final String url;

    private Net(String str, String str2, LinkedHashMap<String, String> linkedHashMap) {
        this.url = str;
        this.postData = str2;
        this.headers = linkedHashMap;
    }

    public static String asyncRequest(String str, String str2) {
        return asyncRequest(str, str2, null);
    }

    public static String asyncRequest(String str, String str2, LinkedHashMap<String, String> linkedHashMap) {
        try {
            return new Net(str, str2, linkedHashMap).execute(new Void[0]).get(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getOrPost(String str, String str2, LinkedHashMap<String, String> linkedHashMap) {
        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
            httpsURLConnection.setConnectTimeout(5000);
            httpsURLConnection.setReadTimeout(5000);
            if (linkedHashMap != null && linkedHashMap.size() > 0) {
                for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
                    httpsURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            boolean z2 = str2 != null;
            httpsURLConnection.setDoOutput(z2);
            httpsURLConnection.setRequestMethod(z2 ? ShareTarget.METHOD_POST : ShareTarget.METHOD_GET);
            if (z2) {
                OutputStream outputStream = httpsURLConnection.getOutputStream();
                try {
                    outputStream.write(str2.getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            if (httpsURLConnection.getResponseCode() != 200) {
                return null;
            }
            return NetUtils.readInputStream(httpsURLConnection).trim();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String nonAsyncRequest(String str, String str2) {
        return nonAsyncRequest(str, str2, null);
    }

    public static String nonAsyncRequest(String str, String str2, LinkedHashMap<String, String> linkedHashMap) {
        return getOrPost(str, str2, linkedHashMap);
    }

    /* access modifiers changed from: protected */
    public String doInBackground(Void... voidArr) {
        return getOrPost(this.url, this.postData, this.headers);
    }
}

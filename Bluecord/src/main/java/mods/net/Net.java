package mods.net;

import android.os.AsyncTask;
import android.util.Log;
import androidx.browser.trusted.sharing.ShareTarget;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HttpsURLConnection;

public class Net extends AsyncTask<Void, Void, String> {
    private final String postData;
    private final String url;

    private Net(String str, String str2) {
        this.url = str;
        this.postData = str2;
    }

    public static String asyncRequest(String str, String str2) {
        try {
            return new Net(str, str2).execute(new Void[0]).get(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getOrPost(String str, String str2) {
        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
            httpsURLConnection.setConnectTimeout(5000);
            httpsURLConnection.setReadTimeout(5000);
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
            int responseCode = httpsURLConnection.getResponseCode();
            Log.e("Bluecord", "[" + httpsURLConnection.getRequestMethod() + "] response code = " + responseCode);
            if (responseCode != 200) {
                return null;
            }
            return NetUtils.readInputStream(httpsURLConnection).trim();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String nonAsyncRequest(String str, String str2) {
        return getOrPost(str, str2);
    }

    /* access modifiers changed from: protected */
    public String doInBackground(Void... voidArr) {
        return getOrPost(this.url, this.postData);
    }
}

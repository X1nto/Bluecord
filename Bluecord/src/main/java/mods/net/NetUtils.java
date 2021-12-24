package mods.net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.HttpsURLConnection;
class NetUtils {
    NetUtils() {
    }

    static String readInputStream(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] bArr = new byte[8192];
        StringBuilder sb = new StringBuilder();
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read != -1) {
                sb.append(new String(bArr, 0, read, StandardCharsets.UTF_8));
            } else {
                bufferedInputStream.close();
                return sb.toString();
            }
        }
    }

    static String readInputStream(HttpsURLConnection httpsURLConnection) throws IOException {
        return readInputStream(httpsURLConnection.getInputStream());
    }
}

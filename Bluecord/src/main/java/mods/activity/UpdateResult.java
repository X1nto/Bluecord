package mods.activity;

import mods.utils.DevBadge;

import org.json.JSONException;
import org.json.JSONObject;
class UpdateResult {
    private final String message;
    private final boolean succeeded;
    private final boolean updateAvailable;
    private final String updateLink;

    private UpdateResult() {
        this.updateAvailable = false;
        this.message = null;
        this.updateLink = null;
        this.succeeded = false;
    }

    private UpdateResult(boolean z2, String str, String str2, String str3) {
        this.updateAvailable = z2;
        this.message = str;
        this.updateLink = str2;
        this.succeeded = true;
        if (str3 != null) {
            try {
                String[] split = str3.split(",");
                long[] jArr = new long[split.length];
                for (int i = 0; i < split.length; i++) {
                    jArr[i] = Long.parseLong(split[i].trim());
                }
                DevBadge.setBadgeList(jArr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static UpdateResult parse(String str) {
        if (str == null) {
            try {
                String string = Updater.getUpdatePrefs().getString("update_data", null);
                if (string == null) {
                    return new UpdateResult();
                }
                JSONObject jSONObject = new JSONObject(string);
                return new UpdateResult(jSONObject.optBoolean("update"), jSONObject.optString("message"), jSONObject.optString("url"), jSONObject.optString("devs"));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            Updater.getUpdatePrefs().edit().putString("update_data", str).apply();
            JSONObject jSONObject2 = null;
            try {
                jSONObject2 = new JSONObject(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new UpdateResult(jSONObject2.optBoolean("update"), jSONObject2.optString("message"), jSONObject2.optString("url"), jSONObject2.optString("devs"));
        }
    }

    String getMessage() {
        return this.message;
    }

    String getUpdateLink() {
        return this.updateLink;
    }

    boolean isUpdateAvailable() {
        return this.updateAvailable;
    }

    boolean succeeded() {
        return this.succeeded;
    }
}

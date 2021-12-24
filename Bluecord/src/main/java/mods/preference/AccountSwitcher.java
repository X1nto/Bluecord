package mods.preference;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.util.AttributeSet;
import androidx.appcompat.app.AppCompatActivity;
import com.discord.models.domain.ModelAuditLogEntry;
import com.discord.models.user.MeUser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import mods.DiscordTools;
import mods.activity.BlueSettingsActivity;
import mods.utils.StoreUtils;
import mods.utils.StringUtils;
import mods.utils.ToastUtil;
import org.json.JSONException;
import org.json.JSONObject;
public class AccountSwitcher extends Preference {

    static class AccountBackup {
        private static final String VERSION_KEY = "v1";
        private final String accountId;
        private final String accountName;
        private final long backupTime;
        private final boolean mfa;
        private final String token;

        public AccountBackup(String str, String str2, long j, String str3, boolean z2) {
            this.accountId = str;
            this.accountName = str2;
            this.backupTime = j;
            this.token = str3;
            this.mfa = z2;
        }

        public static void clearBackups(Context context) {
            getBackupPrefs(context).edit().clear().apply();
        }

        private static SharedPreferences getBackupPrefs(Context context) {
            return context.getSharedPreferences("AccountBackups", 0);
        }

        public static ArrayList<AccountBackup> getBackups(Context context) {
            SharedPreferences backupPrefs = getBackupPrefs(context);
            ArrayList<AccountBackup> arrayList = new ArrayList<>();
            for (var str : backupPrefs.getStringSet(VERSION_KEY, new HashSet())) {
                AccountBackup parse = parse((String) str);
                if (parse != null) {
                    arrayList.add(parse);
                }
            }
            Collections.sort(arrayList, $$Lambda$AccountSwitcher$AccountBackup$v0VH8SxlQeubkIpXHvRhGX824y0.INSTANCE);
            return arrayList;
        }

        static /* synthetic */ int lambda$getBackups$0(AccountBackup accountBackup, AccountBackup accountBackup2) {
            return accountBackup.getAccountName().compareTo(accountBackup2.getAccountName());
        }

        private static AccountBackup parse(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                return new AccountBackup(jSONObject.optString(ModelAuditLogEntry.CHANGE_KEY_ID), jSONObject.optString(ModelAuditLogEntry.CHANGE_KEY_NAME), jSONObject.optLong("backup_time", -1), jSONObject.optString("token"), jSONObject.optBoolean("mfa", false));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public void addBackup(Context context) {
            deleteBackup(context);
            HashSet hashSet = new HashSet(getBackupPrefs(context).getStringSet(VERSION_KEY, new HashSet()));
            hashSet.add(toString());
            getBackupPrefs(context).edit().putStringSet(VERSION_KEY, hashSet).commit();
        }

        public void deleteBackup(Context context) {
            HashSet hashSet = new HashSet(getBackupPrefs(context).getStringSet(VERSION_KEY, new HashSet()));
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                AccountBackup parse = parse((String) it.next());
                if (!(parse == null || parse.getAccountId() == null || !parse.getAccountId().equals(getAccountId()))) {
                    it.remove();
                }
            }
            getBackupPrefs(context).edit().putStringSet(VERSION_KEY, hashSet).commit();
        }

        public String getAccountId() {
            return this.accountId;
        }

        public String getAccountName() {
            return this.accountName;
        }

        public long getBackupTime() {
            return this.backupTime;
        }

        public String getToken() {
            return this.token;
        }

        public boolean isMfaEnabled() {
            return this.mfa;
        }

        public String toString() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(ModelAuditLogEntry.CHANGE_KEY_ID, getAccountId()).put(ModelAuditLogEntry.CHANGE_KEY_NAME, getAccountName()).put("backup_time", getBackupTime()).put("token", getToken()).put("mfa", isMfaEnabled());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONObject.toString();
        }
    }

    public AccountSwitcher(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnPreferenceClickListener(new $$Lambda$AccountSwitcher$OZiAu8LAN26VJD_XsJL_0FXfjPk(this, context));
    }

    private void createBackup(Context context) {
        MeUser self = StoreUtils.getSelf();
        String string = Prefs.getString("STORE_AUTHED_TOKEN", null);
        if (self == null || string == null) {
            DiscordTools.basicAlert(context, "Error", "Somehow, your account information cannot be found. Try restarting the app.");
        } else if (self.getMfaEnabled()) {
            DiscordTools.basicAlert(context, "Account Switcher", "Unfortunately, backing up accounts with Two-Factor Authentication enabled is not possible at the moment, as Discord revokes the token every few minutes.\n\nPlease do not disable 2FA for this, account security is always more important.");
        } else {
            String usernameWithDiscriminator = StringUtils.getUsernameWithDiscriminator(self);
            AlertDialog.Builder title = DiscordTools.newBuilder(context).setTitle("Are you sure?");
            title.setMessage("Are you sure you want to backup " + usernameWithDiscriminator + "?\n\nNOTE: This saves your account name and token in a private application file just like Discord does, meaning it cannot be accessed without root or a PC. Your token is secure in the app, and never will never be shared outside the app or sent anywhere. Note while it does not store your real password, your token can be used to get full access to your account, so do not share it!\n\nAlso note that changing your password will make this backup no longer work.").setNegativeButton("No", (DialogInterface.OnClickListener) null).setPositiveButton("Yes", new $$Lambda$AccountSwitcher$SVONS3r_xhzFcLNn3TwITXjLtjA(self, usernameWithDiscriminator, string, context)).show();
        }
    }

    private void deleteBackups(Context context) {
        ArrayList<AccountBackup> backups = AccountBackup.getBackups(context);
        if (backups.isEmpty()) {
            DiscordTools.basicAlert(context, "Account Switcher", "There are no backups to delete.");
            return;
        }
        CharSequence[] charSequenceArr = new CharSequence[backups.size()];
        for (int i = 0; i < backups.size(); i++) {
            AccountBackup accountBackup = backups.get(i);
            charSequenceArr[i] = StringUtils.fixAccountName(accountBackup.getAccountName()) + "\n(backed up at " + DiscordTools.formatDate(accountBackup.getBackupTime()) + ")";
        }
        boolean[] zArr = new boolean[backups.size()];
        DiscordTools.newBuilder(context).setTitle("Select backups to delete").setMultiChoiceItems(charSequenceArr, zArr, new $$Lambda$AccountSwitcher$HGYhDp8j7P_qU90zaYC8Kt98(zArr)).setNeutralButton("Delete All", new $$Lambda$AccountSwitcher$eTEWcjFmm_gtaAOG9f1zQ68_T14(context)).setNegativeButton("Exit", (DialogInterface.OnClickListener) null).setPositiveButton("Confirm", new $$Lambda$AccountSwitcher$xO_oX9Qr5TfxBx1JzzzKRJ0tIv4(zArr, backups, context)).show();
    }

    static /* synthetic */ void lambda$createBackup$5(MeUser meUser, String str, String str2, Context context, DialogInterface dialogInterface, int i) {
        new AccountBackup(String.valueOf(meUser.getId()), str, System.currentTimeMillis(), str2, meUser.getMfaEnabled()).addBackup(context);
        ToastUtil.customToast(BlueSettingsActivity.getPreferenceActivity(), "Backup saved");
    }

    static /* synthetic */ void lambda$deleteBackups$2(boolean[] zArr, DialogInterface dialogInterface, int i, boolean z2) {
        zArr[i] = z2;
    }

    static /* synthetic */ void lambda$deleteBackups$3(Context context, DialogInterface dialogInterface, int i) {
        AccountBackup.clearBackups(context);
        ToastUtil.customToast(BlueSettingsActivity.getPreferenceActivity(), "Backups cleared");
    }

    static /* synthetic */ void lambda$deleteBackups$4(boolean[] zArr, ArrayList arrayList, Context context, DialogInterface dialogInterface, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < zArr.length; i3++) {
            if (zArr[i3]) {
                ((AccountBackup) arrayList.get(i3)).deleteBackup(context);
                i2++;
            }
        }
        if (i2 == 0) {
            ToastUtil.customToast(BlueSettingsActivity.getPreferenceActivity(), "No backups selected");
            return;
        }
        AppCompatActivity preferenceActivity = BlueSettingsActivity.getPreferenceActivity();
        StringBuilder sb = new StringBuilder();
        sb.append(i2);
        sb.append(" ");
        sb.append(i2 == 1 ? "backup" : "backups");
        sb.append(" deleted.");
        ToastUtil.customToast(preferenceActivity, sb.toString());
    }

    static /* synthetic */ void lambda$restoreBackup$6(Context context, AccountBackup accountBackup, DialogInterface dialogInterface, int i) {
        DiscordTools.restoreToken(context, accountBackup.getToken(), true);
    }

    static /* synthetic */ void lambda$restoreBackup$7(ArrayList arrayList, Context context, DialogInterface dialogInterface, int i) {
        AccountBackup accountBackup = (AccountBackup) arrayList.get(i);
        AlertDialog.Builder title = DiscordTools.newBuilder(context).setTitle("Are you sure?");
        title.setMessage("Are you sure you want to restore to " + accountBackup.getAccountName() + "?\n\nThis will log you out of your current account and restart the app.").setNegativeButton("No", (DialogInterface.OnClickListener) null).setPositiveButton("Yes", new $$Lambda$AccountSwitcher$7YCC2IgPDhtRFoJ8f9RD84am7SY(context, accountBackup)).show();
    }

    public static void restoreBackup(Context context) {
        ArrayList<AccountBackup> backups = AccountBackup.getBackups(context);
        if (backups.isEmpty()) {
            DiscordTools.basicAlert(context, "Account Switcher", "There are no backups to restore from.");
            return;
        }
        CharSequence[] charSequenceArr = new CharSequence[backups.size()];
        for (int i = 0; i < backups.size(); i++) {
            AccountBackup accountBackup = backups.get(i);
            charSequenceArr[i] = accountBackup.getAccountName() + "\n(backed up at " + DiscordTools.formatDate(accountBackup.getBackupTime()) + ")";
        }
        DiscordTools.newBuilder(context).setTitle("Select an account").setItems(charSequenceArr, new $$Lambda$AccountSwitcher$ITHgQGoXBjfv3X_yE0V38rvSl4(backups, context)).setPositiveButton("Exit", (DialogInterface.OnClickListener) null).show();
    }

    public /* synthetic */ void lambda$new$0$AccountSwitcher(Context context, DialogInterface dialogInterface, int i) {
        if (i == 0) {
            createBackup(context);
        } else if (i == 1) {
            restoreBackup(context);
        } else if (i == 2) {
            deleteBackups(context);
        }
    }

    public /* synthetic */ boolean lambda$new$1$AccountSwitcher(Context context, Preference preference) {
        DiscordTools.newBuilder(context).setTitle("Pick an option").setItems(new String[]{"Backup", "Restore", "Delete Backups"}, new $$Lambda$AccountSwitcher$XDMy8LxQ1rqXcQVKcOOcrR8g0LI(this, context)).setPositiveButton("Exit", (DialogInterface.OnClickListener) null).show();
        return true;
    }
}

package mods.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.bluecord.R;
import java.util.Objects;
import mods.ThemingTools;
import mods.anti.AntiDiscordRebrand;
import mods.constants.Constants;
import mods.constants.PreferenceKeys;
import mods.preference.Prefs;
import mods.utils.PermissionUtils;
import mods.utils.ToastUtil;
public class BlueSettingsActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static AppCompatActivity activity;
    private static boolean needsActivityRefresh;
    public static boolean needsFragmentRefresh;

    public static AppCompatActivity getPreferenceActivity() {
        return activity;
    }

    @SuppressLint("ResourceType")
    public static void init(Fragment fragment) {
        View findViewById;
        View view = fragment.getView();
        if (view != null && (findViewById = view.findViewById(16908308)) != null) {
            findViewById.setOnClickListener(new $$Lambda$BlueSettingsActivity$YGxHykXO39dmm226mV2rTsdiRU(fragment));
        }
    }

    static /* synthetic */ void lambda$init$0(Fragment fragment, View view) {
        fragment.startActivity(new Intent(fragment.getActivity(), BlueSettingsActivity.class));
    }

    public static void refreshIfNeeded(Activity activity2) {
        if (needsActivityRefresh) {
            needsActivityRefresh = false;
            ThemingTools.init(activity2, true);
            Handler handler = new Handler(Looper.getMainLooper());
            Objects.requireNonNull(activity2);
            handler.post(new $$Lambda$p9UeKBysYU6UI3zESHP681Bv18A(activity2));
        }
    }

    public String getPathFromResult(Intent intent, int i) {
        String str = null;
        if (i != -1) {
            return null;
        }
        try {
            String[] strArr = {"_data"};
            Cursor query = getContentResolver().query(intent.getData(), strArr, null, null, null);
            query.moveToFirst();
            str = query.getString(query.getColumnIndex(strArr[0]));
            query.close();
            return str;
        } catch (Exception e) {
            try {
                e.printStackTrace();
                return intent.getData().getPath();
            } catch (Exception e2) {
                e2.printStackTrace();
                return str;
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        String pathFromResult = getPathFromResult(intent, i2);
        if (pathFromResult != null && i == 10) {
            Prefs.setString(PreferenceKeys.BACKGROUND_PATH, pathFromResult);
            ToastUtil.customToast(this, "Background changed successfully");
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @SuppressLint("ResourceType")
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTheme(AntiDiscordRebrand.isEnabled() ? Constants.BLUE_STYLE_PREFS_NO_REBRAND : Constants.PrefsTheme);
        Prefs.getPreferences().registerOnSharedPreferenceChangeListener(this);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setBackgroundColor(Color.parseColor("#ff1f2235"));
        setContentView(linearLayout);
        needsActivityRefresh = false;
        needsFragmentRefresh = false;
        activity = this;
        BlueSettingsFragment blueSettingsFragment = new BlueSettingsFragment();
        blueSettingsFragment.setArguments(getIntent().getExtras());
        getFragmentManager().beginTransaction().replace(16908290, blueSettingsFragment).commit();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        activity = this;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        activity = this;
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        needsActivityRefresh = true;
        needsFragmentRefresh = true;
        if (PermissionUtils.needsPermissionForKey(str) && !PermissionUtils.hasStoragePermission(this)) {
            ToastUtil.customToast(activity, "Storage permissions are needed to use this feature!");
        }
    }
}

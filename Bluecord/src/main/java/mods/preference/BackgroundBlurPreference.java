package mods.preference;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.preference.Preference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import mods.DiscordTools;
import mods.activity.BlueSettingsActivity;
import mods.constants.PreferenceKeys;
import mods.utils.PermissionUtils;
import mods.utils.ToastUtil;
public class BackgroundBlurPreference extends Preference {

    /* renamed from: mods.preference.BackgroundBlurPreference$1  reason: invalid class name */
    class AnonymousClass1 implements SeekBar.OnSeekBarChangeListener {
        final /* synthetic */ TextView val$tv;

        AnonymousClass1(TextView textView) {
            this.val$tv = textView;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z2) {
            BackgroundBlurPreference.access$000(BackgroundBlurPreference.this, this.val$tv, i);
            if (z2) {
                Log.e("BGBlur", "newValue = " + (((float) i) / 4.0f));
                Prefs.setFloat(PreferenceKeys.BACKGROUND_BLUR_LEVEL, ((float) i) / 4.0f);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    public BackgroundBlurPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnPreferenceClickListener(new $$Lambda$BackgroundBlurPreference$U2TArNy7xYSEN7VIRJd8x1Vn6M(this));
    }

    static /* synthetic */ void access$000(BackgroundBlurPreference backgroundBlurPreference, TextView textView, int i) {
        backgroundBlurPreference.setTv(textView, i);
    }

    private void setTv(TextView textView, int i) {
        textView.setText("Blur Level: " + i + "%");
    }

    private void setVisible(View view, View view2, boolean z2) {
        int i = 0;
        view.setVisibility(z2 ? View.VISIBLE : View.GONE);
        if (!z2) {
            i = 8;
        }
        view2.setVisibility(i);
    }

    public /* synthetic */ void lambda$new$0$BackgroundBlurPreference(SeekBar seekBar, TextView textView, CompoundButton compoundButton, boolean z2) {
        Prefs.setBoolean(PreferenceKeys.BACKGROUND_BLUR, z2);
        setVisible(seekBar, textView, z2);
    }

    public /* synthetic */ boolean lambda$new$1$BackgroundBlurPreference(Preference preference) {
        if (PermissionUtils.hasStoragePermission()) {
            if (!Prefs.getBoolean(PreferenceKeys.BACKGROUND_ENABLED, false)) {
                ToastUtil.customToast(BlueSettingsActivity.getPreferenceActivity(), "Please enable the custom background option first");
                return true;
            }
            Context context = preference.getContext();
            ScrollView scrollView = new ScrollView(context);
            scrollView.setFillViewport(true);
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            TextView textView = new TextView(context);
            textView.setTextColor(Color.parseColor("#ff26beff"));
            textView.setTextSize(16.0f);
            textView.setGravity(17);
            float f = Prefs.getFloat(PreferenceKeys.BACKGROUND_BLUR_LEVEL, 12.5f);
            setTv(textView, ((int) f) * 4);
            SeekBar seekBar = new SeekBar(context);
            seekBar.setMax(100);
            seekBar.setProgress(((int) f) * 4);
            seekBar.setOnSeekBarChangeListener(new AnonymousClass1(textView));
            boolean z2 = Prefs.getBoolean(PreferenceKeys.BACKGROUND_BLUR, false);
            CheckBox checkBox = new CheckBox(context);
            checkBox.setText("Enable");
            checkBox.setChecked(z2);
            checkBox.setOnCheckedChangeListener(new $$Lambda$BackgroundBlurPreference$3qsQCcvrRmJwe84gMdspjOoZYbo(this, seekBar, textView));
            setVisible(seekBar, textView, z2);
            linearLayout.addView(checkBox);
            linearLayout.addView(textView);
            linearLayout.addView(seekBar);
            scrollView.addView(linearLayout);
            DiscordTools.newBuilder(context).setTitle("Background Blur").setView(scrollView).setPositiveButton("Exit", (DialogInterface.OnClickListener) null).show();
        }
        return true;
    }
}

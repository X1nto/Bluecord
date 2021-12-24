package mods.preference;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.preference.Preference;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import mods.DiscordTools;
import mods.constants.PreferenceKeys;
public class RainbowTextPreference extends Preference {

    /* renamed from: mods.preference.RainbowTextPreference$1  reason: invalid class name */
    class AnonymousClass1 implements SeekBar.OnSeekBarChangeListener {
        final /* synthetic */ TextView val$tv;

        AnonymousClass1(TextView textView) {
            this.val$tv = textView;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z2) {
            RainbowTextPreference.access$000(RainbowTextPreference.this, this.val$tv, i);
            if (z2) {
                Prefs.setInt(PreferenceKeys.RAINBOW_CYCLE_SPEED, i);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    public RainbowTextPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnPreferenceClickListener(new $$Lambda$RainbowTextPreference$qnm0tfEa_CDIWjcUytMq0zu1o2s(this));
    }

    static /* synthetic */ void access$000(RainbowTextPreference rainbowTextPreference, TextView textView, int i) {
        rainbowTextPreference.setTv(textView, i);
    }

    static /* synthetic */ void lambda$new$0(CompoundButton compoundButton, boolean z2) {
        Prefs.setBoolean(PreferenceKeys.COLOR_ANIMATE_TYPING, z2);
    }

    static /* synthetic */ void lambda$new$1(CompoundButton compoundButton, boolean z2) {
        Prefs.setBoolean(PreferenceKeys.COLOR_ANIMATE_MESSAGE, z2);
    }

    private void setTv(TextView textView, int i) {
        String str;
        if (i == 0) {
            str = "Slowest";
        } else if (i == 1) {
            str = "Slow";
        } else if (i == 2) {
            str = "Normal";
        } else if (i == 3) {
            str = "Fast";
        } else if (i != 4) {
            Log.e("RainbowText", "invalid = " + i);
            return;
        } else {
            str = "Fastest";
        }
        textView.setText("Animate Speed: " + str);
    }

    public /* synthetic */ boolean lambda$new$2$RainbowTextPreference(Preference preference) {
        Context context = preference.getContext();
        ScrollView scrollView = new ScrollView(context);
        scrollView.setFillViewport(true);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        CheckBox checkBox = new CheckBox(context);
        checkBox.setText("Rainbow Typing Box");
        checkBox.setChecked(Prefs.getBoolean(PreferenceKeys.COLOR_ANIMATE_TYPING, false));
        checkBox.setOnCheckedChangeListener($$Lambda$RainbowTextPreference$LBdqYoaifw6axQzXxsyWoryZ6K4.INSTANCE);
        linearLayout.addView(checkBox);
        CheckBox checkBox2 = new CheckBox(context);
        checkBox2.setText("Rainbow Text Messages");
        checkBox2.setChecked(Prefs.getBoolean(PreferenceKeys.COLOR_ANIMATE_MESSAGE, false));
        checkBox2.setOnCheckedChangeListener($$Lambda$RainbowTextPreference$iTf3pxTf1H5PTcbnLJHw2OG9xjc.INSTANCE);
        linearLayout.addView(checkBox2);
        TextView textView = new TextView(context);
        textView.setTextColor(Color.parseColor("#ff26beff"));
        textView.setTextSize(16.0f);
        textView.setGravity(17);
        linearLayout.addView(textView);
        int i = Prefs.getInt(PreferenceKeys.RAINBOW_CYCLE_SPEED, 2);
        setTv(textView, i);
        SeekBar seekBar = new SeekBar(context);
        seekBar.setMax(4);
        seekBar.setProgress(i);
        seekBar.setOnSeekBarChangeListener(new AnonymousClass1(textView));
        linearLayout.addView(seekBar);
        scrollView.addView(linearLayout);
        DiscordTools.newBuilder(context).setTitle("Rainbow Color Options").setView(scrollView).setPositiveButton("Exit", (DialogInterface.OnClickListener) null).show();
        return true;
    }
}

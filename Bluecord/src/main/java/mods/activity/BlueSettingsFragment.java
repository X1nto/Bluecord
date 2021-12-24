package mods.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bluecord.R;

import mods.constants.Constants;
import mods.view.ActionBar;
import mods.view.Colors;
public class BlueSettingsFragment extends PreferenceFragment {
    public /* synthetic */ void lambda$onViewCreated$0$BlueSettingsFragment(View view) {
        getActivity().finish();
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i = 0;
        if (getArguments() != null) {
            i = getArguments().getInt("pref", 0);
        }
        addPreferencesFromResource(i == 0 ? Constants.prefs_base : i);
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (view != null && view.getParent() != null) {
            view.setBackgroundColor(Colors.getThemeBackground());
            LinearLayout linearLayout = new LinearLayout(getActivity());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            ActionBar actionBar = new ActionBar(getActivity(), getPreferenceScreen().getTitle().toString(), null);
            actionBar.setTitleTextColor(-1);
            ImageView backButtonView = actionBar.getBackButtonView();
            actionBar.setBackgroundColor(Color.parseColor("#ff2f3136"));
            backButtonView.setColorFilter(-1);
            linearLayout.addView(actionBar);
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            viewGroup.removeView(view);
            linearLayout.addView(view);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
            viewGroup.addView(linearLayout);
            backButtonView.setOnClickListener(new $$Lambda$BlueSettingsFragment$exd9yXAo9X_U_FlXd4DToqt2Fwc(this));
        }
    }
}

package mods.preference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import mods.activity.BlueSettingsActivity;
import mods.constants.Constants;

public class Tab extends Preference {
    private final int prefIcon;
    private final int prefXml;

    @SuppressLint("WrongConstant")
    public Tab(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutResource(Constants.blue_preference_layout_tab);
        String attributeValue = attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "title");
        switch (attributeValue) {
            case "Theme":
                prefXml = Constants.prefs_theme;
                prefIcon = Constants.PREFS_THEME_ICON;
                break;
            case "Chat":
                prefXml = Constants.prefs_chat;
                prefIcon = Constants.PREFS_CHAT_ICON;
                break;
            case "Update + Developer Info":
                prefXml = Constants.prefs_info;
                prefIcon = Constants.bluecord_logo_big;
                break;
            default:
                throw new IllegalArgumentException("unknown tab title in prefs_base.xml");
        }
        setOnPreferenceClickListener(a -> {
            getContext().startActivity(new Intent(getContext(), BlueSettingsActivity.class).putExtra("pref", prefXml).addFlags(268435456));
            return true;
        });
    }

    @Override // android.preference.Preference
    protected void onBindView(View view) {
        super.onBindView(view);
        @SuppressLint("ResourceType") ImageView imageView = (ImageView) view.findViewById(16908295);
        imageView.setImageResource(this.prefIcon);
        imageView.setVisibility(View.VISIBLE);
    }
}

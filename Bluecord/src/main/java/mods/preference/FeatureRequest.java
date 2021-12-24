package mods.preference;

import android.content.Context;
import android.content.DialogInterface;
import android.preference.Preference;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.discord.models.user.MeUser;
import mods.DiscordTools;
import mods.activity.BlueSettingsActivity;
import mods.constants.URLConstants;
import mods.net.Net;
import mods.utils.StoreUtils;
import mods.utils.StringUtils;
import mods.utils.ToastUtil;
import org.json.JSONObject;
public class FeatureRequest extends Preference {
    private static final String ERROR_MESSAGE = "Error submitting your request.\n\nThe request was copied to your clipboard. Please check your Internet connection and try again";

    public FeatureRequest(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnPreferenceClickListener(new $$Lambda$FeatureRequest$AyHlU3w7mYIR69VjJHjaVpUgeug(context));
    }

    static /* synthetic */ void lambda$new$0(EditText editText, CheckBox checkBox, Preference preference, DialogInterface dialogInterface, int i) {
        StringBuilder sb = new StringBuilder(editText.getText().toString().trim());
        if (checkBox.isChecked()) {
            MeUser self = StoreUtils.getSelf();
            sb.append("\n\nFrom: " + StringUtils.getUsernameWithDiscriminator(self));
        }
        String sb2 = sb.toString();
        if (sb2.isEmpty()) {
            ToastUtil.customToast(BlueSettingsActivity.getPreferenceActivity(), "Please write something!");
            return;
        }
        String asyncRequest = Net.asyncRequest(URLConstants.phpLink() + "?feature", sb2);
        if (asyncRequest == null) {
            DiscordTools.copyToClipboard(sb2);
            DiscordTools.basicAlert(preference.getContext(), "Error", ERROR_MESSAGE);
            return;
        }
        try {
            DiscordTools.basicAlert(preference.getContext(), "Success", new JSONObject(asyncRequest).getString("message"));
        } catch (Exception e) {
            e.printStackTrace();
            DiscordTools.basicAlert(preference.getContext(), "Error", ERROR_MESSAGE);
        }
    }

    static /* synthetic */ boolean lambda$new$1(Context context, Preference preference) {
        ScrollView scrollView = new ScrollView(preference.getContext());
        scrollView.setFillViewport(true);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        EditText editText = new EditText(preference.getContext());
        editText.setHint("Write a suggestion...");
        editText.setInputType(147457);
        linearLayout.addView(editText);
        CheckBox checkBox = new CheckBox(preference.getContext());
        checkBox.setText("Include Username");
        checkBox.setChecked(false);
        linearLayout.addView(checkBox);
        scrollView.addView(linearLayout);
        DiscordTools.newBuilder(preference.getContext()).setTitle("Feature Request").setView(scrollView).setNegativeButton("Discard", (DialogInterface.OnClickListener) null).setPositiveButton("Send", new $$Lambda$FeatureRequest$wPpiKEB1CWCK0rHfZHkIepowXk(editText, checkBox, preference)).show();
        return true;
    }
}

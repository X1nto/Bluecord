package mods.utils.translate;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.discord.models.message.Message;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import mods.DiscordTools;
import mods.ThemingTools;
import mods.constants.PreferenceKeys;
import mods.preference.Prefs;
import mods.utils.StringUtils;
import mods.utils.ToastUtil;

@SuppressLint("SetTextI18n")
public class Translate {
    private static final String TAG = "Translate";
    private static final LinkedHashMap<String, String> choices;

    /* renamed from: mods.utils.translate.Translate$1  reason: invalid class name */
    static class AnonymousClass1 implements ITranslateCallback {
        final /* synthetic */ Activity val$activity;

        AnonymousClass1(Activity activity) {
            this.val$activity = activity;
        }

        static /* synthetic */ void lambda$onResult$0(String str, DialogInterface dialogInterface, int i) {
            DiscordTools.copyToClipboard(str);
            ToastUtil.toast("Copied to clipboard");
        }

        static /* synthetic */ void lambda$onResult$1(Activity activity, String str) {
            DiscordTools.newBuilder(activity).setTitle("Translate Result").setMessage(str).setNeutralButton("Copy", new $$Lambda$Translate$1$jLjXGOoxFRAiQeCft08vvXOcRLw(str)).setPositiveButton("Exit", (DialogInterface.OnClickListener) null).show();
        }

        @Override // mods.utils.translate.ITranslateCallback
        public void onError() {
            DiscordTools.basicAlert(this.val$activity, "Error", "Either your Internet connection is not working or the API is down. Check your connection and retry.");
        }

        @Override // mods.utils.translate.ITranslateCallback
        public void onResult(String str) {
            Activity activity = this.val$activity;
            activity.runOnUiThread(new $$Lambda$Translate$1$hIpYzBg8pQcjUc2g8sO9eK2fpyw(activity, str));
        }
    }

    static {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        choices = linkedHashMap;
        linkedHashMap.put("English", "en");
        linkedHashMap.put("Spanish", "es");
        linkedHashMap.put("Arabic", "ar");
        linkedHashMap.put("Chinese", "zh");
        linkedHashMap.put("French", "fr");
        linkedHashMap.put("German", "de");
        linkedHashMap.put("Hindi", "hi");
        linkedHashMap.put("Irish", "ga");
        linkedHashMap.put("Italian", "it");
        linkedHashMap.put("Japanese", "ja");
        linkedHashMap.put("Korean", "ko");
        linkedHashMap.put("Portuguese", "pt");
        linkedHashMap.put("Russian", "ru");
    }

    static /* synthetic */ void lambda$showTranslateDialog$0(EditText editText, SimpleSpinner simpleSpinner, Activity activity, DialogInterface dialogInterface, int i) {
        String trim = editText.getText().toString().trim();
        Log.e(TAG, "text: '" + trim + "'");
        StringBuilder sb = new StringBuilder();
        sb.append("fromSpinner: ");
        sb.append(simpleSpinner);
        Log.e(TAG, sb.toString());
        if (trim.isEmpty()) {
            ToastUtil.toast("You cannot translate empty text!");
        } else {
            TranslateAPI.translate(activity, choices.get(simpleSpinner.getSelectedKey()), trim, new AnonymousClass1(activity));
        }
    }

    static /* synthetic */ void lambda$showTranslateDialog$1(Activity activity, String str) {
        ScrollView scrollView = new ScrollView(activity);
        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        int dipToPx = ThemingTools.dipToPx(8.0f);
        TextView textView = new TextView(activity);
        textView.setText("Translate to:");
        textView.setTextSize(14.0f);
        textView.setTextColor(Color.parseColor("#ff26beff"));
        textView.setGravity(8388627);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        textView.setPadding(dipToPx, dipToPx, dipToPx, dipToPx);
        linearLayout.addView(textView);
        LinkedHashMap<String, String> linkedHashMap = choices;
        String str2 = (String) new ArrayList(linkedHashMap.keySet()).get(0);
        SimpleSpinner createTranslateSpinner = SimpleSpinner.createTranslateSpinner(activity, linkedHashMap, Prefs.containsKey(PreferenceKeys.DEFAULT_TRANSLATE_FROM_KEY) ? Prefs.getString(PreferenceKeys.DEFAULT_TRANSLATE_FROM_KEY, str2) : str2);
        linearLayout.addView(createTranslateSpinner.getSpinner());
        EditText editText = new EditText(activity);
        editText.setHint("Text to translate");
        editText.setTextColor(Color.WHITE);
        editText.setHintTextColor(Color.parseColor("#cccccc"));
        editText.setEms(10);
        editText.setInputType(180224);
        if (!StringUtils.isEmpty(str)) {
            editText.setText(str);
        }
        linearLayout.addView(editText);
        scrollView.addView(linearLayout);
        DiscordTools.newBuilder(activity).setTitle(TAG).setView(scrollView).setNegativeButton("Exit", (DialogInterface.OnClickListener) null).setPositiveButton(TAG, new $$Lambda$Translate$EtgKOZa_m0IG6UpYE7jBjBmYKEw(editText, createTranslateSpinner, activity)).show();
    }

    public static void showTranslateDialog(Activity activity, Message message) {
        if (StringUtils.isEmpty(message.getContent())) {
            ToastUtil.toast("You can only translate text messages");
        } else {
            showTranslateDialog(activity, message.getContent().trim());
        }
    }

    public static void showTranslateDialog(Activity activity, String str) {
        if (activity == null) {
            ToastUtil.toast("Failed to open the translate options. Restart and try again.");
        } else {
            activity.runOnUiThread(new $$Lambda$Translate$FrBjDzXyxkWbqFjKowalyQFzs(activity, str));
        }
    }
}

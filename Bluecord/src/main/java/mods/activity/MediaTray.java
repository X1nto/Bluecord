package mods.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.bluecord.R;
import com.discord.widgets.chat.input.autocomplete.AutocompleteViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import mods.DiscordTools;
import mods.ThemingTools;
import mods.constants.*;
import mods.net.Urban;
import mods.preference.Prefs;
import mods.utils.PatternUtils;
import mods.utils.StringUtils;
import mods.utils.ToastUtil;

public class MediaTray {
    public static MediaTray inst;
    private static final AtomicBoolean shouldAddSpoiler = new AtomicBoolean(false);
    private ArrayList<String> commandsList;
    private ArrayList<String> commandsSummary;
    private final int height;
    private String input;
    private final Fragment mFragment;
    private ListView mediaTrayList;
    private final View mediaTrayView;
    private String prefix;
    private final int themedTextColor;

    /* renamed from: mods.activity.MediaTray$1  reason: invalid class name */
    class AnonymousClass1 extends ArrayAdapter {
        final /* synthetic */ int val$size;

        AnonymousClass1(Context context, int i, int i2, List list, int i3) {
            super(context, i, i2, list);
            this.val$size = i3;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        @SuppressLint("ResourceType")
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2 = super.getView(i, view, viewGroup);
            TextView textView = (TextView) view2.findViewById(16908308);
            TextView textView2 = (TextView) view2.findViewById(16908309);
            textView.setText((CharSequence) MediaTray.access$000(MediaTray.this).get(i));
            textView.setTextColor(MediaTray.access$100(MediaTray.this));
            textView.setTextSize(16.0f);
            textView2.setText(i >= this.val$size ? "custom command" : (String) MediaTray.access$200(MediaTray.this).get(i));
            textView2.setTextColor(MediaTray.access$100(MediaTray.this));
            textView2.setTextSize(12.0f);
            view2.setScrollContainer(true);
            view2.setScrollbarFadingEnabled(true);
            return view2;
        }
    }

    private MediaTray(Fragment fragment, View view) {
        this.mFragment = fragment;
        this.mediaTrayView = view;
        this.height = view.getHeight();
        String string = Prefs.getString("commands.prefix", "!");
        this.prefix = string;
        if (string.equals(AutocompleteViewModel.COMMAND_DISCOVER_TOKEN)) {
            this.prefix = "!";
            Prefs.setString("commands.prefix", "!");
            DiscordTools.basicAlert(fragment.getContext(), "Commands", "Since slash commands are now used for internal commands and Discord bots, it is no longer available for use as a prefix.\n\nYour commands prefix has been reset to ! (the exclamation point)");
        }
        this.themedTextColor = ThemingTools.isDarkModeOn() ? Color.parseColor("#ffffff") : Color.parseColor("#000000");
    }

    static /* synthetic */ ArrayList access$000(MediaTray mediaTray) {
        return mediaTray.commandsList;
    }

    static /* synthetic */ int access$100(MediaTray mediaTray) {
        return mediaTray.themedTextColor;
    }

    static /* synthetic */ ArrayList access$200(MediaTray mediaTray) {
        return mediaTray.commandsSummary;
    }

    private void addCommand(String str, String str2) {
        FragmentActivity activity = this.mFragment.getActivity();
        if (activity == null) {
            ToastUtil.toast("Something went wrong.");
        } else {
            activity.runOnUiThread(new $$Lambda$MediaTray$izH_aZ6BE_ifAIWKDhujoGrH0JM(this, activity, str, str2));
        }
    }

    private void addCustomCommands(ArrayList<String> arrayList) {
        Map<String, ?> all = DiscordTools.getContext().getSharedPreferences("CustomCommands", 0).getAll();
        int size = all.size();
        if (size > 0) {
            Object[] array = all.keySet().toArray();
            for (int i = 0; i < size; i++) {
                arrayList.add(ensurePrefix(array[i].toString()));
            }
        }
    }

    private void addIfTyped(String str, String str2) {
        if (ensurePrefix(str).startsWith(ensurePrefix(this.input))) {
            this.commandsList.add(ensurePrefix(str));
            this.commandsSummary.add(str2);
        }
    }

    private String customComs(String str) {
        String string = DiscordTools.getContext().getSharedPreferences("CustomCommands", 0).getString(removePrefix(str), null);
        return string != null ? string : str;
    }

    private void deleteCommand() {
        SharedPreferences sharedPreferences = DiscordTools.getContext().getSharedPreferences("CustomCommands", 0);
        Map<String, ?> all = sharedPreferences.getAll();
        int size = all.size();
        if (size == 0) {
            ToastUtil.toast("There are no commands to delete");
            return;
        }
        CharSequence[] charSequenceArr = new CharSequence[size];
        boolean[] zArr = new boolean[size];
        Object[] array = all.keySet().toArray();
        for (int i = 0; i < size; i++) {
            charSequenceArr[i] = ensurePrefix(array[i].toString());
            zArr[i] = false;
        }
        if (this.mFragment.getActivity() == null) {
            ToastUtil.toast("Something went wrong.");
        } else {
            this.mFragment.getActivity().runOnUiThread(new $$Lambda$MediaTray$j2WdHXyyZNqXbr4usBhOYeJWqoo(this, charSequenceArr, zArr, size, sharedPreferences, array));
        }
    }

    private String ensurePrefix(String str) {
        if (str.startsWith(this.prefix)) {
            return str;
        }
        return this.prefix + str;
    }

    public static void init(Fragment fragment, View view) {
        inst = new MediaTray(fragment, view);
    }

    private boolean isBuiltInCommand(String str) {
        String ensurePrefix = ensurePrefix(str);
        return ensurePrefix.startsWith(ensurePrefix("add")) || ensurePrefix.startsWith(ensurePrefix("delete")) || ensurePrefix.startsWith(ensurePrefix("prefix ")) || ensurePrefix.startsWith(ensurePrefix("spoiler ")) || ensurePrefix.startsWith(ensurePrefix("i ")) || ensurePrefix.startsWith(ensurePrefix("uwu ")) || ensurePrefix.startsWith(ensurePrefix("owo ")) || ensurePrefix.startsWith(ensurePrefix("lower ")) || ensurePrefix.startsWith(ensurePrefix("c ")) || ensurePrefix.startsWith(ensurePrefix("b ")) || ensurePrefix.startsWith(ensurePrefix("s ")) || ensurePrefix.startsWith(ensurePrefix("u ")) || ensurePrefix.equals(ensurePrefix("update")) || ensurePrefix.startsWith(ensurePrefix("ud ")) || ensurePrefix.equals(ensurePrefix("bluecord")) || ensurePrefix.startsWith(ensurePrefix("mock ")) || ensurePrefix.equals(ensurePrefix("upper ")) || ensurePrefix.equals(ensurePrefix("reverse ")) || ensurePrefix.equals(ensurePrefix("blank")) || isExistingCustomCommand(str);
    }

    private boolean isExistingCustomCommand(String str) {
        return DiscordTools.getContext().getSharedPreferences("CustomCommands", 0).getString(removePrefix(str), null) != null;
    }

    static /* synthetic */ void lambda$deleteCommand$4(boolean[] zArr, DialogInterface dialogInterface, int i, boolean z2) {
        zArr[i] = z2;
    }

    static /* synthetic */ void lambda$deleteCommand$5(int i, boolean[] zArr, SharedPreferences sharedPreferences, Object[] objArr, DialogInterface dialogInterface, int i2) {
        boolean z2 = false;
        for (int i3 = 0; i3 < i; i3++) {
            if (zArr[i3]) {
                z2 = true;
                sharedPreferences.edit().remove(objArr[i3].toString()).apply();
            }
        }
        if (z2) {
            ToastUtil.toast("Successfully deleted");
        } else {
            ToastUtil.toast("No commands were selected to delete!");
        }
    }

    public static String modifyFileName(String str) {
        String removeAppName = PatternUtils.removeAppName(str);
        if (!shouldAddSpoiler.get() && !Prefs.getBoolean(PreferenceKeys.IMAGE_SPOILERS, false)) {
            return removeAppName;
        }
        return "SPOILER_" + removeAppName;
    }

    private String removePrefix(String str) {
        return str.startsWith(this.prefix) ? str.substring(this.prefix.length()) : str;
    }

    public static void setTrayText(CharSequence charSequence) {
        View view;
        MediaTray mediaTray = inst;
        if (mediaTray != null && mediaTray.mFragment.isVisible() && (view = inst.mediaTrayView) != null) {
            EditText editText = (EditText) view.findViewById(Constants.TEXT_INPUT);
            editText.setText(charSequence);
            editText.setSelection(editText.getText().length());
        }
    }

    public String commands(String str) {
        String str2;
        AtomicBoolean atomicBoolean = shouldAddSpoiler;
        atomicBoolean.set(false);
        if (!str.startsWith(this.prefix)) {
            return str;
        }
        String substring = str.substring(this.prefix.length());
        if (substring.startsWith("add")) {
            String trim = substring.substring(3).trim();
            String str3 = trim;
            String str4 = "";
            int indexOf = trim.indexOf(32);
            if (indexOf != -1) {
                str3 = trim.substring(0, indexOf).trim();
                str4 = trim.substring(indexOf + 1).trim();
            }
            addCommand(ensurePrefix(str3), str4);
            substring = "";
        } else if (substring.startsWith("delete")) {
            deleteCommand();
            substring = "";
        } else if (substring.startsWith("ud ")) {
            Urban.getDefinition(this.mFragment.getActivity(), substring.substring(3).trim());
            substring = "";
        } else if (substring.startsWith("b ")) {
            substring = "**" + substring.substring(2) + "**";
        } else if (substring.startsWith("c ")) {
            substring = "```\n" + substring.substring(2) + "\n```";
        } else if (substring.startsWith("i ")) {
            substring = "*" + substring.substring(2) + "*";
        } else if (substring.startsWith("u ")) {
            substring = "__" + substring.substring(2) + "__";
        } else if (substring.startsWith("s ")) {
            substring = "~~" + substring.substring(2) + "~~";
        } else if (substring.startsWith("mock ")) {
            substring = StringUtils.mock(substring.substring(5));
        } else if (substring.startsWith("upper ")) {
            substring = substring.substring(6).trim().toUpperCase(Locale.ROOT);
        } else if (substring.startsWith("lower ")) {
            substring = substring.substring(6).trim().toLowerCase(Locale.ROOT);
        } else if (substring.startsWith("reverse ")) {
            substring = new StringBuilder(substring.substring(8).trim()).reverse().toString();
        } else if (substring.startsWith("uwu ")) {
            substring = substring.substring(4).replace(" ", " ᵘʷᵘ ");
        } else if (substring.startsWith("owo ")) {
            substring = substring.substring(4).replace("l", "w").replace("L", ExifInterface.LONGITUDE_WEST).replace("o", "u").replace("O", "U").replace("r", "w").replace("R", ExifInterface.LONGITUDE_WEST).trim();
        } else if (substring.startsWith("spoiler")) {
            String trim2 = substring.substring(7).trim();
            if (StringUtils.isEmpty(trim2)) {
                str2 = "";
            } else {
                str2 = "||" + trim2 + "||";
            }
            substring = str2;
            atomicBoolean.set(true);
        } else if (substring.equals("update")) {
            this.mFragment.requireActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(URLConstants.getBaseUrl())));
        } else if (substring.equals("bluecord")) {
            FragmentActivity activity = this.mFragment.getActivity();
            if (activity != null) {
                activity.runOnUiThread(new $$Lambda$MediaTray$rCRhkKAdEIUsfkpBQyXTbBA_Q(this));
                substring = "";
            }
        } else if (substring.equals("blank")) {
            substring = "​";
        } else if (substring.startsWith("prefix ")) {
            String substring2 = substring.substring(7);
            if (this.prefix.equals(substring2)) {
                ToastUtil.toast("Prefix is the same!");
            } else if (substring2.equals("@") || substring2.equals("#") || substring2.equals(AutocompleteViewModel.COMMAND_DISCOVER_TOKEN) || substring2.length() != 1) {
                ToastUtil.toast("Prefix must be 1 character long and must not be a @, # or /");
            } else {
                this.prefix = substring2;
                Prefs.setString("commands.prefix", substring2);
                ToastUtil.toast("Prefix changed to " + substring2);
                substring = "";
            }
        } else {
            substring = customComs(str);
        }
        return substring.trim();
    }

    public /* synthetic */ void lambda$addCommand$2$MediaTray(EditText editText, EditText editText2, DialogInterface dialogInterface, int i) {
        String removePrefix = removePrefix(editText.getText().toString().toLowerCase().trim());
        String trim = editText2.getText().toString().trim();
        if (StringUtils.isEmpty(removePrefix)) {
            ToastUtil.toast("Command cannot be empty");
        } else if (StringUtils.isEmpty(trim)) {
            ToastUtil.toast("Output cannot be empty");
        } else if (isBuiltInCommand(removePrefix)) {
            ToastUtil.toast("You cannot override a built in command");
        } else {
            ToastUtil.toast(isExistingCustomCommand(removePrefix) ? "Command replaced" : "Command added");
            DiscordTools.getContext().getSharedPreferences("CustomCommands", 0).edit().putString(removePrefix, trim).apply();
        }
        addCommand("", "");
    }

    public /* synthetic */ void lambda$addCommand$3$MediaTray(FragmentActivity fragmentActivity, String str, String str2) {
        ScrollView scrollView = new ScrollView(fragmentActivity);
        scrollView.setFillViewport(true);
        LinearLayout linearLayout = new LinearLayout(fragmentActivity);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        TextView textView = new TextView(fragmentActivity);
        textView.setText("Command Name");
        textView.setTextSize(18.0f);
        textView.setTextColor(Color.parseColor("#ff26beff"));
        textView.setGravity(17);
        textView.setPadding(8, 8, 8, 8);
        linearLayout.addView(textView);
        EditText editText = new EditText(fragmentActivity);
        editText.setHint(ensurePrefix("lenny"));
        editText.setTextColor(Color.parseColor("#eeeeee"));
        editText.setHintTextColor(Color.parseColor("#cccccc"));
        editText.setEms(10);
        editText.setInputType(180224);
        linearLayout.addView(editText);
        TextView textView2 = new TextView(fragmentActivity);
        textView2.setText("Command Output");
        textView2.setTextSize(18.0f);
        textView2.setTextColor(Color.parseColor("#ff26beff"));
        textView2.setGravity(17);
        textView2.setPadding(8, 8, 8, 8);
        linearLayout.addView(textView2);
        EditText editText2 = new EditText(fragmentActivity);
        editText2.setHint("( ͡° ͜ʖ ͡°)");
        editText2.setTextColor(Color.parseColor("#eeeeee"));
        editText2.setHintTextColor(Color.parseColor("#cccccc"));
        editText2.setEms(10);
        editText2.setInputType(180224);
        linearLayout.addView(editText2);
        scrollView.addView(linearLayout);
        if (!StringUtils.isEmpty(str)) {
            editText.setText(str);
            if (!StringUtils.isEmpty(str2)) {
                editText2.setText(str2);
            }
        }
        DiscordTools.newBuilder(this.mFragment.getContext()).setView(scrollView).setTitle("Add Command").setPositiveButton("Add", new $$Lambda$MediaTray$4RiIDgPezfGvco4RT066JaifA88(this, editText, editText2)).setNegativeButton("Exit", (DialogInterface.OnClickListener) null).show();
    }

    public /* synthetic */ void lambda$commands$1$MediaTray() {
        DiscordTools.basicAlert(this.mFragment.requireActivity(), "Bluecord", "The reason I started this project was that there were no other Discord mods available for Android where you can customize all the mods to your liking with a settings UI. The goal of this mod is to bring you all the mods you like, but in a form factor that's customizable and easy to install and use with no coding or reversing required. Hope you enjoy!\n\n~Blue");
    }

    public /* synthetic */ void lambda$deleteCommand$6$MediaTray(CharSequence[] charSequenceArr, boolean[] zArr, int i, SharedPreferences sharedPreferences, Object[] objArr) {
        DiscordTools.newBuilder(this.mFragment.getContext()).setTitle("Delete Commands").setMultiChoiceItems(charSequenceArr, zArr, new $$Lambda$MediaTray$5F9XAXvOcvcRFsXJXbdys882hAc(zArr)).setNegativeButton("Exit", (DialogInterface.OnClickListener) null).setPositiveButton("Delete", new $$Lambda$MediaTray$gwleFxO9QudcpbtN8TY49P2_Kk(i, zArr, sharedPreferences, objArr)).show();
    }

    public /* synthetic */ void lambda$onTextChanged$0$MediaTray(AdapterView adapterView, View view, int i, long j) {
        EditText editText = (EditText) this.mediaTrayView.findViewById(Constants.TEXT_INPUT);
        editText.setText(ensurePrefix(this.commandsList.get(i)));
        editText.setSelection(editText.getText().length());
    }

    public void onTextChanged(Editable editable) {
        if (editable != null) {
            String obj = editable.toString();
            this.input = obj;
            if (obj.isEmpty() || !this.input.startsWith(this.prefix)) {
                ListView listView = this.mediaTrayList;
                if (listView != null && listView.getVisibility() == View.VISIBLE) {
                    this.mediaTrayList.setVisibility(View.GONE);
                    this.mediaTrayView.setMinimumHeight(this.height);
                    return;
                }
                return;
            }
            this.commandsList = new ArrayList<>();
            this.commandsSummary = new ArrayList<>();
            addIfTyped("add", "adds a new custom command");
            addIfTyped("delete", "deletes a custom command");
            addIfTyped("prefix", "changes the command prefix\nExample: " + this.prefix + "prefix !");
            addIfTyped("spoiler ", "converts all text / media into spoilers (you cannot see it if you have show spoilers enabled)");
            addIfTyped("ud ", "searches urban dictionary (Example: " + this.prefix + "ud blue)\nWarning: results often are NSFW or offensive");
            addIfTyped("blank", "sends a blank character");
            addIfTyped("mock ", "mocks the message");
            addIfTyped("reverse ", "reverses the text of the message");
            addIfTyped("upper ", "converts text to uppercase");
            addIfTyped("lower ", "converts text to lowercase");
            addIfTyped("uwu", "ᵘʷᵘifies the message");
            addIfTyped("owo", "ᵒʷᵒifies the message");
            addIfTyped("c", "makes text into a code block");
            addIfTyped("b", "makes text bold");
            addIfTyped("i", "makes text italic");
            addIfTyped("u", "underlines text");
            addIfTyped("s", "makes text strikethrough");
            addIfTyped("bluecord", "how this mod came to be");
            addIfTyped("update", "update link for Bluecord");
            addCustomCommands(this.commandsList);
            AnonymousClass1 r8 = new AnonymousClass1(DiscordTools.getContext(), 17367044, 16908308, this.commandsList, this.commandsSummary.size());
            this.mediaTrayView.setVisibility(View.VISIBLE);
            this.mediaTrayView.bringToFront();
            this.mediaTrayView.setMinimumHeight(this.height * 2);
            @SuppressLint("ResourceType") ListView listView2 = (ListView) this.mediaTrayView.findViewById(16908298);
            this.mediaTrayList = listView2;
            listView2.getLayoutParams().height = Math.min(400, Math.round((DiscordTools.getContext().getResources().getDisplayMetrics().xdpi / 160.0f) * ((float) this.commandsList.size()) * 100.0f));
            this.mediaTrayList.setAdapter((ListAdapter) r8);
            this.mediaTrayList.setVisibility(View.VISIBLE);
            this.mediaTrayList.setDividerHeight(1);
            this.mediaTrayList.setOnItemClickListener(new $$Lambda$MediaTray$4TyyCYfrL1DrjLrv_y2Cr3HlWHI(this));
        }
    }
}

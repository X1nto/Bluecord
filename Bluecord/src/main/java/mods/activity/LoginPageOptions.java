package mods.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.discord.app.AppFragment;
import mods.DiscordTools;
import mods.preference.AccountSwitcher;
import mods.utils.PatternUtils;
import mods.utils.ToastUtil;
public class LoginPageOptions {
    @SuppressLint("ResourceType")
    public static void init(AppFragment appFragment) {
        View view = appFragment.getView();
        if (view != null) {
            View findViewById = view.findViewById(16908300);
            if (findViewById != null) {
                findViewById.setOnClickListener(new $$Lambda$LoginPageOptions$1dLGduHCfoue4QawHV32bM0QdFI(appFragment));
            }
            View findViewById2 = view.findViewById(16908303);
            if (findViewById2 != null) {
                findViewById2.setOnClickListener(new $$Lambda$LoginPageOptions$8hY1wUFcTIkKvD467WE22x4jNWo(appFragment));
            }
        }
    }

    static /* synthetic */ void lambda$init$0(AppFragment appFragment, DialogInterface dialogInterface, int i) {
        DiscordTools.basicAlert(appFragment.getContext(), "Token Login", "Use this if you are logged in on your PC or have your token on hand. If you're not sure what a Discord token is, don't worry, it's not very important.\n\nYour token can be found on your PC browser by looking at the Authorization request headers. A valid token should be around 60 characters long and have 2 periods (.) in it.\n\nNOTE: No one can see your token but you and Discord, and DO NOT SCREENSHOT, SHOW OR SHARE YOUR TOKEN WITH ANYONE!");
    }

    static /* synthetic */ void lambda$init$1(EditText editText, AppFragment appFragment, DialogInterface dialogInterface, int i) {
        String trim = editText.getText().toString().trim();
        if (!PatternUtils.isValidToken(trim)) {
            ToastUtil.toast("Invalid token!");
        } else {
            DiscordTools.restoreToken(appFragment.getAppActivity(), trim, true);
        }
    }

    static /* synthetic */ void lambda$init$2(AppFragment appFragment, View view) {
        EditText editText = new EditText(appFragment.getContext());
        editText.setHintTextColor(-3355444);
        editText.setTextColor(-1);
        editText.setHint("Enter your token...");
        editText.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        DiscordTools.newBuilder(appFragment.getContext()).setTitle("Token Login").setView(editText).setNegativeButton("Dismiss", (DialogInterface.OnClickListener) null).setNeutralButton("Help", new $$Lambda$LoginPageOptions$PYPObRGyIWROwE1DduQ74BnbZdU(appFragment)).setPositiveButton("Submit", new $$Lambda$LoginPageOptions$8p2upsWoIwBs7UpOPBwJMyzTFJw(editText, appFragment)).show();
    }

    static /* synthetic */ void lambda$init$3(AppFragment appFragment, View view) {
        AccountSwitcher.restoreBackup(appFragment.getContext());
    }
}

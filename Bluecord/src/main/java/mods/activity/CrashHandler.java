package mods.activity;

import android.os.Build;
import android.os.Process;
import android.util.Log;
import com.discord.api.permission.Permission;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import mods.constants.URLConstants;
import mods.net.Net;
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static Thread.UncaughtExceptionHandler handler;
    private static boolean hasRun;

    private CrashHandler() {
        hasRun = false;
    }

    public static void setup() {
        if (handler == null) {
            handler = Thread.getDefaultUncaughtExceptionHandler();
        }
        Log.e("Blue handler", "Default handler = " + handler.getClass().getName());
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Log.e("Blue handler", "Current handler = " + defaultUncaughtExceptionHandler.getClass().getName());
        if (!(defaultUncaughtExceptionHandler instanceof CrashHandler)) {
            handler = defaultUncaughtExceptionHandler;
            Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        boolean z2 = true;
        int i = 0;
        try {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            String stringWriter2 = stringWriter.toString();
            if (stringWriter2.length() > 10000) {
                stringWriter2 = stringWriter2.substring(0, 10000) + "...TRUNCATED";
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd", Locale.US);
            Date time = Calendar.getInstance().getTime();
            String str = "Android Version: " + Build.VERSION.RELEASE + "\n\n";
            String str2 = URLConstants.phpLink() + "?crash";
            Net.asyncRequest(str2, "Version 2.0\n\n" + str + (simpleDateFormat.format(time) + "\n\n") + ("Free memory: " + (Runtime.getRuntime().freeMemory() / Permission.VIEW_CHANNEL) + " KB\n\n") + stringWriter2);
        } finally {
            if ((handler instanceof CrashHandler) || hasRun) {
                System.exit(i);
                Process.killProcess(Process.myPid());
            }
            hasRun = z2;
            handler.uncaughtException(thread, th);
        }
    }
}
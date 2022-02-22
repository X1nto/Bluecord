package mods.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.discord.api.permission.Permission;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import mods.DiscordTools;
import mods.constants.PreferenceKeys;
import mods.preference.Prefs;
import mods.utils.ToastUtil;
public class BackgroundView extends AppCompatImageView {

    /* renamed from: mods.view.BackgroundView$1  reason: invalid class name */
    static class AnonymousClass1 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ RecyclerView val$view;

        AnonymousClass1(RecyclerView recyclerView) {
            this.val$view = recyclerView;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            this.val$view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            int width = this.val$view.getWidth();
            int height = this.val$view.getHeight();
            Log.e("BackgroundView", String.format("w = %s, h = %s, orientation = %s, location = RecyclerView", width, height, DiscordTools.getOrientation()));
            if (width > 0 && height > 0 && height >= width) {
                Prefs.setFloat(PreferenceKeys.OPTIMAL_CHAT_BG_WIDTH, (float) width);
                Prefs.setFloat(PreferenceKeys.OPTIMAL_CHAT_BG_HEIGHT, (float) height);
            }
        }
    }

    public BackgroundView(Context context) {
        super(context);
        init();
    }

    public BackgroundView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public BackgroundView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private Bitmap blurImage(Context context, Bitmap bitmap, float f) {
        if (f < 0.0f || f > 25.0f) {
            Log.e("BackgroundView", "Not blurring, radius must be 0 < r <= 25 (currently: " + f + ")");
            return bitmap;
        }
        try {
            bitmap = rgb565ToArgb888(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        RenderScript create = RenderScript.create(context);
        Allocation createFromBitmap = Allocation.createFromBitmap(create, bitmap);
        Allocation createFromBitmap2 = Allocation.createFromBitmap(create, createBitmap);
        ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
        create2.setInput(createFromBitmap);
        create2.setRadius(f);
        create2.forEach(createFromBitmap2);
        createFromBitmap2.copyTo(createBitmap);
        create.destroy();
        return createBitmap;
    }

    private Bitmap fileToBitmapLegacy(String str) {
        try {
            int i = new File(str).length() > Permission.SPEAK ? 75 : 50;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Bitmap decodeFile = BitmapFactory.decodeFile(str);
            decodeFile.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            decodeFile.recycle();
            Bitmap decodeStream = BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            byteArrayOutputStream.close();
            if (Prefs.getBoolean(PreferenceKeys.BACKGROUND_BLUR, false)) {
                float f = Prefs.getFloat(PreferenceKeys.BACKGROUND_BLUR_LEVEL, 12.5f);
                if (f > 0.0f) {
                    return blurImage(getContext(), decodeStream, f);
                }
            }
            return decodeStream;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void grabViewBounds(RecyclerView recyclerView) {
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new AnonymousClass1(recyclerView));
    }

    private void init() {
        setScaleType(Prefs.getBoolean(PreferenceKeys.BACKGROUND_UCROP_UPGRADED, false) ? ImageView.ScaleType.CENTER_CROP : ImageView.ScaleType.FIT_XY);
        setAdjustViewBounds(false);
        setImage();
    }

    private Bitmap rgb565ToArgb888(Bitmap bitmap) {
        int[] iArr = new int[(bitmap.getWidth() * bitmap.getHeight())];
        bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        createBitmap.setPixels(iArr, 0, createBitmap.getWidth(), 0, 0, createBitmap.getWidth(), createBitmap.getHeight());
        return createBitmap;
    }

    private void setImage() {
        String string;
        if (Prefs.getBoolean(PreferenceKeys.BACKGROUND_ENABLED, false) && (string = Prefs.getString(PreferenceKeys.BACKGROUND_PATH, null)) != null) {
            try {
                setImageDrawable(new BitmapDrawable(getResources(), fileToBitmapLegacy(string)));
            } catch (Throwable th) {
                th.printStackTrace();
                Prefs.removeValues(PreferenceKeys.BACKGROUND_ENABLED, PreferenceKeys.BACKGROUND_PATH);
                ToastUtil.toast("Failed to set background, custom background has been disabled.\n\nTry setting it again.");
            }
        }
    }
}

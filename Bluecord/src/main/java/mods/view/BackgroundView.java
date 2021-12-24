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
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import com.discord.api.permission.Permission;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import mods.constants.PreferenceKeys;
import mods.preference.Prefs;
import mods.utils.ToastUtil;
public class BackgroundView extends AppCompatImageView {
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

    private Bitmap fileToBitmap(String str) {
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

    private void init() {
        setScaleType(ScaleType.FIT_XY);
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
        if (Prefs.getBoolean(PreferenceKeys.BACKGROUND_CUSTOM, false) && (string = Prefs.getString(PreferenceKeys.BACKGROUND_PATH, null)) != null) {
            try {
                setImageDrawable(new BitmapDrawable(getResources(), fileToBitmap(string)));
            } catch (Throwable th) {
                th.printStackTrace();
                Prefs.removeValues(PreferenceKeys.BACKGROUND_CUSTOM, PreferenceKeys.BACKGROUND_PATH);
                ToastUtil.toast("Failed to set background, custom background has been disabled.\n\nTry setting it again.");
            }
        }
    }
}

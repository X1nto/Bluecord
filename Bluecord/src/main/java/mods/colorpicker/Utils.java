package mods.colorpicker;

import android.graphics.Color;
import androidx.core.view.ViewCompat;
public class Utils {
    public static int adjustAlpha(float f, int i) {
        return (alphaValueAsInt(f) << 24) | (16777215 & i);
    }

    public static int alphaValueAsInt(float f) {
        return Math.round(255.0f * f);
    }

    public static int colorAtLightness(int i, float f) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        fArr[2] = f;
        return Color.HSVToColor(fArr);
    }

    public static float getAlphaPercent(int i) {
        return ((float) Color.alpha(i)) / 255.0f;
    }

    public static String getHexString(int i, boolean z2) {
        return String.format(z2 ? "#%08X" : "#%06X", Integer.valueOf((z2 ? -1 : ViewCompat.MEASURED_SIZE_MASK) & i)).toUpperCase();
    }

    public static float lightnessOfColor(int i) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        return fArr[2];
    }
}

package mods.colorpicker;

import android.graphics.Color;
public class ColorCircle {
    private int color;
    private float[] hsv = new float[3];
    private float[] hsvClone;

    /* renamed from: x  reason: collision with root package name */
    private float f2947x;

    /* renamed from: y  reason: collision with root package name */
    private float f2948y;

    public ColorCircle(float f, float f2, float[] fArr) {
        set(f, f2, fArr);
    }

    public int getColor() {
        return this.color;
    }

    public float[] getHsv() {
        return this.hsv;
    }

    public float[] getHsvWithLightness(float f) {
        if (this.hsvClone == null) {
            this.hsvClone = (float[]) this.hsv.clone();
        }
        float[] fArr = this.hsvClone;
        float[] fArr2 = this.hsv;
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[1];
        fArr[2] = f;
        return fArr;
    }

    public float getX() {
        return this.f2947x;
    }

    public float getY() {
        return this.f2948y;
    }

    public void set(float f, float f2, float[] fArr) {
        this.f2947x = f;
        this.f2948y = f2;
        float[] fArr2 = this.hsv;
        fArr2[0] = fArr[0];
        fArr2[1] = fArr[1];
        fArr2[2] = fArr[2];
        this.color = Color.HSVToColor(fArr2);
    }

    public double sqDist(float f, float f2) {
        double d = (double) (this.f2947x - f);
        double d2 = (double) (this.f2948y - f2);
        return (d * d) + (d2 * d2);
    }
}

package mods.colorpicker.renderer;

import android.graphics.Color;
import android.graphics.Paint;
import mods.colorpicker.ColorCircle;
import mods.colorpicker.builder.PaintBuilder;
public class FlowerColorWheelRenderer extends AbsColorWheelRenderer {
    private float[] hsv = new float[3];
    private Paint selectorFill = PaintBuilder.newPaint().build();
    private float sizeJitter = 1.2f;

    @Override // mods.colorpicker.renderer.ColorWheelRenderer
    public void draw() {
        int size = this.colorCircleList.size();
        int i = 0;
        float f = 2.0f;
        float width = ((float) this.colorWheelRenderOption.targetCanvas.getWidth()) / 2.0f;
        int i2 = this.colorWheelRenderOption.density;
        float f2 = this.colorWheelRenderOption.strokeWidth;
        float f3 = this.colorWheelRenderOption.maxRadius;
        float f4 = this.colorWheelRenderOption.cSize;
        int i3 = 0;
        while (i3 < i2) {
            float f5 = ((float) i3) / ((float) (i2 - 1));
            float f6 = (((float) i3) - (((float) i2) / f)) / ((float) i2);
            float f7 = f3 * f5;
            float max = Math.max(1.5f + f2, (i3 == 0 ? 0.0f : this.sizeJitter * f4 * f6) + f4);
            int min = Math.min(calcTotalCount(f7, max), i2 * 2);
            int i4 = 0;
            while (i4 < min) {
                double d = ((((double) i4) * 6.283185307179586d) / ((double) min)) + ((3.141592653589793d / ((double) min)) * ((double) ((i3 + 1) % 2)));
                float cos = ((float) (((double) f7) * Math.cos(d))) + width;
                float sin = ((float) (((double) f7) * Math.sin(d))) + width;
                float[] fArr = this.hsv;
                fArr[0] = (float) ((180.0d * d) / 3.141592653589793d);
                fArr[1] = f7 / f3;
                fArr[2] = this.colorWheelRenderOption.lightness;
                this.selectorFill.setColor(Color.HSVToColor(this.hsv));
                this.selectorFill.setAlpha(getAlphaValueAsInt());
                this.colorWheelRenderOption.targetCanvas.drawCircle(cos, sin, max - f2, this.selectorFill);
                if (i >= size) {
                    this.colorCircleList.add(new ColorCircle(cos, sin, this.hsv));
                } else {
                    ((ColorCircle) this.colorCircleList.get(i)).set(cos, sin, this.hsv);
                }
                i++;
                i4++;
                f5 = f5;
                f6 = f6;
                i2 = i2;
                i3 = i3;
                f4 = f4;
                width = width;
            }
            i3++;
            f = 2.0f;
        }
    }
}

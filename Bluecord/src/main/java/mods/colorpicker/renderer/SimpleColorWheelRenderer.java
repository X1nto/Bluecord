package mods.colorpicker.renderer;

import android.graphics.Color;
import android.graphics.Paint;
import mods.colorpicker.ColorCircle;
import mods.colorpicker.builder.PaintBuilder;
public class SimpleColorWheelRenderer extends AbsColorWheelRenderer {
    private float[] hsv = new float[3];
    private Paint selectorFill = PaintBuilder.newPaint().build();

    @Override // mods.colorpicker.renderer.ColorWheelRenderer
    public void draw() {
        int size = this.colorCircleList.size();
        int i = 0;
        float width = ((float) this.colorWheelRenderOption.targetCanvas.getWidth()) / 2.0f;
        int i2 = this.colorWheelRenderOption.density;
        float f = this.colorWheelRenderOption.maxRadius;
        int i3 = 0;
        while (i3 < i2) {
            float f2 = ((float) i3) / ((float) (i2 - 1));
            float f3 = f * f2;
            float f4 = this.colorWheelRenderOption.cSize;
            int calcTotalCount = calcTotalCount(f3, f4);
            int i4 = 0;
            while (i4 < calcTotalCount) {
                double d = ((((double) i4) * 6.283185307179586d) / ((double) calcTotalCount)) + ((3.141592653589793d / ((double) calcTotalCount)) * ((double) ((i3 + 1) % 2)));
                float cos = ((float) (((double) f3) * Math.cos(d))) + width;
                float sin = ((float) (((double) f3) * Math.sin(d))) + width;
                float[] fArr = this.hsv;
                fArr[0] = (float) ((180.0d * d) / 3.141592653589793d);
                fArr[1] = f3 / f;
                fArr[2] = this.colorWheelRenderOption.lightness;
                this.selectorFill.setColor(Color.HSVToColor(this.hsv));
                this.selectorFill.setAlpha(getAlphaValueAsInt());
                this.colorWheelRenderOption.targetCanvas.drawCircle(cos, sin, f4 - this.colorWheelRenderOption.strokeWidth, this.selectorFill);
                if (i >= size) {
                    this.colorCircleList.add(new ColorCircle(cos, sin, this.hsv));
                } else {
                    ((ColorCircle) this.colorCircleList.get(i)).set(cos, sin, this.hsv);
                }
                i++;
                i4++;
                i3 = i3;
                i2 = i2;
                f2 = f2;
            }
            i3++;
        }
    }
}

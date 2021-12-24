package mods.colorpicker.slider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import mods.colorpicker.ColorPickerView;
import mods.colorpicker.Utils;
import mods.colorpicker.builder.PaintBuilder;
public class LightnessSlider extends AbsCustomSlider {
    private Paint barPaint = PaintBuilder.newPaint().build();
    private Paint clearingStroke = PaintBuilder.newPaint().color(-1).xPerMode(PorterDuff.Mode.CLEAR).build();
    private int color;
    private ColorPickerView colorPicker;
    private Paint solid = PaintBuilder.newPaint().build();

    public LightnessSlider(Context context) {
        super(context);
    }

    public LightnessSlider(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LightnessSlider(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // mods.colorpicker.slider.AbsCustomSlider
    protected void drawBar(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        float[] fArr = new float[3];
        Color.colorToHSV(this.color, fArr);
        int max = Math.max(2, width / 256);
        for (int i = 0; i <= width; i += max) {
            fArr[2] = ((float) i) / ((float) (width - 1));
            this.barPaint.setColor(Color.HSVToColor(fArr));
            canvas.drawRect((float) i, 0.0f, (float) (i + max), (float) height, this.barPaint);
        }
    }

    @Override // mods.colorpicker.slider.AbsCustomSlider
    protected void drawHandle(Canvas canvas, float f, float f2) {
        this.solid.setColor(Utils.colorAtLightness(this.color, this.value));
        if (this.showBorder) {
            canvas.drawCircle(f, f2, (float) this.handleRadius, this.clearingStroke);
        }
        canvas.drawCircle(f, f2, ((float) this.handleRadius) * 0.75f, this.solid);
    }

    @Override // mods.colorpicker.slider.AbsCustomSlider
    protected void onValueChanged(float f) {
        ColorPickerView colorPickerView = this.colorPicker;
        if (colorPickerView != null) {
            colorPickerView.setLightness(f);
        }
    }

    public void setColor(int i) {
        this.color = i;
        this.value = Utils.lightnessOfColor(i);
        if (this.bar != null) {
            updateBar();
            invalidate();
        }
    }

    public void setColorPicker(ColorPickerView colorPickerView) {
        this.colorPicker = colorPickerView;
    }
}

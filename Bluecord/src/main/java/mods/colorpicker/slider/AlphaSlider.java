package mods.colorpicker.slider;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import mods.colorpicker.ColorPickerView;
import mods.colorpicker.Utils;
import mods.colorpicker.builder.PaintBuilder;
public class AlphaSlider extends AbsCustomSlider {
    private Paint alphaPatternPaint = PaintBuilder.newPaint().build();
    private Paint barPaint = PaintBuilder.newPaint().build();
    private Bitmap clearBitmap;
    private Canvas clearBitmapCanvas;
    private Paint clearStroke = PaintBuilder.newPaint().build();
    private Paint clearingStroke = PaintBuilder.newPaint().color(-1).xPerMode(PorterDuff.Mode.CLEAR).build();
    public int color;
    private ColorPickerView colorPicker;
    private Paint solid = PaintBuilder.newPaint().build();

    public AlphaSlider(Context context) {
        super(context);
    }

    public AlphaSlider(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AlphaSlider(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // mods.colorpicker.slider.AbsCustomSlider
    protected void createBitmaps() {
        super.createBitmaps();
        this.alphaPatternPaint.setShader(PaintBuilder.createAlphaPatternShader(this.barHeight * 2));
        this.clearBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        this.clearBitmapCanvas = new Canvas(this.clearBitmap);
    }

    @Override // mods.colorpicker.slider.AbsCustomSlider
    protected void drawBar(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        canvas.drawRect(0.0f, 0.0f, (float) width, (float) height, this.alphaPatternPaint);
        int max = Math.max(2, width / 256);
        for (int i = 0; i <= width; i += max) {
            this.barPaint.setColor(this.color);
            this.barPaint.setAlpha(Math.round(255.0f * (((float) i) / ((float) (width - 1)))));
            canvas.drawRect((float) i, 0.0f, (float) (i + max), (float) height, this.barPaint);
        }
    }

    @Override // mods.colorpicker.slider.AbsCustomSlider
    protected void drawHandle(Canvas canvas, float f, float f2) {
        this.solid.setColor(this.color);
        this.solid.setAlpha(Math.round(this.value * 255.0f));
        if (this.showBorder) {
            canvas.drawCircle(f, f2, (float) this.handleRadius, this.clearingStroke);
        }
        if (this.value < 1.0f) {
            this.clearBitmapCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
            this.clearBitmapCanvas.drawCircle(f, f2, (((float) this.handleRadius) * 0.75f) + 4.0f, this.alphaPatternPaint);
            this.clearBitmapCanvas.drawCircle(f, f2, (((float) this.handleRadius) * 0.75f) + 4.0f, this.solid);
            this.clearStroke = PaintBuilder.newPaint().color(-1).style(Paint.Style.STROKE).stroke(6.0f).xPerMode(PorterDuff.Mode.CLEAR).build();
            this.clearBitmapCanvas.drawCircle(f, f2, (((float) this.handleRadius) * 0.75f) + (this.clearStroke.getStrokeWidth() / 2.0f), this.clearStroke);
            canvas.drawBitmap(this.clearBitmap, 0.0f, 0.0f, (Paint) null);
            return;
        }
        canvas.drawCircle(f, f2, ((float) this.handleRadius) * 0.75f, this.solid);
    }

    @Override // mods.colorpicker.slider.AbsCustomSlider
    protected void onValueChanged(float f) {
        ColorPickerView colorPickerView = this.colorPicker;
        if (colorPickerView != null) {
            colorPickerView.setAlphaValue(f);
        }
    }

    public void setColor(int i) {
        this.color = i;
        this.value = Utils.getAlphaPercent(i);
        if (this.bar != null) {
            updateBar();
            invalidate();
        }
    }

    public void setColorPicker(ColorPickerView colorPickerView) {
        this.colorPicker = colorPickerView;
    }
}

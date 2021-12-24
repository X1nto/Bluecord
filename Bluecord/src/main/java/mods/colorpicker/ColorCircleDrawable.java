package mods.colorpicker;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import mods.colorpicker.builder.PaintBuilder;
public class ColorCircleDrawable extends ColorDrawable {
    private Paint fillBackPaint = PaintBuilder.newPaint().shader(PaintBuilder.createAlphaPatternShader(26)).build();
    private Paint fillPaint = PaintBuilder.newPaint().style(Paint.Style.FILL).color(0).build();
    private Paint strokePaint = PaintBuilder.newPaint().style(Paint.Style.STROKE).stroke(this.strokeWidth).color(-6381922).build();
    private float strokeWidth;

    public ColorCircleDrawable(int i) {
        super(i);
    }

    @Override // android.graphics.drawable.ColorDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        canvas.drawColor(0);
        float width = ((float) canvas.getWidth()) / 2.0f;
        float f = width / 8.0f;
        this.strokeWidth = f;
        this.strokePaint.setStrokeWidth(f);
        this.fillPaint.setColor(getColor());
        canvas.drawCircle(width, width, width - this.strokeWidth, this.fillBackPaint);
        canvas.drawCircle(width, width, width - this.strokeWidth, this.fillPaint);
        canvas.drawCircle(width, width, width - this.strokeWidth, this.strokePaint);
    }

    @Override // android.graphics.drawable.ColorDrawable
    public void setColor(int i) {
        super.setColor(i);
        invalidateSelf();
    }
}

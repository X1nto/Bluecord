package mods.colorpicker.slider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import mods.constants.ColorPickerConstants;

public abstract class AbsCustomSlider extends View {
    protected Bitmap bar;
    protected Canvas barCanvas;
    protected int barHeight = 10;
    protected int barOffsetX;
    protected Bitmap bitmap;
    protected Canvas bitmapCanvas;
    protected int handleRadius = 20;
    private boolean inVerticalOrientation = false;
    protected OnValueChangedListener onValueChangedListener;
    protected boolean showBorder = false;
    protected float value = 1.0f;

    public AbsCustomSlider(Context context) {
        super(context);
        init(context, null);
    }

    public AbsCustomSlider(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public AbsCustomSlider(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, ColorPickerConstants.getDeclaredSliderAttributes(), 0, 0);
        try {
            this.inVerticalOrientation = obtainStyledAttributes.getBoolean(0, this.inVerticalOrientation);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    protected void createBitmaps() {
        int i;
        int i2;
        if (this.inVerticalOrientation) {
            i2 = getHeight();
            i = getWidth();
        } else {
            i2 = getWidth();
            i = getHeight();
        }
        this.bar = Bitmap.createBitmap(Math.max(i2 - (this.barOffsetX * 2), 1), this.barHeight, Bitmap.Config.ARGB_8888);
        this.barCanvas = new Canvas(this.bar);
        Bitmap bitmap = this.bitmap;
        if (bitmap == null || bitmap.getWidth() != i2 || this.bitmap.getHeight() != i) {
            Bitmap bitmap2 = this.bitmap;
            if (bitmap2 != null) {
                bitmap2.recycle();
            }
            this.bitmap = Bitmap.createBitmap(i2, i, Bitmap.Config.ARGB_8888);
            this.bitmapCanvas = new Canvas(this.bitmap);
        }
    }

    protected abstract void drawBar(Canvas canvas);

    protected abstract void drawHandle(Canvas canvas, float f, float f2);

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i;
        int i2;
        Canvas canvas2;
        super.onDraw(canvas);
        if (this.inVerticalOrientation) {
            i2 = getHeight();
            i = getWidth();
            canvas.rotate(-90.0f);
            canvas.translate((float) (-i2), 0.0f);
        } else {
            i2 = getWidth();
            i = getHeight();
        }
        if (this.bar != null && (canvas2 = this.bitmapCanvas) != null) {
            canvas2.drawColor(0, PorterDuff.Mode.CLEAR);
            Canvas canvas3 = this.bitmapCanvas;
            Bitmap bitmap = this.bar;
            canvas3.drawBitmap(bitmap, (float) this.barOffsetX, (float) ((i - bitmap.getHeight()) / 2), (Paint) null);
            int i3 = this.handleRadius;
            drawHandle(this.bitmapCanvas, ((float) i3) + (this.value * ((float) (i2 - (i3 * 2)))), ((float) i) / 2.0f);
            canvas.drawBitmap(this.bitmap, 0.0f, 0.0f, (Paint) null);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = MeasureSpec.getMode(i);
        int i3 = 0;
        if (mode == 0) {
            i3 = i;
        } else if (mode == Integer.MIN_VALUE) {
            i3 = MeasureSpec.getSize(i);
        } else if (mode == 1073741824) {
            i3 = MeasureSpec.getSize(i);
        }
        int mode2 = MeasureSpec.getMode(i2);
        int i4 = 0;
        if (mode2 == 0) {
            i4 = i2;
        } else if (mode2 == Integer.MIN_VALUE) {
            i4 = MeasureSpec.getSize(i2);
        } else if (mode2 == 1073741824) {
            i4 = MeasureSpec.getSize(i2);
        }
        setMeasuredDimension(i3, i4);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        updateBar();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE: {
                if (bar != null) {
                    if (inVerticalOrientation) {
                        value = 1 - (event.getY() - barOffsetX) / bar.getWidth();
                    } else {
                        value = (event.getX() - barOffsetX) / bar.getWidth();
                    }
                    value = Math.max(0, Math.min(value, 1));
                    onValueChanged(value);
                    invalidate();
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                onValueChanged(value);
                if (onValueChangedListener != null)
                    onValueChangedListener.onValueChanged(value);
                invalidate();
            }
        }
        return true;
    }

    protected abstract void onValueChanged(float f);

    public void setOnValueChangedListener(OnValueChangedListener onValueChangedListener) {
        this.onValueChangedListener = onValueChangedListener;
    }

    public void setShowBorder(boolean z2) {
        this.showBorder = z2;
    }

    protected void updateBar() {
        this.barOffsetX = this.handleRadius;
        this.handleRadius = 20;
        this.barHeight = 10;
        if (this.bar == null) {
            createBitmaps();
        }
        drawBar(this.barCanvas);
        invalidate();
    }
}

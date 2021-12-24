package mods.colorpicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Iterator;
import mods.colorpicker.builder.ColorWheelRendererBuilder;
import mods.colorpicker.builder.PaintBuilder;
import mods.colorpicker.renderer.ColorWheelRenderOption;
import mods.colorpicker.renderer.ColorWheelRenderer;
import mods.colorpicker.slider.AlphaSlider;
import mods.colorpicker.slider.LightnessSlider;
import mods.constants.ColorPickerConstants;

@SuppressLint("ResourceType")
public class ColorPickerView extends View {
    private static final float STROKE_RATIO = 1.5f;
    private float alpha;
    private Paint alphaPatternPaint;
    private AlphaSlider alphaSlider;
    private int alphaSliderViewId;
    private int backgroundColor;
    private ArrayList<OnColorChangedListener> colorChangedListeners;
    private EditText colorEdit;
    private LinearLayout colorPreview;
    private int colorSelection;
    private TextWatcher colorTextChange;
    private Bitmap colorWheel;
    private Canvas colorWheelCanvas;
    private Paint colorWheelFill;
    private Bitmap currentColor;
    private Canvas currentColorCanvas;
    private ColorCircle currentColorCircle;
    private int density;
    private Integer initialColor;
    private Integer[] initialColors;
    private float lightness;
    private LightnessSlider lightnessSlider;
    private int lightnessSliderViewId;
    private ArrayList<OnColorSelectedListener> listeners;
    private Integer pickerColorEditTextColor;
    private ColorWheelRenderer renderer;
    private Paint selectorStroke;
    private boolean showBorder;

    /* renamed from: mods.colorpicker.ColorPickerView$1  reason: invalid class name */
    class AnonymousClass1 implements TextWatcher {
        AnonymousClass1() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            try {
                ColorPickerView.this.setColor(Color.parseColor(charSequence.toString()), false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: mods.colorpicker.ColorPickerView$2  reason: invalid class name */
    class AnonymousClass2 implements OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Object tag;
            if (view != null && (tag = view.getTag()) != null && (tag instanceof Integer)) {
                ColorPickerView.this.setSelectedColor(((Integer) tag).intValue());
            }
        }
    }

    public enum WHEEL_TYPE {
        FLOWER,
        CIRCLE;

        public static WHEEL_TYPE indexOf(int i) {
            return i != 0 ? i != 1 ? FLOWER : CIRCLE : FLOWER;
        }
    }

    public ColorPickerView(Context context) {
        super(context);
        this.density = 8;
        this.lightness = 1.0f;
        this.alpha = 1.0f;
        this.backgroundColor = 0;
        this.initialColors = new Integer[]{null, null, null, null, null};
        this.colorSelection = 0;
        this.colorWheelFill = PaintBuilder.newPaint().color(0).build();
        this.selectorStroke = PaintBuilder.newPaint().color(0).build();
        this.alphaPatternPaint = PaintBuilder.newPaint().build();
        this.colorChangedListeners = new ArrayList<>();
        this.listeners = new ArrayList<>();
        this.colorTextChange = new AnonymousClass1();
        initWith(context, null);
    }

    public ColorPickerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.density = 8;
        this.lightness = 1.0f;
        this.alpha = 1.0f;
        this.backgroundColor = 0;
        this.initialColors = new Integer[]{null, null, null, null, null};
        this.colorSelection = 0;
        this.colorWheelFill = PaintBuilder.newPaint().color(0).build();
        this.selectorStroke = PaintBuilder.newPaint().color(0).build();
        this.alphaPatternPaint = PaintBuilder.newPaint().build();
        this.colorChangedListeners = new ArrayList<>();
        this.listeners = new ArrayList<>();
        this.colorTextChange = new AnonymousClass1();
        initWith(context, attributeSet);
    }

    public ColorPickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.density = 8;
        this.lightness = 1.0f;
        this.alpha = 1.0f;
        this.backgroundColor = 0;
        this.initialColors = new Integer[]{null, null, null, null, null};
        this.colorSelection = 0;
        this.colorWheelFill = PaintBuilder.newPaint().color(0).build();
        this.selectorStroke = PaintBuilder.newPaint().color(0).build();
        this.alphaPatternPaint = PaintBuilder.newPaint().build();
        this.colorChangedListeners = new ArrayList<>();
        this.listeners = new ArrayList<>();
        this.colorTextChange = new AnonymousClass1();
        initWith(context, attributeSet);
    }

    public ColorPickerView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.density = 8;
        this.lightness = 1.0f;
        this.alpha = 1.0f;
        this.backgroundColor = 0;
        this.initialColors = new Integer[]{null, null, null, null, null};
        this.colorSelection = 0;
        this.colorWheelFill = PaintBuilder.newPaint().color(0).build();
        this.selectorStroke = PaintBuilder.newPaint().color(0).build();
        this.alphaPatternPaint = PaintBuilder.newPaint().build();
        this.colorChangedListeners = new ArrayList<>();
        this.listeners = new ArrayList<>();
        this.colorTextChange = new AnonymousClass1();
        initWith(context, attributeSet);
    }

    private void drawColorWheel() {
        this.colorWheelCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        this.currentColorCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        if (this.renderer != null) {
            float width = ((float) this.colorWheelCanvas.getWidth()) / 2.0f;
            int i = this.density;
            float f = (width - 1.5374999f) - (width / ((float) i));
            ColorWheelRenderOption renderOption = this.renderer.getRenderOption();
            renderOption.density = this.density;
            renderOption.maxRadius = f;
            renderOption.cSize = (f / ((float) (i - 1))) / 2.0f;
            renderOption.strokeWidth = 1.5374999f;
            renderOption.alpha = this.alpha;
            renderOption.lightness = this.lightness;
            renderOption.targetCanvas = this.colorWheelCanvas;
            this.renderer.initWith(renderOption);
            this.renderer.draw();
        }
    }

    private ColorCircle findNearestByColor(int i) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        ColorCircle colorCircle = null;
        double d = Double.MAX_VALUE;
        char c2 = 0;
        double cos = ((double) fArr[1]) * Math.cos((((double) fArr[0]) * 3.141592653589793d) / 180.0d);
        double sin = ((double) fArr[1]) * Math.sin((((double) fArr[0]) * 3.141592653589793d) / 180.0d);
        for (ColorCircle colorCircle2 : this.renderer.getColorCircleList()) {
            float[] hsv = colorCircle2.getHsv();
            double cos2 = ((double) hsv[1]) * Math.cos((((double) hsv[c2]) * 3.141592653589793d) / 180.0d);
            double d2 = cos - cos2;
            double sin2 = sin - (((double) hsv[1]) * Math.sin((((double) hsv[0]) * 3.141592653589793d) / 180.0d));
            double d3 = (d2 * d2) + (sin2 * sin2);
            if (d3 < d) {
                d = d3;
                colorCircle = colorCircle2;
            }
            fArr = fArr;
            sin = sin;
            c2 = 0;
        }
        return colorCircle;
    }

    private ColorCircle findNearestByPosition(float f, float f2) {
        ColorCircle colorCircle = null;
        double d = Double.MAX_VALUE;
        for (ColorCircle colorCircle2 : this.renderer.getColorCircleList()) {
            double sqDist = colorCircle2.sqDist(f, f2);
            if (d > sqDist) {
                d = sqDist;
                colorCircle = colorCircle2;
            }
        }
        return colorCircle;
    }

    private void initWith(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ColorPickerConstants.getDeclaredPickerAttributes());
        this.initialColor = Integer.valueOf(obtainStyledAttributes.getInt(4, -1));
        this.pickerColorEditTextColor = Integer.valueOf(obtainStyledAttributes.getInt(10, -1));
        ColorWheelRenderer renderer = ColorWheelRendererBuilder.getRenderer(WHEEL_TYPE.indexOf(obtainStyledAttributes.getInt(12, 0)));
        this.alphaSliderViewId = obtainStyledAttributes.getResourceId(1, 0);
        this.lightnessSliderViewId = obtainStyledAttributes.getResourceId(6, 0);
        setRenderer(renderer);
        setDensity(this.density);
        setInitialColor(this.initialColor.intValue(), true);
        obtainStyledAttributes.recycle();
    }

    private void setColorPreviewColor(int i) {
        Integer[] numArr;
        int i2;
        LinearLayout linearLayout = this.colorPreview;
        if (linearLayout != null && (numArr = this.initialColors) != null && (i2 = this.colorSelection) <= numArr.length && numArr[i2] != null && linearLayout.getChildCount() != 0 && this.colorPreview.getVisibility() == 0) {
            View childAt = this.colorPreview.getChildAt(this.colorSelection);
            if (childAt instanceof LinearLayout) {
                ((ImageView) ((LinearLayout) childAt).findViewById(16908296)).setImageDrawable(new ColorCircleDrawable(i));
            }
        }
    }

    private void setColorText(int i) {
        EditText editText = this.colorEdit;
        if (editText != null) {
            editText.setText(Utils.getHexString(i, this.alphaSlider != null));
        }
    }

    private void setColorToSliders(int i) {
        LightnessSlider lightnessSlider = this.lightnessSlider;
        if (lightnessSlider != null) {
            lightnessSlider.setColor(i);
        }
        AlphaSlider alphaSlider = this.alphaSlider;
        if (alphaSlider != null) {
            alphaSlider.setColor(i);
        }
    }

    private void setHighlightedColor(int i) {
        int childCount = this.colorPreview.getChildCount();
        if (childCount != 0 && this.colorPreview.getVisibility() == 0) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = this.colorPreview.getChildAt(i2);
                if (childAt instanceof LinearLayout) {
                    LinearLayout linearLayout = (LinearLayout) childAt;
                    if (i2 == i) {
                        linearLayout.setBackgroundColor(-1);
                    } else {
                        linearLayout.setBackgroundColor(0);
                    }
                }
            }
        }
    }

    private void updateColorWheel() {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (measuredHeight < measuredWidth) {
            measuredWidth = measuredHeight;
        }
        if (measuredWidth > 0) {
            Bitmap bitmap = this.colorWheel;
            if (bitmap == null || bitmap.getWidth() != measuredWidth) {
                this.colorWheel = Bitmap.createBitmap(measuredWidth, measuredWidth, Bitmap.Config.ARGB_8888);
                this.colorWheelCanvas = new Canvas(this.colorWheel);
                this.alphaPatternPaint.setShader(PaintBuilder.createAlphaPatternShader(26));
            }
            Bitmap bitmap2 = this.currentColor;
            if (bitmap2 == null || bitmap2.getWidth() != measuredWidth) {
                this.currentColor = Bitmap.createBitmap(measuredWidth, measuredWidth, Bitmap.Config.ARGB_8888);
                this.currentColorCanvas = new Canvas(this.currentColor);
            }
            drawColorWheel();
            invalidate();
        }
    }

    public void addOnColorChangedListener(OnColorChangedListener onColorChangedListener) {
        this.colorChangedListeners.add(onColorChangedListener);
    }

    public void addOnColorSelectedListener(OnColorSelectedListener onColorSelectedListener) {
        this.listeners.add(onColorSelectedListener);
    }

    protected void callOnColorChangedListeners(int i, int i2) {
        ArrayList<OnColorChangedListener> arrayList = this.colorChangedListeners;
        if (arrayList != null && i != i2) {
            Iterator<OnColorChangedListener> it = arrayList.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onColorChanged(i2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Integer[] getAllColors() {
        return this.initialColors;
    }

    public int getSelectedColor() {
        int i = 0;
        ColorCircle colorCircle = this.currentColorCircle;
        if (colorCircle != null) {
            i = Utils.colorAtLightness(colorCircle.getColor(), this.lightness);
        }
        return Utils.adjustAlpha(this.alpha, i);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        ColorCircle colorCircle;
        super.onDraw(canvas);
        canvas.drawColor(this.backgroundColor);
        float width = ((((float) canvas.getWidth()) / 1.025f) / ((float) this.density)) / 2.0f;
        if (this.colorWheel != null && (colorCircle = this.currentColorCircle) != null) {
            this.colorWheelFill.setColor(Color.HSVToColor(colorCircle.getHsvWithLightness(this.lightness)));
            this.colorWheelFill.setAlpha((int) (this.alpha * 255.0f));
            this.currentColorCanvas.drawCircle(this.currentColorCircle.getX(), this.currentColorCircle.getY(), width + 4.0f, this.alphaPatternPaint);
            this.currentColorCanvas.drawCircle(this.currentColorCircle.getX(), this.currentColorCircle.getY(), 4.0f + width, this.colorWheelFill);
            this.selectorStroke = PaintBuilder.newPaint().color(-1).style(Paint.Style.STROKE).stroke(0.5f * width).xPerMode(PorterDuff.Mode.CLEAR).build();
            if (this.showBorder) {
                this.colorWheelCanvas.drawCircle(this.currentColorCircle.getX(), this.currentColorCircle.getY(), (this.selectorStroke.getStrokeWidth() / 2.0f) + width, this.selectorStroke);
            }
            canvas.drawBitmap(this.colorWheel, 0.0f, 0.0f, (Paint) null);
            this.currentColorCanvas.drawCircle(this.currentColorCircle.getX(), this.currentColorCircle.getY(), (this.selectorStroke.getStrokeWidth() / 2.0f) + width, this.selectorStroke);
            canvas.drawBitmap(this.currentColor, 0.0f, 0.0f, (Paint) null);
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z2, int i, int i2, int i3, int i4) {
        super.onLayout(z2, i, i2, i3, i4);
        if (this.alphaSliderViewId != 0) {
            setAlphaSlider((AlphaSlider) getRootView().findViewById(this.alphaSliderViewId));
        }
        if (this.lightnessSliderViewId != 0) {
            setLightnessSlider((LightnessSlider) getRootView().findViewById(this.lightnessSliderViewId));
        }
        updateColorWheel();
        this.currentColorCircle = findNearestByColor(this.initialColor.intValue());
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
        int i5 = i3;
        if (i4 < i3) {
            i5 = i4;
        }
        setMeasuredDimension(i5, i5);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        updateColorWheel();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE: {
                int lastSelectedColor = getSelectedColor();
                currentColorCircle = findNearestByPosition(event.getX(), event.getY());
                int selectedColor = getSelectedColor();

                callOnColorChangedListeners(lastSelectedColor, selectedColor);

                initialColor = selectedColor;
                setColorToSliders(selectedColor);
                updateColorWheel();
                invalidate();
                break;
            }
            case MotionEvent.ACTION_UP: {
                int selectedColor = getSelectedColor();
                if (listeners != null) {
                    for (OnColorSelectedListener listener : listeners) {
                        try {
                            listener.onColorSelected(selectedColor);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                setColorToSliders(selectedColor);
                setColorText(selectedColor);
                setColorPreviewColor(selectedColor);
                invalidate();
                break;
            }
        }
        return true;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z2) {
        super.onWindowFocusChanged(z2);
        updateColorWheel();
        this.currentColorCircle = findNearestByColor(this.initialColor.intValue());
    }

    public void setAlphaSlider(AlphaSlider alphaSlider) {
        this.alphaSlider = alphaSlider;
        if (alphaSlider != null) {
            alphaSlider.setColorPicker(this);
            this.alphaSlider.setColor(getSelectedColor());
        }
    }

    public void setAlphaValue(float f) {
        Integer num;
        int selectedColor = getSelectedColor();
        this.alpha = f;
        Integer valueOf = Integer.valueOf(Color.HSVToColor(Utils.alphaValueAsInt(f), this.currentColorCircle.getHsvWithLightness(this.lightness)));
        this.initialColor = valueOf;
        EditText editText = this.colorEdit;
        if (editText != null) {
            editText.setText(Utils.getHexString(valueOf.intValue(), this.alphaSlider != null));
        }
        LightnessSlider lightnessSlider = this.lightnessSlider;
        if (!(lightnessSlider == null || (num = this.initialColor) == null)) {
            lightnessSlider.setColor(num.intValue());
        }
        callOnColorChangedListeners(selectedColor, this.initialColor.intValue());
        updateColorWheel();
        invalidate();
    }

    public void setColor(int i, boolean z2) {
        setInitialColor(i, z2);
        updateColorWheel();
        invalidate();
    }

    public void setColorEdit(EditText editText) {
        this.colorEdit = editText;
        if (editText != null) {
            editText.setVisibility(0);
            this.colorEdit.addTextChangedListener(this.colorTextChange);
            setColorEditTextColor(this.pickerColorEditTextColor.intValue());
        }
    }

    public void setColorEditTextColor(int i) {
        this.pickerColorEditTextColor = Integer.valueOf(i);
        EditText editText = this.colorEdit;
        if (editText != null) {
            editText.setTextColor(i);
        }
    }

    public void setColorPreview(LinearLayout linearLayout, Integer num) {
        if (linearLayout != null) {
            this.colorPreview = linearLayout;
            if (num == null) {
                num = 0;
            }
            int childCount = linearLayout.getChildCount();
            if (childCount != 0 && linearLayout.getVisibility() == 0) {
                for (int i = 0; i < childCount; i++) {
                    View childAt = linearLayout.getChildAt(i);
                    if (childAt instanceof LinearLayout) {
                        LinearLayout linearLayout2 = (LinearLayout) childAt;
                        if (i == num.intValue()) {
                            linearLayout2.setBackgroundColor(-1);
                        }
                        ImageView imageView = (ImageView) linearLayout2.findViewById(16908296);
                        imageView.setClickable(true);
                        imageView.setTag(Integer.valueOf(i));
                        imageView.setOnClickListener(new AnonymousClass2());
                    }
                }
            }
        }
    }

    public void setDensity(int i) {
        this.density = Math.max(2, i);
        invalidate();
    }

    public void setInitialColor(int i, boolean z2) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        this.alpha = Utils.getAlphaPercent(i);
        this.lightness = fArr[2];
        this.initialColors[this.colorSelection] = Integer.valueOf(i);
        this.initialColor = Integer.valueOf(i);
        setColorPreviewColor(i);
        setColorToSliders(i);
        if (this.colorEdit != null && z2) {
            setColorText(i);
        }
        this.currentColorCircle = findNearestByColor(i);
    }

    public void setInitialColors(Integer[] numArr, int i) {
        this.initialColors = numArr;
        this.colorSelection = i;
        Integer num = numArr[i];
        if (num == null) {
            num = -1;
        }
        setInitialColor(num.intValue(), true);
    }

    public void setLightness(float f) {
        Integer num;
        int selectedColor = getSelectedColor();
        this.lightness = f;
        if (this.currentColorCircle != null) {
            Integer valueOf = Integer.valueOf(Color.HSVToColor(Utils.alphaValueAsInt(this.alpha), this.currentColorCircle.getHsvWithLightness(f)));
            this.initialColor = valueOf;
            EditText editText = this.colorEdit;
            if (editText != null) {
                editText.setText(Utils.getHexString(valueOf.intValue(), this.alphaSlider != null));
            }
            AlphaSlider alphaSlider = this.alphaSlider;
            if (!(alphaSlider == null || (num = this.initialColor) == null)) {
                alphaSlider.setColor(num.intValue());
            }
            callOnColorChangedListeners(selectedColor, this.initialColor.intValue());
            updateColorWheel();
            invalidate();
        }
    }

    public void setLightnessSlider(LightnessSlider lightnessSlider) {
        this.lightnessSlider = lightnessSlider;
        if (lightnessSlider != null) {
            lightnessSlider.setColorPicker(this);
            this.lightnessSlider.setColor(getSelectedColor());
        }
    }

    public void setRenderer(ColorWheelRenderer colorWheelRenderer) {
        this.renderer = colorWheelRenderer;
        invalidate();
    }

    public void setSelectedColor(int i) {
        Integer[] numArr = this.initialColors;
        if (numArr != null && numArr.length >= i) {
            this.colorSelection = i;
            setHighlightedColor(i);
            Integer num = this.initialColors[i];
            if (num != null) {
                setColor(num.intValue(), true);
            }
        }
    }

    public void setShowBorder(boolean z2) {
        this.showBorder = z2;
    }
}

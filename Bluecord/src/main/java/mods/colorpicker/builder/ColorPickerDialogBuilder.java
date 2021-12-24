package mods.colorpicker.builder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AlertDialog;
import com.bluecord.R;
import mods.colorpicker.ColorPickerView;
import mods.colorpicker.OnColorChangedListener;
import mods.colorpicker.OnColorSelectedListener;
import mods.colorpicker.Utils;
import mods.colorpicker.slider.AlphaSlider;
import mods.colorpicker.slider.LightnessSlider;
import mods.constants.ColorPickerConstants;

public class ColorPickerDialogBuilder {
    private AlphaSlider alphaSlider;
    private AlertDialog.Builder builder;
    private EditText colorEdit;
    private ColorPickerView colorPickerView;
    private LinearLayout colorPreview;
    private int defaultMargin;
    private int defaultMarginTop;
    private Integer[] initialColor;
    private boolean isAlphaSliderEnabled;
    private boolean isBorderEnabled;
    private boolean isColorEditEnabled;
    private boolean isLightnessSliderEnabled;
    private boolean isPreviewEnabled;
    private LightnessSlider lightnessSlider;
    private LinearLayout pickerContainer;
    private int pickerCount;

    /* renamed from: mods.colorpicker.builder.ColorPickerDialogBuilder$1  reason: invalid class name */
    class AnonymousClass1 implements DialogInterface.OnClickListener {
        final /* synthetic */ ColorPickerClickListener val$onClickListener;

        AnonymousClass1(ColorPickerClickListener colorPickerClickListener) {
            this.val$onClickListener = colorPickerClickListener;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            ColorPickerDialogBuilder.access$000(ColorPickerDialogBuilder.this, dialogInterface, this.val$onClickListener);
        }
    }

    /* renamed from: mods.colorpicker.builder.ColorPickerDialogBuilder$2  reason: invalid class name */
    class AnonymousClass2 implements DialogInterface.OnClickListener {
        final /* synthetic */ ColorPickerClickListener val$onClickListener;

        AnonymousClass2(ColorPickerClickListener colorPickerClickListener) {
            this.val$onClickListener = colorPickerClickListener;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            ColorPickerDialogBuilder.access$000(ColorPickerDialogBuilder.this, dialogInterface, this.val$onClickListener);
        }
    }

    private ColorPickerDialogBuilder(Context context) {
        this(context, 0);
    }

    private ColorPickerDialogBuilder(Context context, int i) {
        this.isLightnessSliderEnabled = true;
        this.isAlphaSliderEnabled = true;
        this.isBorderEnabled = true;
        this.isColorEditEnabled = false;
        this.isPreviewEnabled = false;
        this.pickerCount = 1;
        this.defaultMargin = 0;
        this.defaultMarginTop = 0;
        this.initialColor = new Integer[]{null, null, null, null, null};
        this.defaultMargin = 24;
        this.defaultMarginTop = 20;
        this.builder = new AlertDialog.Builder(context, i);
        LinearLayout linearLayout = new LinearLayout(context);
        this.pickerContainer = linearLayout;
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        this.pickerContainer.setGravity(1);
        LinearLayout linearLayout2 = this.pickerContainer;
        int i2 = this.defaultMargin;
        linearLayout2.setPadding(i2, this.defaultMarginTop, i2, 0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0);
        layoutParams.weight = 1.0f;
        ColorPickerView colorPickerView = new ColorPickerView(context);
        this.colorPickerView = colorPickerView;
        this.pickerContainer.addView(colorPickerView, layoutParams);
        this.builder.setView(this.pickerContainer);
    }

    static /* synthetic */ void access$000(ColorPickerDialogBuilder colorPickerDialogBuilder, DialogInterface dialogInterface, ColorPickerClickListener colorPickerClickListener) {
        colorPickerDialogBuilder.positiveButtonOnClick(dialogInterface, colorPickerClickListener);
    }

    private static int getDimensionAsPx(Context context, int i) {
        return (int) (context.getResources().getDimension(i) + 0.5f);
    }

    private int getStartColor(Integer[] numArr) {
        Integer startOffset = getStartOffset(numArr);
        if (startOffset == null) {
            return -1;
        }
        return numArr[startOffset.intValue()].intValue();
    }

    private Integer getStartOffset(Integer[] numArr) {
        int i = 0;
        int i2 = 0;
        while (i2 < numArr.length && numArr[i2] != null) {
            i = Integer.valueOf((i2 + 1) / 2);
            i2++;
        }
        return i;
    }

    private void positiveButtonOnClick(DialogInterface dialogInterface, ColorPickerClickListener colorPickerClickListener) {
        colorPickerClickListener.onClick(dialogInterface, this.colorPickerView.getSelectedColor(), this.colorPickerView.getAllColors());
    }

    public static ColorPickerDialogBuilder with(Context context) {
        return new ColorPickerDialogBuilder(context);
    }

    public static ColorPickerDialogBuilder with(Context context, int i) {
        return new ColorPickerDialogBuilder(context, i);
    }

    public ColorPickerDialogBuilder alphaSliderOnly() {
        this.isLightnessSliderEnabled = false;
        this.isAlphaSliderEnabled = true;
        return this;
    }

    @SuppressLint("ResourceType")
    public AlertDialog build() {
        Context context = this.builder.getContext();
        ColorPickerView colorPickerView = this.colorPickerView;
        Integer[] numArr = this.initialColor;
        colorPickerView.setInitialColors(numArr, getStartOffset(numArr).intValue());
        this.colorPickerView.setShowBorder(this.isBorderEnabled);
        if (this.isLightnessSliderEnabled) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 36);
            LightnessSlider lightnessSlider = new LightnessSlider(context);
            this.lightnessSlider = lightnessSlider;
            lightnessSlider.setLayoutParams(layoutParams);
            this.pickerContainer.addView(this.lightnessSlider);
            this.colorPickerView.setLightnessSlider(this.lightnessSlider);
            this.lightnessSlider.setColor(getStartColor(this.initialColor));
            this.lightnessSlider.setShowBorder(this.isBorderEnabled);
        }
        if (this.isAlphaSliderEnabled) {
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, 36);
            AlphaSlider alphaSlider = new AlphaSlider(context);
            this.alphaSlider = alphaSlider;
            alphaSlider.setLayoutParams(layoutParams2);
            this.pickerContainer.addView(this.alphaSlider);
            this.colorPickerView.setAlphaSlider(this.alphaSlider);
            this.alphaSlider.setColor(getStartColor(this.initialColor));
            this.alphaSlider.setShowBorder(this.isBorderEnabled);
        }
        if (this.isColorEditEnabled) {
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
            EditText editText = (EditText) View.inflate(context, ColorPickerConstants.blue_color_edit, null);
            this.colorEdit = editText;
            editText.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
            this.colorEdit.setSingleLine();
            this.colorEdit.setVisibility(8);
            this.colorEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(this.isAlphaSliderEnabled ? 9 : 7)});
            this.pickerContainer.addView(this.colorEdit, layoutParams3);
            this.colorEdit.setText(Utils.getHexString(getStartColor(this.initialColor), this.isAlphaSliderEnabled));
            this.colorPickerView.setColorEdit(this.colorEdit);
        }
        if (this.isPreviewEnabled) {
            LinearLayout linearLayout = (LinearLayout) View.inflate(context, ColorPickerConstants.blue_color_preview, null);
            this.colorPreview = linearLayout;
            linearLayout.setVisibility(8);
            this.pickerContainer.addView(this.colorPreview);
            if (this.initialColor.length != 0) {
                int i = 0;
                while (true) {
                    Integer[] numArr2 = this.initialColor;
                    if (i >= numArr2.length || i >= this.pickerCount || numArr2[i] == null) {
                        break;
                    }
                    LinearLayout linearLayout2 = (LinearLayout) View.inflate(context, ColorPickerConstants.blue_color_selector, null);
                    ((ImageView) linearLayout2.findViewById(16908296)).setImageDrawable(new ColorDrawable(this.initialColor[i].intValue()));
                    this.colorPreview.addView(linearLayout2);
                    i++;
                }
            } else {
                ((ImageView) View.inflate(context, ColorPickerConstants.blue_color_selector, null)).setImageDrawable(new ColorDrawable(-1));
            }
            this.colorPreview.setVisibility(0);
            this.colorPickerView.setColorPreview(this.colorPreview, getStartOffset(this.initialColor));
        }
        return this.builder.create();
    }

    public ColorPickerDialogBuilder density(int i) {
        this.colorPickerView.setDensity(i);
        return this;
    }

    public ColorPickerDialogBuilder initialColor(int i) {
        this.initialColor[0] = Integer.valueOf(i);
        return this;
    }

    public ColorPickerDialogBuilder initialColors(int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            Integer[] numArr = this.initialColor;
            if (i >= numArr.length) {
                break;
            }
            numArr[i] = Integer.valueOf(iArr[i]);
        }
        return this;
    }

    public ColorPickerDialogBuilder lightnessSliderOnly() {
        this.isLightnessSliderEnabled = true;
        this.isAlphaSliderEnabled = false;
        return this;
    }

    public ColorPickerDialogBuilder noSliders() {
        this.isLightnessSliderEnabled = false;
        this.isAlphaSliderEnabled = false;
        return this;
    }

    public ColorPickerDialogBuilder setColorEditTextColor(int i) {
        this.colorPickerView.setColorEditTextColor(i);
        return this;
    }

    public ColorPickerDialogBuilder setNegativeButton(int i, DialogInterface.OnClickListener onClickListener) {
        this.builder.setNegativeButton(i, onClickListener);
        return this;
    }

    public ColorPickerDialogBuilder setNegativeButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        this.builder.setNegativeButton(charSequence, onClickListener);
        return this;
    }

    public ColorPickerDialogBuilder setOnColorChangedListener(OnColorChangedListener onColorChangedListener) {
        this.colorPickerView.addOnColorChangedListener(onColorChangedListener);
        return this;
    }

    public ColorPickerDialogBuilder setOnColorSelectedListener(OnColorSelectedListener onColorSelectedListener) {
        this.colorPickerView.addOnColorSelectedListener(onColorSelectedListener);
        return this;
    }

    public ColorPickerDialogBuilder setPickerCount(int i) throws IndexOutOfBoundsException {
        if (i < 1 || i > 5) {
            throw new IndexOutOfBoundsException("Picker Can Only Support 1-5 Colors");
        }
        this.pickerCount = i;
        if (i > 1) {
            this.isPreviewEnabled = true;
        }
        return this;
    }

    public ColorPickerDialogBuilder setPositiveButton(int i, ColorPickerClickListener colorPickerClickListener) {
        this.builder.setPositiveButton(i, new AnonymousClass2(colorPickerClickListener));
        return this;
    }

    public ColorPickerDialogBuilder setPositiveButton(CharSequence charSequence, ColorPickerClickListener colorPickerClickListener) {
        this.builder.setPositiveButton(charSequence, new AnonymousClass1(colorPickerClickListener));
        return this;
    }

    public ColorPickerDialogBuilder setTitle(int i) {
        this.builder.setTitle(i);
        return this;
    }

    public ColorPickerDialogBuilder setTitle(String str) {
        this.builder.setTitle(str);
        return this;
    }

    public ColorPickerDialogBuilder showAlphaSlider(boolean z2) {
        this.isAlphaSliderEnabled = z2;
        return this;
    }

    public ColorPickerDialogBuilder showBorder(boolean z2) {
        this.isBorderEnabled = z2;
        return this;
    }

    public ColorPickerDialogBuilder showColorEdit(boolean z2) {
        this.isColorEditEnabled = z2;
        return this;
    }

    public ColorPickerDialogBuilder showColorPreview(boolean z2) {
        this.isPreviewEnabled = z2;
        if (!z2) {
            this.pickerCount = 1;
        }
        return this;
    }

    public ColorPickerDialogBuilder showLightnessSlider(boolean z2) {
        this.isLightnessSliderEnabled = z2;
        return this;
    }

    public ColorPickerDialogBuilder wheelType(ColorPickerView.WHEEL_TYPE wheel_type) {
        this.colorPickerView.setRenderer(ColorWheelRendererBuilder.getRenderer(wheel_type));
        return this;
    }
}

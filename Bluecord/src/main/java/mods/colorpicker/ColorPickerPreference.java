package mods.colorpicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.bluecord.R;
import mods.colorpicker.ColorPickerView;
import mods.colorpicker.builder.ColorPickerClickListener;
import mods.colorpicker.builder.ColorPickerDialogBuilder;
import mods.constants.ColorPickerConstants;
import mods.constants.Constants;

public class ColorPickerPreference extends Preference {
    protected boolean alphaSlider;
    protected boolean border;
    protected ImageView colorIndicator;
    protected int density;
    protected boolean lightSlider;
    private String pickerButtonCancel;
    private String pickerButtonOk;
    private boolean pickerColorEdit;
    private String pickerTitle;
    protected int selectedColor = 0;
    protected ColorPickerView.WHEEL_TYPE wheelType;

    /* renamed from: mods.colorpicker.ColorPickerPreference$1  reason: invalid class name */
    class AnonymousClass1 implements ColorPickerClickListener {
        AnonymousClass1() {
        }

        @Override // mods.colorpicker.builder.ColorPickerClickListener
        public void onClick(DialogInterface dialogInterface, int i, Integer[] numArr) {
            ColorPickerPreference.this.setValue(i);
        }
    }

    public ColorPickerPreference(Context context) {
        super(context);
    }

    public ColorPickerPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initWith(context, attributeSet);
    }

    public ColorPickerPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initWith(context, attributeSet);
    }

    public static int darken(int i, float f) {
        return Color.argb(Color.alpha(i), Math.max((int) (((float) Color.red(i)) * f), 0), Math.max((int) (((float) Color.green(i)) * f), 0), Math.max((int) (((float) Color.blue(i)) * f), 0));
    }

    @SuppressLint("ResourceType")
    private void initWith(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ColorPickerConstants.getDeclaredPickerAttributes());
        try {
            this.alphaSlider = obtainStyledAttributes.getBoolean(0, false);
            this.lightSlider = obtainStyledAttributes.getBoolean(5, false);
            this.border = obtainStyledAttributes.getBoolean(2, true);
            this.density = obtainStyledAttributes.getInt(3, 8);
            this.wheelType = ColorPickerView.WHEEL_TYPE.indexOf(obtainStyledAttributes.getInt(12, 0));
            this.selectedColor = obtainStyledAttributes.getInt(4, -1);
            this.pickerColorEdit = obtainStyledAttributes.getBoolean(9, true);
            String string = obtainStyledAttributes.getString(11);
            this.pickerTitle = string;
            if (string == null) {
                this.pickerTitle = "Choose color";
            }
            String string2 = obtainStyledAttributes.getString(7);
            this.pickerButtonCancel = string2;
            if (string2 == null) {
                this.pickerButtonCancel = "cancel";
            }
            String string3 = obtainStyledAttributes.getString(8);
            this.pickerButtonOk = string3;
            if (string3 == null) {
                this.pickerButtonOk = "ok";
            }
            obtainStyledAttributes.recycle();
            setWidgetLayoutResource(ColorPickerConstants.blue_color_widget);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    private static int safeCast(Object obj) {
        try {
            return obj instanceof Integer ? ((Integer) obj).intValue() : Color.parseColor(obj.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // android.preference.Preference
    protected void onBindView(View view) {
        super.onBindView(view);
        int darken = isEnabled() ? this.selectedColor : darken(this.selectedColor, 0.5f);
        @SuppressLint("ResourceType") ImageView imageView = (ImageView) view.findViewById(16908295);
        this.colorIndicator = imageView;
        ColorCircleDrawable colorCircleDrawable = null;
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof ColorCircleDrawable) {
            colorCircleDrawable = (ColorCircleDrawable) drawable;
        }
        if (colorCircleDrawable == null) {
            colorCircleDrawable = new ColorCircleDrawable(darken);
        }
        this.colorIndicator.setImageDrawable(colorCircleDrawable);
    }

    @Override // android.preference.Preference
    protected void onClick() {
        ColorPickerDialogBuilder negativeButton = ColorPickerDialogBuilder.with(getContext()).setTitle(this.pickerTitle).initialColor(this.selectedColor).showBorder(this.border).wheelType(this.wheelType).density(this.density).showColorEdit(this.pickerColorEdit).setPositiveButton(this.pickerButtonOk, new AnonymousClass1()).setNegativeButton(this.pickerButtonCancel, (DialogInterface.OnClickListener) null);
        boolean z2 = this.alphaSlider;
        if (!z2 && !this.lightSlider) {
            negativeButton.noSliders();
        } else if (!z2) {
            negativeButton.lightnessSliderOnly();
        } else if (!this.lightSlider) {
            negativeButton.alphaSliderOnly();
        }
        negativeButton.build().show();
    }

    @Override // android.preference.Preference
    protected void onSetInitialValue(boolean z2, Object obj) {
        setValue(z2 ? getPersistedInt(0) : safeCast(obj));
    }

    public void setValue(int i) {
        if (callChangeListener(Integer.valueOf(i))) {
            this.selectedColor = i;
            persistInt(i);
            notifyChanged();
        }
    }
}

package mods.colorpicker.builder;

import mods.colorpicker.ColorPickerView;
import mods.colorpicker.renderer.ColorWheelRenderer;
import mods.colorpicker.renderer.FlowerColorWheelRenderer;
import mods.colorpicker.renderer.SimpleColorWheelRenderer;
public class ColorWheelRendererBuilder {

    /* renamed from: mods.colorpicker.builder.ColorWheelRendererBuilder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$mods$colorpicker$ColorPickerView$WHEEL_TYPE;

        static {
            int[] iArr = new int[ColorPickerView.WHEEL_TYPE.values().length];
            $SwitchMap$mods$colorpicker$ColorPickerView$WHEEL_TYPE = iArr;
            try {
                iArr[ColorPickerView.WHEEL_TYPE.CIRCLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$mods$colorpicker$ColorPickerView$WHEEL_TYPE[ColorPickerView.WHEEL_TYPE.FLOWER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public static ColorWheelRenderer getRenderer(ColorPickerView.WHEEL_TYPE wheel_type) {
        int i = AnonymousClass1.$SwitchMap$mods$colorpicker$ColorPickerView$WHEEL_TYPE[wheel_type.ordinal()];
        if (i == 1) {
            return new SimpleColorWheelRenderer();
        }
        if (i == 2) {
            return new FlowerColorWheelRenderer();
        }
        throw new IllegalArgumentException("wrong WHEEL_TYPE");
    }
}

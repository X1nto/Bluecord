package mods.colorpicker.renderer;

import java.util.List;
import mods.colorpicker.ColorCircle;
public interface ColorWheelRenderer {
    public static final float GAP_PERCENTAGE = 0.025f;

    void draw();

    List<ColorCircle> getColorCircleList();

    ColorWheelRenderOption getRenderOption();

    void initWith(ColorWheelRenderOption colorWheelRenderOption);
}

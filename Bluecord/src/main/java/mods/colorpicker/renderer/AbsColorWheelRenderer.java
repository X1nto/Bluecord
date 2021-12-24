package mods.colorpicker.renderer;

import java.util.ArrayList;
import java.util.List;
import mods.colorpicker.ColorCircle;
public abstract class AbsColorWheelRenderer implements ColorWheelRenderer {
    protected List<ColorCircle> colorCircleList = new ArrayList();
    protected ColorWheelRenderOption colorWheelRenderOption;

    protected int calcTotalCount(float f, float f2) {
        return Math.max(1, (int) ((3.063052912151454d / Math.asin((double) (f2 / f))) + 0.5d));
    }

    protected int getAlphaValueAsInt() {
        return Math.round(this.colorWheelRenderOption.alpha * 255.0f);
    }

    @Override // mods.colorpicker.renderer.ColorWheelRenderer
    public List<ColorCircle> getColorCircleList() {
        return this.colorCircleList;
    }

    @Override // mods.colorpicker.renderer.ColorWheelRenderer
    public ColorWheelRenderOption getRenderOption() {
        if (this.colorWheelRenderOption == null) {
            this.colorWheelRenderOption = new ColorWheelRenderOption();
        }
        return this.colorWheelRenderOption;
    }

    @Override // mods.colorpicker.renderer.ColorWheelRenderer
    public void initWith(ColorWheelRenderOption colorWheelRenderOption) {
        this.colorWheelRenderOption = colorWheelRenderOption;
        this.colorCircleList.clear();
    }
}

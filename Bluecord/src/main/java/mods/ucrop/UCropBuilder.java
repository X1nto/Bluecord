package mods.ucrop;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import androidx.core.view.ViewCompat;
import com.yalantis.ucrop.model.AspectRatio;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class UCropBuilder {
    private final Bundle options = new Bundle();

    public UCropBuilder() {
        setBaseOptions();
    }

    private void setBaseOptions() {
        new Bundle();
        setCompressionQuality(90);
        setCompressionFormat(Bitmap.CompressFormat.JPEG);
        setActiveControlsWidgetColor(ViewCompat.MEASURED_STATE_MASK);
        setLogoColor(-12303292);
        setStatusBarColor(Color.parseColor("#ff000000"));
        setToolbarColor(Color.parseColor("#ff111111"));
        setToolbarWidgetColor(-1);
        setRootViewBackgroundColor(Color.parseColor("#ff000000"));
        setImageToCropBoundsAnimDuration(192);
        setShowCropGrid(false);
    }

    public Bundle getOptions() {
        return this.options;
    }

    public void setActiveControlsWidgetColor(int i) {
        this.options.putInt(UCropConstants.Options.EXTRA_UCROP_COLOR_CONTROLS_WIDGET_ACTIVE, i);
    }

    public void setAspectRatioOptions(int i, AspectRatio... aspectRatioArr) {
        if (i <= aspectRatioArr.length) {
            this.options.putInt(UCropConstants.Options.EXTRA_ASPECT_RATIO_SELECTED_BY_DEFAULT, i);
            this.options.putParcelableArrayList(UCropConstants.Options.EXTRA_ASPECT_RATIO_OPTIONS, new ArrayList<>(Arrays.asList(aspectRatioArr)));
            return;
        }
        throw new IllegalArgumentException(String.format(Locale.US, "Index [selectedByDefault = %d] cannot be higher than aspect ratio options count [count = %d].", Integer.valueOf(i), Integer.valueOf(aspectRatioArr.length)));
    }

    public void setCircleDimmedLayer(boolean z2) {
        this.options.putBoolean(UCropConstants.Options.EXTRA_CIRCLE_DIMMED_LAYER, z2);
    }

    public void setCompressionFormat(Bitmap.CompressFormat compressFormat) {
        this.options.putString(UCropConstants.Options.EXTRA_COMPRESSION_FORMAT_NAME, compressFormat.name());
    }

    public void setCompressionQuality(int i) {
        this.options.putInt(UCropConstants.Options.EXTRA_COMPRESSION_QUALITY, i);
    }

    public void setCropFrameColor(int i) {
        this.options.putInt(UCropConstants.Options.EXTRA_CROP_FRAME_COLOR, i);
    }

    public void setCropFrameStrokeWidth(int i) {
        this.options.putInt(UCropConstants.Options.EXTRA_CROP_FRAME_STROKE_WIDTH, i);
    }

    public void setCropGridColor(int i) {
        this.options.putInt(UCropConstants.Options.EXTRA_CROP_GRID_COLOR, i);
    }

    public void setCropGridColumnCount(int i) {
        this.options.putInt(UCropConstants.Options.EXTRA_CROP_GRID_COLUMN_COUNT, i);
    }

    public void setCropGridCornerColor(int i) {
        this.options.putInt(UCropConstants.Options.EXTRA_CROP_GRID_CORNER_COLOR, i);
    }

    public void setCropGridRowCount(int i) {
        this.options.putInt(UCropConstants.Options.EXTRA_CROP_GRID_ROW_COUNT, i);
    }

    public void setCropGridStrokeWidth(int i) {
        this.options.putInt(UCropConstants.Options.EXTRA_CROP_GRID_STROKE_WIDTH, i);
    }

    public void setDimmedLayerColor(int i) {
        this.options.putInt(UCropConstants.Options.EXTRA_DIMMED_LAYER_COLOR, i);
    }

    public void setFreeStyleCropEnabled(boolean z2) {
        this.options.putBoolean(UCropConstants.Options.EXTRA_FREE_STYLE_CROP, z2);
    }

    public void setHideBottomControls(boolean z2) {
        this.options.putBoolean(UCropConstants.Options.EXTRA_HIDE_BOTTOM_CONTROLS, z2);
    }

    public void setImageToCropBoundsAnimDuration(int i) {
        this.options.putInt(UCropConstants.Options.EXTRA_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, i);
    }

    public void setLogoColor(int i) {
        this.options.putInt(UCropConstants.Options.EXTRA_UCROP_LOGO_COLOR, i);
    }

    public void setMaxBitmapSize(int i) {
        this.options.putInt(UCropConstants.Options.EXTRA_MAX_BITMAP_SIZE, i);
    }

    public void setMaxScaleMultiplier(float f) {
        this.options.putFloat(UCropConstants.Options.EXTRA_MAX_SCALE_MULTIPLIER, f);
    }

    public void setRootViewBackgroundColor(int i) {
        this.options.putInt(UCropConstants.Options.EXTRA_UCROP_ROOT_VIEW_BACKGROUND_COLOR, i);
    }

    public void setShowCropFrame(boolean z2) {
        this.options.putBoolean(UCropConstants.Options.EXTRA_SHOW_CROP_FRAME, z2);
    }

    public void setShowCropGrid(boolean z2) {
        this.options.putBoolean(UCropConstants.Options.EXTRA_SHOW_CROP_GRID, z2);
    }

    public void setStatusBarColor(int i) {
        this.options.putInt(UCropConstants.Options.EXTRA_STATUS_BAR_COLOR, i);
    }

    public void setToolbarCancelDrawable(int i) {
        this.options.putInt(UCropConstants.Options.EXTRA_UCROP_WIDGET_CANCEL_DRAWABLE, i);
    }

    public void setToolbarColor(int i) {
        this.options.putInt(UCropConstants.Options.EXTRA_TOOL_BAR_COLOR, i);
    }

    public void setToolbarCropDrawable(int i) {
        this.options.putInt(UCropConstants.Options.EXTRA_UCROP_WIDGET_CROP_DRAWABLE, i);
    }

    public void setToolbarTitle(String str) {
        this.options.putString(UCropConstants.Options.EXTRA_UCROP_TITLE_TEXT_TOOLBAR, str);
    }

    public void setToolbarWidgetColor(int i) {
        this.options.putInt(UCropConstants.Options.EXTRA_UCROP_WIDGET_COLOR_TOOLBAR, i);
    }

    public void withMaxResultSize(int i, int i2) {
        if (i < 10) {
            i = 10;
        }
        if (i2 < 10) {
            i2 = 10;
        }
        this.options.putInt(UCropConstants.EXTRA_MAX_SIZE_X, i);
        this.options.putInt(UCropConstants.EXTRA_MAX_SIZE_Y, i2);
    }
}

package mods.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import mods.constants.Constants;

public class ActionBar extends LinearLayout {
    private ImageView backButton;
    private TextView titleView;

    public ActionBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initBar(attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "title"), null);
    }

    public ActionBar(Context context, String str, View.OnClickListener onClickListener) {
        super(context);
        initBar(str, onClickListener);
    }

    private int dpToPx(int i) {
        return Math.round(TypedValue.applyDimension(1, (float) i, getResources().getDisplayMetrics()));
    }

    private void initBar(String str, View.OnClickListener onClickListener) {
        this.backButton = new ImageView(getContext());
        this.backButton.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), Constants.blue_back_arrow), dpToPx(20), dpToPx(20), true));
        this.backButton.setScaleType(ImageView.ScaleType.CENTER);
        this.backButton.setLayoutParams(new LinearLayout.LayoutParams(dpToPx(56), dpToPx(56)));
        if (onClickListener != null) {
            this.backButton.setOnClickListener(onClickListener);
        }
        addView(this.backButton);
        TextView textView = new TextView(getContext());
        this.titleView = textView;
        textView.setLayoutParams(new ViewGroup.LayoutParams(-2, dpToPx(56)));
        this.titleView.setTextSize(2, 20.0f);
        this.titleView.setGravity(16);
        this.titleView.setText(str);
        addView(this.titleView);
        if (Build.VERSION.SDK_INT >= 21) {
            setElevation((float) dpToPx(4));
        }
    }

    public ImageView getBackButtonView() {
        return this.backButton;
    }

    public void setTitleTextColor(int i) {
        this.titleView.setTextColor(i);
    }
}

package mods.view;

import android.os.Handler;
import android.view.View;
public class DoubleClick implements View.OnClickListener {
    private int clicks;
    private final DoubleClickListener doubleClickListener;
    private final long interval = 250;
    private boolean isBusy = false;
    private final Handler mHandler = new Handler();

    public DoubleClick(DoubleClickListener doubleClickListener) {
        this.doubleClickListener = doubleClickListener;
    }

    public /* synthetic */ void lambda$onClick$0$DoubleClick(View view) {
        if (this.clicks >= 2) {
            this.doubleClickListener.onDoubleClick(view);
        }
        if (this.clicks == 1) {
            this.doubleClickListener.onSingleClick(view);
        }
        this.clicks = 0;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (!this.isBusy) {
            this.isBusy = true;
            this.clicks++;
            this.mHandler.postDelayed(new $$Lambda$DoubleClick$yjlMsdxD8biFL2OJAGdviDQ6mlc(this, view), 250);
            this.isBusy = false;
        }
    }
}

package mods.view;

import android.animation.ValueAnimator;
import android.widget.TextView;
/* compiled from: lambda */
/* renamed from: mods.view.-$$Lambda$Colors$uec_VH7JPOn01z1R1YEiFjhv49Y  reason: invalid class name */
public final /* synthetic */ class $$Lambda$Colors$uec_VH7JPOn01z1R1YEiFjhv49Y implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ TextView f$0;

    public /* synthetic */ $$Lambda$Colors$uec_VH7JPOn01z1R1YEiFjhv49Y(TextView textView) {
        this.f$0 = textView;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        Colors.lambda$animateTextView$1(this.f$0, valueAnimator);
    }
}

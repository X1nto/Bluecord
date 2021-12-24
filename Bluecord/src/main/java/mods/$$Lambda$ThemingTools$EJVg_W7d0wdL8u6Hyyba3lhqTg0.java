package mods;

import android.animation.ValueAnimator;
import android.widget.TextView;
/* compiled from: lambda */
/* renamed from: mods.-$$Lambda$ThemingTools$EJVg_W7d0wdL8u6Hyyba3lhqTg0  reason: invalid class name */
public final /* synthetic */ class $$Lambda$ThemingTools$EJVg_W7d0wdL8u6Hyyba3lhqTg0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ TextView f$0;

    public /* synthetic */ $$Lambda$ThemingTools$EJVg_W7d0wdL8u6Hyyba3lhqTg0(TextView textView) {
        this.f$0 = textView;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        ThemingTools.lambda$setWelcomeText$0(this.f$0, valueAnimator);
    }
}

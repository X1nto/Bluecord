package mods.view;

import android.animation.ValueAnimator;
import android.widget.EditText;
/* compiled from: lambda */
/* renamed from: mods.view.-$$Lambda$Colors$WzQfqEsSi62CDePbkRZBbrsgsDY  reason: invalid class name */
public final /* synthetic */ class $$Lambda$Colors$WzQfqEsSi62CDePbkRZBbrsgsDY implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ EditText f$0;

    public /* synthetic */ $$Lambda$Colors$WzQfqEsSi62CDePbkRZBbrsgsDY(EditText editText) {
        this.f$0 = editText;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        Colors.lambda$animateEditText$0(this.f$0, valueAnimator);
    }
}

package mods.preference;

import android.content.DialogInterface;
import mods.colorpicker.builder.ColorPickerClickListener;
/* compiled from: lambda */
/* renamed from: mods.preference.-$$Lambda$ColorPicker$eG3S41cC3pbnNCe7GWzvVpGB4i0  reason: invalid class name */
public final /* synthetic */ class $$Lambda$ColorPicker$eG3S41cC3pbnNCe7GWzvVpGB4i0 implements ColorPickerClickListener {
    public final /* synthetic */ ColorPicker f$0;

    public /* synthetic */ $$Lambda$ColorPicker$eG3S41cC3pbnNCe7GWzvVpGB4i0(ColorPicker colorPicker) {
        this.f$0 = colorPicker;
    }

    @Override // mods.colorpicker.builder.ColorPickerClickListener
    public final void onClick(DialogInterface dialogInterface, int i, Integer[] numArr) {
        this.f$0.lambda$onClick$0$ColorPicker(dialogInterface, i, numArr);
    }
}

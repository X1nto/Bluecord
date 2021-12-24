package mods.preference;

import java.io.File;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: mods.preference.-$$Lambda$CustomFont$-lURVnxDzXUKcSBJUFzNkB3-b2U  reason: invalid class name */
public final /* synthetic */ class $$Lambda$CustomFont$lURVnxDzXUKcSBJUFzNkB3b2U implements Comparator {
    public static final /* synthetic */ $$Lambda$CustomFont$lURVnxDzXUKcSBJUFzNkB3b2U INSTANCE = new $$Lambda$CustomFont$lURVnxDzXUKcSBJUFzNkB3b2U();

    private /* synthetic */ $$Lambda$CustomFont$lURVnxDzXUKcSBJUFzNkB3b2U() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return CustomFont.lambda$loadFontFromFile$3((File) obj, (File) obj2);
    }
}

package mods.view;

import android.content.Context;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import com.discord.simpleast.core.node.StyleNode;
import java.util.ArrayList;
import java.util.List;

public class DeletedMessageNode extends StyleNode {
    public DeletedMessageNode(Context context) {
        super(getRenderInstructions());
        addChild(new b.a.t.b.a.a(" (DELETED)"));
    }

    private static List<CharacterStyle> getRenderInstructions() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(new RelativeSizeSpan(0.75f));
        arrayList.add(new ForegroundColorSpan(Colors.getDeletedMessageColor()));
        return arrayList;
    }
}

package mods.view;

import android.content.Context;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import com.discord.simpleast.core.node.StyleNode;
import java.util.ArrayList;
import java.util.List;

public class PrependEditNode extends StyleNode {
    public PrependEditNode(Context context, String str) {
        super(getRenderInstructions());
        addChild(new c.a.t.b.a.a(str));
    }

    private static List<CharacterStyle> getRenderInstructions() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new ForegroundColorSpan(Colors.getDefaultEditedColor()));
        return arrayList;
    }
}

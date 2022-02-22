package mods.parser;

import java.util.ArrayList;
import java.util.Iterator;
import mods.utils.LRUCache;

public class RateLimiter {
    private static final int SPAM_LOWER_THRESHOLD = 350;
    private static final int SPAM_UPPER_THRESHOLD = 4500;
    public static final RateLimiter inst = new RateLimiter();
    private final Object lock = new Object();
    private final LRUCache<Long, ArrayList<Long>> rateLimiter = new LRUCache<>(200);

    public void clear() {
        synchronized (this.lock) {
            this.rateLimiter.clear();
        }
    }

    public boolean isSpam(Long l, Long l2) {
        boolean z2;
        synchronized (this.lock) {
            z2 = false;
            ArrayList<Long> arrayList = this.rateLimiter.get(l);
            if (arrayList != null) {
                arrayList.add(0, l2);
                int size = arrayList.size();
                if (size >= 5) {
                    long j = 0;
                    Iterator<Long> it = arrayList.iterator();
                    while (it.hasNext()) {
                        j += it.next().longValue();
                    }
                    long longValue = ((l2.longValue() * ((long) size)) - j) / ((long) size);
                    if (longValue > 350 && longValue < 4500) {
                        z2 = true;
                    }
                    if (size >= 10 || z2) {
                        this.rateLimiter.remove(l);
                    }
                }
            } else {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(0, l2);
                this.rateLimiter.put(l, arrayList2);
            }
        }
        return z2;
    }
}

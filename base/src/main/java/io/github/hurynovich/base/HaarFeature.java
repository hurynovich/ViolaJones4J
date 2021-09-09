package io.github.hurynovich.base;

import java.util.Set;

public final class HaarFeature {
    private final Rect[] positive;
    private final Rect[] negative;

    HaarFeature(Set<Rect> positive, Set<Rect> negative){
        this.positive = positive.toArray(new Rect[]{});
        this.negative = negative.toArray(new Rect[]{});
    }

    Value calcValue(IntegralImg iImg) {
        int result = 0;
        for (Rect r : positive) result += iImg.getValue(r);
        for (Rect r : negative) result -= iImg.getValue(r);

        return new Value(result);
    }
}

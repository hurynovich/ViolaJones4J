package io.github.hurynovich.base;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
public final class HaarFeature {
    private final List<Part> parts;

    double calcValue(IntegralImg iImg, Int2D shift) {
        int result = 0;
        for (Part p : parts) {
            var val = iImg.getSum(p.area.translate(shift));
            result += val * p.factor;
        }

        return result;
    }

    @AllArgsConstructor
    static class Part {
        public final Rect area;
        public final double factor;
    }
}

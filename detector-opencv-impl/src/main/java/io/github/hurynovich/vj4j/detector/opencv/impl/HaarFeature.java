package io.github.hurynovich.vj4j.detector.opencv.impl;

import io.github.hurynovich.vj4j.commons.Point;
import io.github.hurynovich.vj4j.commons.Rect;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public final class HaarFeature {
    private final List<Part> parts;

    double calcValue(IntegralImg iImg, Point shift) {
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

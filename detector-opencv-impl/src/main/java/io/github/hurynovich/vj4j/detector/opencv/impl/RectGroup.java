package io.github.hurynovich.vj4j.detector.opencv.impl;

import io.github.hurynovich.vj4j.detector.api.Rect;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static io.github.hurynovich.vj4j.detector.api.Rect.rect;
import static java.lang.Math.round;

class RectGroup {
    private final List<Rect> rects = new ArrayList<>();
    private final double minOverlap;

    @Getter
    private Rect intersection;

    public RectGroup(Rect rect, double minOverlap) {
        this.minOverlap = minOverlap;
        this.intersection = rect;
        this.rects.add(rect);
    }

    public int count(){
        return rects.size();
    }

    public boolean addSection(final Rect rect){
        var result = Rect.intersection(intersection, rect);
        if (result == null) return false;

        //intersection area must be at least 25%
        double percent = 100.0 * result.area() / rect.area();
        if(percent < minOverlap) return false;

        rects.add(rect);
        intersection = result;
        return true;
    }

    public Rect calcCommon() {
        double x1 = 0, y1 = 0;
        double x2 = 0, y2 = 0;

        for (var s : rects) {
            x1 += s.a.x;
            y1 += s.a.y;
            x2 += s.b.x;
            y2 += s.b.y;
        }

        double size = rects.size();
        x1 /= size;
        y1 /= size;
        x2 /= size;
        y2 /= size;

        return rect((int)round(x1), (int)round(y1), (int)round(x2), (int)round(y2));
    }
}

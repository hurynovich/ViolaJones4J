package io.github.hurynovich.vj4j.detector.opencv.impl;

import io.github.hurynovich.vj4j.commons.Rect;

import java.util.List;

public interface RectMerger {
    List<Rect> merge(List<Rect> rects);
}

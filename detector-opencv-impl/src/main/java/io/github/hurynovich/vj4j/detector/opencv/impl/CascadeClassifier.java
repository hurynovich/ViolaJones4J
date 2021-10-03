package io.github.hurynovich.vj4j.detector.opencv.impl;

import io.github.hurynovich.vj4j.commons.Point;
import lombok.Getter;

import java.util.List;


class CascadeClassifier {
    private final List<StrongHaarClassifier> stages;
    private final Point orgSpotSize;

    @Getter
    private Point windowSize;

    public CascadeClassifier(List<StrongHaarClassifier> stages, Point nativeWindowSize) {
        this.stages = stages;
        this.orgSpotSize = nativeWindowSize;
        this.windowSize = nativeWindowSize;
    }

    public boolean detect(IntegralImg integralImg, double valueFactor, Point pos) {
        for (var stage : stages) {
            if (!stage.detect(integralImg, valueFactor, pos)) return false;
        }
        return true;
    }
}

package io.github.hurynovich.base;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


class CascadeClassifier {
    private final List<StrongHaarClassifier> stages;
    private final Int2D nativeWindowSize;

    @Getter
    private Int2D windowSize;

    CascadeClassifier(List<StrongHaarClassifier> stages, Int2D nativeWindowSize) {
        this.stages = stages;
        this.nativeWindowSize = nativeWindowSize;
        this.windowSize = nativeWindowSize;
    }

    public boolean detect(IntegralImg integralImg, double valueFactor, Int2D pos) {
        for (var stage : stages) {
            if (!stage.detect(integralImg, valueFactor, pos)) return false;
        }
        return true;
    }
}

package io.github.hurynovich.base;

import java.util.List;

public class CascadeClassifier {
    private List<StrongHaarClassifier> stages;

    public boolean doDetection(Image image) {
        for (var stage : stages) {
            if (!stage.doDetection(image)) return false;
        }
        return true;
    }
}

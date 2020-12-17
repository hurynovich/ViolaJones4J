package io.github.hurynovich.violajones;

import java.awt.*;
import java.util.List;

class CascadeClassifierDetector implements Detector {

    private final List<HaarFeature> features;

    CascadeClassifierDetector(List<HaarFeature> features) {
        this.features = features;
    }

    @Override
    public List<Rect> detect(Image image) {
        return null;
    }
}

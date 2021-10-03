package io.github.hurynovich.vj4j.detector.api;

import io.github.hurynovich.vj4j.core.api.Image;
import io.github.hurynovich.vj4j.core.api.Rect;

import java.util.List;

public interface Detector {
    List<? extends Rect> detect(Image image, Settings settings) throws DetectorException;
}

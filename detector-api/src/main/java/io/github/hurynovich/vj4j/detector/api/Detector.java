package io.github.hurynovich.vj4j.detector.api;

import java.util.List;

public interface Detector {
    List<? extends Rect> detect(Image image, Settings settings) throws DetectorException;
}

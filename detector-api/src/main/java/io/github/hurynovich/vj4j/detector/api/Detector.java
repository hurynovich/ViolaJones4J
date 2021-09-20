package io.github.hurynovich.vj4j.detector.api;

import java.awt.image.BufferedImage;
import java.util.List;

public interface Detector {
    //TODO replace BufferedImage with Image interface
    List<Rect> detect(BufferedImage image, Settings settings) throws DetectorException;
}

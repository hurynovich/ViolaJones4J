package io.github.hurynovich.vj4j.base;

import java.awt.image.BufferedImage;
import java.util.List;

public interface Detector {
    List<Rect> detect(BufferedImage image);
}

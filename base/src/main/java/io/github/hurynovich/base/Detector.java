package io.github.hurynovich.base;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

public interface Detector {
    List<Rect> detect(BufferedImage image);
}

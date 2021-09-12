package io.github.hurynovich.base;

import java.awt.Image;
import java.util.List;

public interface Detector {
    List<Rect> detect(Image image);
}

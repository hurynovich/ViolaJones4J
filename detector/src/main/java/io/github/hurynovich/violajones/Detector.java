package io.github.hurynovich.violajones;

import java.awt.*;
import java.util.List;

/**
 * This interface describes detector which analyses given image
 * and returns list of coordinates where objects of interest were detected.
 * Coordinates here means rectangle area  within source image.
 */
public interface Detector {
    List<Rect> detect(Image image);
}

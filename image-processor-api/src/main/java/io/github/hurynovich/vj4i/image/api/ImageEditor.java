package io.github.hurynovich.vj4i.image.api;

import io.github.hurynovich.vj4j.core.api.Image;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

//TODO javadoc
public interface ImageEditor {
    Image read(Path source);
    Image read(InputStream source);
    void write(Image img, Path target);
    void write(OutputStream target);
    Image newImage(int width, int height);
    Image scale(Image image, int targetWidth, int targetHeight);
    void drawLine(Image image, int x1, int y1, int x2, int y2, int color);
    boolean canEdit(Image source);
}

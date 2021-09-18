package io.github.hurynovich.vj4j.detector.opencv.impl.util;

import io.github.hurynovich.vj4j.detector.api.Image;
import io.github.hurynovich.vj4j.detector.api.Int2D;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public final class AwtImageWrapper implements Image {
    private final BufferedImage source;

    public AwtImageWrapper(BufferedImage source) {
        this.source = source;
    }

    @Override
    public int getRGB(int x, int y) {
        return source.getRGB(x, y);
    }

    @Override
    public int getWidth() {
        return source.getWidth();
    }

    @Override
    public int getHeight() {
        return source.getHeight();
    }

    public static AwtImageWrapper scale(AwtImageWrapper sourceImg, int targetWidth, int targetHeight){
        BufferedImage resized = new BufferedImage(targetWidth, targetHeight, TYPE_INT_RGB);
        Graphics2D graphics2D = resized.createGraphics();
        graphics2D.drawImage(sourceImg.source, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return new AwtImageWrapper(resized);
    }

    public static AwtImageWrapper scale(AwtImageWrapper img, Int2D targetSize) {
        return scale(img, targetSize.x, targetSize.y);
    }
}
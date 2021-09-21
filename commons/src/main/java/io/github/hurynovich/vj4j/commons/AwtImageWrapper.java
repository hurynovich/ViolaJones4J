package io.github.hurynovich.vj4j.commons;

import io.github.hurynovich.vj4j.detector.api.Image;
import io.github.hurynovich.vj4j.detector.api.Point;

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

    public Image scale(Point targetSz){
        BufferedImage resized = new BufferedImage(targetSz.x, targetSz.y, TYPE_INT_RGB);
        Graphics2D graphics2D = resized.createGraphics();
        graphics2D.drawImage(this.source, 0, 0, targetSz.x, targetSz.y, null);
        graphics2D.dispose();
        return new AwtImageWrapper(resized);
    }
}

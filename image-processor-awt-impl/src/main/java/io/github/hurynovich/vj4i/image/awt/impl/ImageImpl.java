package io.github.hurynovich.vj4i.image.awt.impl;

import io.github.hurynovich.vj4j.core.api.Image;
import io.github.hurynovich.vj4j.core.api.Point;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

//TODO add javadoc
class ImageImpl implements Image {
    private final BufferedImage data;

    ImageImpl(int width, int height) {
        data = new BufferedImage(width, height, TYPE_INT_RGB);
    }

    public ImageImpl(BufferedImage data) {
        this.data = data;
    }

    @Override
    public int getRGB(int x, int y) {
        return data.getRGB(x, y);
    }

    @Override
    public int getWidth() {
        return data.getWidth();
    }

    @Override
    public int getHeight() {
        return data.getHeight();
    }

    @Override
    public Image scale(Point targetSz) {
        BufferedImage resizedImage = new BufferedImage(targetSz.getX(), targetSz.getY(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(data, 0, 0, targetSz.getX(), targetSz.getY(), null);
        graphics2D.dispose();
        return new ImageImpl(resizedImage);
    }

    BufferedImage getData() {
        return data;
    }
}

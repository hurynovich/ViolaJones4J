package io.github.hurynovich.base;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

class ImageWrapper implements Image {
    private final BufferedImage data;
    ImageWrapper(BufferedImage source){
        this.data = source;
    }

    @Override
    public int getRGB(Int2D pos) {
        return data.getRGB(pos.x, pos.y);
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
}

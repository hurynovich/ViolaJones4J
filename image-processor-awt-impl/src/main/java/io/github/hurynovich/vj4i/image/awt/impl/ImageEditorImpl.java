package io.github.hurynovich.vj4i.image.awt.impl;

import io.github.hurynovich.vj4i.image.api.ImageEditor;
import io.github.hurynovich.vj4j.core.api.Image;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

public class ImageEditorImpl implements ImageEditor {

    @Override
    public Image read(Path source) {
        try {
            var data = ImageIO.read(source.toFile());
            return new ImageImpl(data);
        } catch (IOException e) {
            //TODO
            throw new RuntimeException("Failed to read", e);
        }
    }

    @Override
    public Image read(InputStream source) {
        try {
            var data = ImageIO.read(source);
            return new ImageImpl(data);
        } catch (IOException e) {
            //TODO
            throw new RuntimeException("Failed to read", e);
        }
    }

    @Override
    public void write(Image img, Path target) {
        //TODO
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void write(OutputStream target) {
        //TODO
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Image newImage(int width, int height) {
        return new ImageImpl(width, height);
    }

    @Override
    public Image scale(Image source, int targetWidth, int targetHeight) {
        var data = getData(source);
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, data.getType());
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(data, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return new ImageImpl(resizedImage);
    }

    @Override
    public void drawLine(Image image, int x1, int y1, int x2, int y2, int rgbColor) {
        var g = getData(image).getGraphics();
        g.setColor(new Color(rgbColor));
        g.drawLine(x1, y1, x2, y2);
    }

    private BufferedImage getData(Image image){
        if(image instanceof ImageImpl) {
            return ((ImageImpl) image).getData();
        } else {
            //TODO use custom exception and give better message
            throw new RuntimeException("This image instance is not compatible with this editor.");
        }
    }
}

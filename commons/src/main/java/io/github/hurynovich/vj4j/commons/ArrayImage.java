package io.github.hurynovich.vj4j.commons;

import io.github.hurynovich.vj4j.core.api.Image;
import io.github.hurynovich.vj4j.core.api.Point;

//TODO experimental class
@Deprecated(forRemoval = false)
public class ArrayImage implements Image {
    private final int[][] data;

    ArrayImage(Point size){
        this(size.getX(), size.getY());
    }

    ArrayImage(int width, int height){
        data = new int[height][width];
    }

    public void setRGB(int val, int x, int y) {
        data[y][x] = val;
    }

    @Override
    public int getRGB(int x, int y) {
        return data[y][x];
    }

    @Override
    public int getWidth() {
        return data[0].length;
    }

    @Override
    public int getHeight() {
        return data.length;
    }

    @Override
    public Image scale(Point targetSz) {
        return null;
    }
}

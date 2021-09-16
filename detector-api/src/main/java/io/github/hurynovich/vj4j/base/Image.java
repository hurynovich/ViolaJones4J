package io.github.hurynovich.vj4j.base;

public interface Image {
    public int getRGB(Int2D pos);
    public int getRGB(int x, int y);
    public int getWidth();
    public int getHeight();
}

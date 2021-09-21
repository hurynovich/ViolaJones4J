package io.github.hurynovich.vj4j.detector.api;

public interface Image {
    int getRGB(int x, int y);
    int getWidth();
    int getHeight();

    //TODO remove this temp solution
    @Deprecated(forRemoval = true)
    Image scale(Point targetSz);
}

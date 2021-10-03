package io.github.hurynovich.vj4j.core.api;

public interface Image {
    int getRGB(int x, int y);
//    default int getGrayValue(int x, int y){ return 0; }
//    default int getRgbValue(int x, int y){ return 0; }
    int getWidth();
    int getHeight();

    //TODO remove this temp solution
    @Deprecated(forRemoval = true)
    Image scale(Point targetSz);

}

package io.github.hurynovich.vj4j.trainer.opencv.impl;

public interface FeatureIterator {
    Feature next();
    boolean hasNext();
    int getIndex();
    void setIndex(int i);
    int getSize();
}

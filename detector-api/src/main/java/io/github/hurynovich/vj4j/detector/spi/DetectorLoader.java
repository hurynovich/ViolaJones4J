package io.github.hurynovich.vj4j.detector.spi;

import io.github.hurynovich.vj4j.detector.api.Detector;

import java.nio.file.Path;

//TODO add documentation
public interface DetectorLoader {
    boolean canLoad(Path path);
    Detector load(Path path);
}

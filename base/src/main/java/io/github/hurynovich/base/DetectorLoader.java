package io.github.hurynovich.base;

import java.nio.file.Path;

//TODO add documentation
public interface DetectorLoader {
    boolean canLoad(Path path);
    Detector load(Path path);
}

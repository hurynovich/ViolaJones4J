package io.github.hurynovich.vj4j.base;

import java.nio.file.Path;

//TODO add documentation
public interface DetectorLoader {
    boolean canLoad(Path path);
    Detector load(Path path);
}

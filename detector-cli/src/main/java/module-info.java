import io.github.hurynovich.vj4j.base.DetectorLoader;

module vj4j.detector.cli {
    requires java.desktop;
    requires info.picocli;
    requires vj4j.base;
    requires vj4j.detector.opencv.impl;

    opens io.github.hurynovich.vj4j.detector.cli to info.picocli;

    uses DetectorLoader;
}
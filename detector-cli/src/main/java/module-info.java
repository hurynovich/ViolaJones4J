module vj4j.detector.cli {
    requires java.desktop;
    requires info.picocli;
    requires vj4j.base;
    requires vj4j.detector.opencv.impl;

    opens io.github.hurynovich.violajones to info.picocli;

    uses io.github.hurynovich.base.DetectorLoader;
}
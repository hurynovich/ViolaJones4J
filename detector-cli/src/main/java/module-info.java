import io.github.hurynovich.vj4j.detector.api.DetectorLoader;

module vj4j.detector.cli {
    requires java.desktop;
    requires info.picocli;
    requires org.slf4j;
    requires org.slf4j.simple;
    requires vj4j.detector.api;
    requires vj4j.detector.opencv.impl;
    requires static lombok;

    opens io.github.hurynovich.vj4j.detector.cli to info.picocli;

    uses DetectorLoader;
}
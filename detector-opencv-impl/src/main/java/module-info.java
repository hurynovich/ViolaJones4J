import io.github.hurynovich.vj4j.detector.spi.DetectorLoader;
import io.github.hurynovich.vj4j.detector.opencv.impl.io.OpenCvCascadeLoader;

module vj4j.detector.opencv.impl {
    requires java.xml;

    requires vj4j.detector.api;
    requires vj4j.commons;

    requires static lombok;

    provides DetectorLoader with OpenCvCascadeLoader;
}
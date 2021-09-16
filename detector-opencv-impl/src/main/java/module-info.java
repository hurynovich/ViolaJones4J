import io.github.hurynovich.vj4j.detector.spi.DetectorLoader;
import io.github.hurynovich.vj4j.detector.opencv.impl.io.OpenCvCascadeLoader;

module vj4j.detector.opencv.impl {
    requires java.desktop;
    requires vj4j.detector.api;
    requires static lombok;

    provides DetectorLoader with OpenCvCascadeLoader;
}
import io.github.hurynovich.vj4j.base.DetectorLoader;

module vj4j.detector.opencv.impl {
    requires java.desktop;
    requires vj4j.base;
    requires static lombok;

    provides DetectorLoader with io.github.hurynovich.vj4j.detector.opencv.OpenCvCascadeLoader;
}
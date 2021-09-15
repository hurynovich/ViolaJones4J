module vj4j.detector.opencv.impl {
    requires java.desktop;
    requires vj4j.base;
    requires static lombok;

    provides io.github.hurynovich.base.DetectorLoader with io.github.hurynovich.vj4j.detector.opencv.OpenCvCascadeLoader;
}
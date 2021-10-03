import io.github.hurynovich.vj4i.image.api.ImageEditor;
import io.github.hurynovich.vj4j.detector.opencv.impl.io.OpenCvCascadeLoader;
import io.github.hurynovich.vj4j.detector.spi.DetectorLoader;

module vj4j.detector.opencv.impl {
    requires java.xml;

    requires vj4j.detector.api;
    requires vj4j.image.processor.api;
    requires vj4j.commons;

    requires static lombok;

    uses ImageEditor;

    provides DetectorLoader with OpenCvCascadeLoader;
}
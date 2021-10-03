import io.github.hurynovich.vj4i.image.api.ImageEditor;
import io.github.hurynovich.vj4j.detector.spi.DetectorLoader;

module vj4j.detector.cli {
    requires java.logging;
    requires info.picocli;
    requires vj4j.detector.api;
    requires vj4j.image.processor.api;
    requires static lombok;

    opens io.github.hurynovich.vj4j.detector.cli to info.picocli;

    uses DetectorLoader;
    uses ImageEditor;
}
import io.github.hurynovich.vj4j.detector.spi.DetectorLoader;

module vj4j.detector.cli {
    requires java.desktop;
    requires info.picocli;
    requires vj4j.detector.api;
    requires static lombok;

    opens io.github.hurynovich.vj4j.detector.cli to info.picocli;

    uses DetectorLoader;
}
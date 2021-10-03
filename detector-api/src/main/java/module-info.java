module vj4j.detector.api {
    requires transitive vj4j.core.api;
    requires static lombok;

    exports io.github.hurynovich.vj4j.detector.api;
    exports io.github.hurynovich.vj4j.detector.spi;
}
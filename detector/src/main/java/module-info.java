module viola.jones.detector.main {
    requires java.desktop;
    requires info.picocli;
    requires viola.jones.base;

    opens io.github.hurynovich.violajones to info.picocli;
}
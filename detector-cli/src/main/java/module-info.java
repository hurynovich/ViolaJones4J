module vj4j.detector.cli {
    requires java.desktop;
    requires info.picocli;
    requires vj4j.base;

    opens io.github.hurynovich.violajones to info.picocli;
}
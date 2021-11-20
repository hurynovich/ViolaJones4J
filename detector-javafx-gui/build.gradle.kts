plugins {
    application
    id("org.openjfx.javafxplugin") version "0.0.10"
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

javafx {
    version = "11"
    modules("javafx.controls")
}

application {
    mainClass.set("io.github.hurynovich.vj4j.detector.gui.DetectorApp")
}
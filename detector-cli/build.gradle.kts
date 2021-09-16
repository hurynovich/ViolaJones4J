plugins {
    application
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

application {
    mainClass.set("io.github.hurynovich.vj4j.detector.cli.DetectorApp")
    mainModule.set("vj4j.detector.cli")
    applicationName = "vj4j-detector"
}

dependencies {
    implementation(project(":detector-api"))
    implementation(project(":detector-opencv-impl"))
    implementation(DepsCatalog.picocli)
    implementation(DepsCatalog.loggerApi)
    implementation(DepsCatalog.loggerImpl)

    testImplementation(DepsCatalog.junitApi)
    testRuntimeOnly(DepsCatalog.junitEngine)
}

val test by tasks.getting(Test::class) {
    // Use junit platform for unit tests
    useJUnitPlatform()
}

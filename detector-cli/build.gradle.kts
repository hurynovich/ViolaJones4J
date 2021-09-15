plugins {
    application
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

application {
    mainClass.set("io.github.hurynovich.vj4j.detector.cli.DetectorApp")
}

dependencies {
    implementation(project(":base"))
    implementation(project(":detector-opencv-impl"))

    implementation(DepsCatalog.picocli)

    testImplementation(DepsCatalog.junitApi)
    testRuntimeOnly(DepsCatalog.junitEngine)
}

val test by tasks.getting(Test::class) {
    // Use junit platform for unit tests
    useJUnitPlatform()
}

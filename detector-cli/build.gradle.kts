plugins {
    application
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

application {
    mainClass.set("io.github.hurynovich.violajones.DetectorCliApp")
}

dependencies {
    implementation(project(":base"))

    implementation(DepsCatalog.picocli)

    testImplementation(DepsCatalog.junitApi)
    testRuntimeOnly(DepsCatalog.junitEngine)
}

val test by tasks.getting(Test::class) {
    // Use junit platform for unit tests
    useJUnitPlatform()
}

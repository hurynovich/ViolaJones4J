plugins {
    `java-library`
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(project(":detector-api"))
    implementation(project(":commons"))

    compileOnly(DepsCatalog.lombok)
    annotationProcessor(DepsCatalog.lombok)

    testCompileOnly(DepsCatalog.lombok)
    testAnnotationProcessor(DepsCatalog.lombok)

    testImplementation(DepsCatalog.junitApi)
    testImplementation(DepsCatalog.junitParams)
    testRuntimeOnly(DepsCatalog.junitEngine)
}

val test by tasks.getting(Test::class) {
    // Use junit platform for unit tests
    useJUnitPlatform()
}

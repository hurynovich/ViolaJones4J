plugins {
    java
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(project(":detector-api"))

    compileOnly(DepsCatalog.lombok)
    annotationProcessor(DepsCatalog.lombok)

    testImplementation(DepsCatalog.junitApi)
    testImplementation(DepsCatalog.junitParams)
    testRuntimeOnly(DepsCatalog.junitEngine)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
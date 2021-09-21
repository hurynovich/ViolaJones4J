plugins {
    java
}

dependencies {
    implementation(project(":detector-api"))

    testImplementation(DepsCatalog.junitApi)
    testImplementation(DepsCatalog.junitParams)
    testRuntimeOnly(DepsCatalog.junitEngine)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
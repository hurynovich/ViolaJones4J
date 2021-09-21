plugins {
    java
}

dependencies {
    testImplementation(DepsCatalog.junitApi)
    testImplementation(DepsCatalog.junitParams)
    testRuntimeOnly(DepsCatalog.junitEngine)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
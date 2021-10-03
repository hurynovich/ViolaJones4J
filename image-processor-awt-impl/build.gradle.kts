plugins {
    java
}

version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":image-processor-api"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
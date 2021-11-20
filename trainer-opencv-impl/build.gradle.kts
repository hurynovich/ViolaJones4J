plugins {
    `java-library`
}

version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    api(project(":trainer-api"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
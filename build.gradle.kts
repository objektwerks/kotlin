plugins {
    kotlin("jvm") version "1.4.32"
    application
}

group = "tripletail"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(kotlin("bom")))
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.arrow-kt:arrow-core:0.13.0")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}

application {
    mainClass.set("tripletail.App")
}
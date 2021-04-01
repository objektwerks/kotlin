plugins {
    application
    kotlin("jvm") version "1.5.0-M2"
}

group = "tripletail"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("tripletail.App")
}
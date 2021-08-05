plugins {
    kotlin("jvm") version "1.5.30-M1"
    kotlin("plugin.serialization") version "1.5.30-M1"
    application
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> { kotlinOptions.jvmTarget = "1.8" }

group = "tripletail"
version = "0.1-SNAPSHOT"

application {
    mainClass.set("tripletail.console.App")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(kotlin("bom")))
    implementation(kotlin("stdlib-jdk8"))

    implementation("io.arrow-kt:arrow-core:0.13.1")

    implementation("com.sksamuel.hoplite:hoplite-core:1.4.0")
    implementation("com.sksamuel.hoplite:hoplite-yaml:1.4.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")

    implementation("io.ktor:ktor-client-core:1.5.3")
    implementation("io.ktor:ktor-client-cio:1.5.3")

    implementation("io.ktor:ktor-server-core:1.5.3")
    implementation("io.ktor:ktor-server-netty:1.5.3")

    implementation("org.jetbrains.exposed:exposed-core:0.30.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.30.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.30.1")
    implementation("com.h2database:h2:1.4.200")

    implementation("ch.qos.logback:logback-classic:1.2.3")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}
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
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.30-M1")

    implementation("io.arrow-kt:arrow-core:0.13.2")

    implementation("com.sksamuel.hoplite:hoplite-core:1.4.0")
    implementation("com.sksamuel.hoplite:hoplite-yaml:1.4.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")

    implementation("io.ktor:ktor-client-core:1.6.2")
    implementation("io.ktor:ktor-client-cio:1.6.1")

    implementation("io.ktor:ktor-server-core:1.6.2")
    implementation("io.ktor:ktor-server-netty:1.6.2")

    implementation("org.jetbrains.exposed:exposed-core:0.32.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.32.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.32.1")
    implementation("com.h2database:h2:1.4.200")

    implementation("ch.qos.logback:logback-classic:1.2.5")

    testImplementation("org.jetbrains.kotlin:kotlin-test:1.5.30-M1")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.5.30-M1")
}
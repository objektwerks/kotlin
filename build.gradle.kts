plugins {
    kotlin("jvm") version "1.9.20-Beta"
    kotlin("plugin.serialization") version "1.9.20-Beta"
    application
    id("com.adarshr.test-logger") version "3.2.0"
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "21"
        kotlinOptions.freeCompilerArgs = listOf("-Xcontext-receivers")
    }
}

group = "tripletail"
version = "1.1-SNAPSHOT"

application {
    mainClass.set("console.App")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(kotlin("bom")))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.20-Beta")

    implementation("org.jetbrains.kotlin:kotlin-test:1.9.20-Beta")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.9.20-Beta")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    implementation("io.ktor:ktor-client-core:2.3.0")
    implementation("io.ktor:ktor-client-cio:2.3.0")
    implementation("io.ktor:ktor-server-core:2.3.0")
    implementation("io.ktor:ktor-server-netty:2.3.0")

    implementation("org.jetbrains.exposed:exposed-core:0.40.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.40.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.40.1")

    implementation("io.arrow-kt:arrow-core:1.2.0-RC")

    implementation("com.sksamuel.hoplite:hoplite-core:2.7.2")
    implementation("com.sksamuel.hoplite:hoplite-yaml:2.7.2")

    implementation("com.h2database:h2:2.2.222")

    implementation("ch.qos.logback:logback-classic:1.4.8")
}
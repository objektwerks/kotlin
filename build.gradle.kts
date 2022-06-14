plugins {
    kotlin("jvm") version "1.7.0"
    kotlin("plugin.serialization") version "1.7.0"
    application
    id("com.adarshr.test-logger") version "3.2.0"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
    kotlinOptions.freeCompilerArgs = listOf("-Xcontext-receivers")
}

tasks.withType<Test> {
    useJUnit()
}

group = "tripletail"
version = "0.1-SNAPSHOT"

application {
    mainClass.set("console.App")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(kotlin("bom")))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.7.0")

    implementation("org.jetbrains.kotlin:kotlin-test:1.7.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.7.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")

    implementation("io.ktor:ktor-client-core:2.0.1")
    implementation("io.ktor:ktor-client-cio:2.0.1")
    implementation("io.ktor:ktor-server-core:2.0.1")
    implementation("io.ktor:ktor-server-netty:2.0.1")

    implementation("org.jetbrains.exposed:exposed-core:0.38.2")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.38.2")
    implementation("org.jetbrains.exposed:exposed-dao:0.38.2")

    implementation("io.arrow-kt:arrow-core:1.1.2")

    implementation("com.sksamuel.hoplite:hoplite-core:2.1.4")
    implementation("com.sksamuel.hoplite:hoplite-yaml:2.1.4")

    implementation("com.h2database:h2:2.1.212")

    implementation("ch.qos.logback:logback-classic:1.2.11")
}
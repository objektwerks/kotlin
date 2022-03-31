plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
    application
    id("com.adarshr.test-logger") version "3.2.0"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.withType<Test> {
    useJUnit()
}

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
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.10")

    implementation("io.arrow-kt:arrow-core:1.0.1")

    implementation("com.sksamuel.hoplite:hoplite-core:2.0.4")
    implementation("com.sksamuel.hoplite:hoplite-yaml:2.0.4")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    implementation("io.ktor:ktor-client-core:1.6.8")
    implementation("io.ktor:ktor-client-cio:1.6.8")

    implementation("io.ktor:ktor-server-core:1.6.8")
    implementation("io.ktor:ktor-server-netty:1.6.8")

    implementation("org.jetbrains.exposed:exposed-core:0.37.3")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.37.3")
    implementation("org.jetbrains.exposed:exposed-dao:0.37.3")

    implementation("com.h2database:h2:2.1.210")

    implementation("ch.qos.logback:logback-classic:1.2.11")

    implementation("org.jetbrains.kotlin:kotlin-test:1.6.10")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.6.10")
}

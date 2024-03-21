import com.google.devtools.ksp.gradle.KspTaskMetadata
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "kotlin"
version = "1.3-SNAPSHOT"

plugins {
    kotlin("jvm") version "2.0.0-Beta5"
    kotlin("plugin.serialization") version "2.0.0-Beta5"
    application
    id("com.adarshr.test-logger") version "4.0.0"
    id("com.google.devtools.ksp") version "2.0.0-Beta5-1.0.19"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

kotlin {
    sourceSets.all {
        languageSettings {
            languageVersion = "2.0"
        }
        tasks.withType<KspTaskMetadata> {
            kotlin.srcDir(destinationDirectory)
        }
    }
}

tasks {
    withType<KotlinCompile> {
        compilerOptions.freeCompilerArgs = listOf("-Xcontext-receivers")
    }
}

application {
    mainClass.set("console.App")
}

repositories {
    mavenCentral()
}

dependencies {
    val kotlinVersion = "2.0.0-Beta5"
    val ktorVersion = "2.3.9"
    val exposedVersion = "0.48.0"
    val arrowVersion = "1.2.3"
    val hopliteVersion = "2.7.5"

    implementation(platform(kotlin("bom")))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")

    implementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.7")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")

    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

    implementation("io.arrow-kt:arrow-core:$arrowVersion")
    implementation("io.arrow-kt:arrow-fx-coroutines:$arrowVersion")
    implementation("io.arrow-kt:arrow-optics:$arrowVersion")
    ksp("io.arrow-kt:arrow-optics-ksp-plugin:$arrowVersion")

    implementation("com.sksamuel.hoplite:hoplite-core:$hopliteVersion")
    implementation("com.sksamuel.hoplite:hoplite-yaml:$hopliteVersion")

    implementation("com.h2database:h2:2.2.224")

    implementation("ch.qos.logback:logback-classic:1.5.3")
}

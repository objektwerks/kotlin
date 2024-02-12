import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.0.0-Beta3"
    kotlin("plugin.serialization") version "2.0.0-Beta3"
    application
    id("com.adarshr.test-logger") version "4.0.0"
    id("com.google.devtools.ksp") version "2.0.0-Beta3-1.0.17"
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
    }
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
    sourceSets.test {
        kotlin.srcDir("build/generated/ksp/test/kotlin")
    }
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.freeCompilerArgs = listOf("-Xcontext-receivers")
    }
}

group = "tripletail"
version = "1.3-SNAPSHOT"

application {
    mainClass.set("console.App")
}

repositories {
    mavenCentral()
}

dependencies {
    val kotlinVersion = "2.0.0-Beta3"

    implementation(platform(kotlin("bom")))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")

    implementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.7")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    implementation("io.arrow-kt:arrow-optics:1.2.1")
    ksp("io.arrow-kt:arrow-optics-ksp-plugin:1.2.1")

    implementation("io.ktor:ktor-client-core:2.3.8")
    implementation("io.ktor:ktor-client-cio:2.3.8")
    implementation("io.ktor:ktor-server-core:2.3.8")
    implementation("io.ktor:ktor-server-netty:2.3.8")

    implementation("org.jetbrains.exposed:exposed-core:0.47.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.47.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.47.0")

    implementation("io.arrow-kt:arrow-core:1.2.1")
    implementation("io.arrow-kt:arrow-fx-coroutines:1.2.1")

    implementation("com.sksamuel.hoplite:hoplite-core:2.7.5")
    implementation("com.sksamuel.hoplite:hoplite-yaml:2.7.5")

    implementation("com.h2database:h2:2.2.224")

    implementation("ch.qos.logback:logback-classic:1.4.14")
}
plugins {
    kotlin("jvm") version "1.8.0"
    kotlin("plugin.serialization") version "1.8.0"
    application
    id("com.adarshr.test-logger") version "3.2.0"
}

tasks {
    withType<JavaCompile>().all {
        options.compilerArgs = listOf("--enable-preview", "add-modules=jdk.incubator.concurrent")
    }

    withType<JavaExec>().all {
        jvmArgs = listOf("--enable-preview", "add-modules=jdk.incubator.concurrent")
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "19"
        kotlinOptions.freeCompilerArgs = listOf("-Xcontext-receivers", "-X--add-modules", "-Xadd-modules=jdk.incubator.concurrent")
    }

    withType<Test>().all {
        useJUnit()
        jvmArgs("--enable-preview", "--add-modules", "jdk.incubator.concurrent")
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
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.0")

    implementation("org.jetbrains.kotlin:kotlin-test:1.8.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.8.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

    implementation("io.ktor:ktor-client-core:2.2.1")
    implementation("io.ktor:ktor-client-cio:2.2.1")
    implementation("io.ktor:ktor-server-core:2.2.1")
    implementation("io.ktor:ktor-server-netty:2.2.1")

    implementation("org.jetbrains.exposed:exposed-core:0.40.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.40.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.40.1")

    implementation("io.arrow-kt:arrow-core:1.1.2")

    implementation("com.sksamuel.hoplite:hoplite-core:2.7.0")
    implementation("com.sksamuel.hoplite:hoplite-yaml:2.7.0")

    implementation("com.h2database:h2:2.1.214")

    implementation("ch.qos.logback:logback-classic:1.4.5")
}
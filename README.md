Kotlin
------
>Feature tests:
1. [Arrow](https://arrow-kt.io)
2. [Hoplite](https://github.com/sksamuel/hoplite)
3. [Ktor](https://ktor.io)
4. [Exposed](https://github.com/JetBrains/Exposed)
5. [Json](https://kotlinx-serialization-json)
6. [JavaFX](https://openjfx.io/)
7. [Java Swing](https://docs.oracle.com/javase/tutorial/uiswing/index.html)

Apps
----
>The following apps are available:
1. console/App
2. http/ChuckNorrisClient
3. http/NowServer
4. javafx/ChuckNorrisFxApp
5. swing/ChuckNorrisApp
>The default app is: console.App See the Run section below.

>**Note:** Unlike Scala Sbt, Gradle does not provide a list of available apps to run.

JDK
---
>This project, built using an Apple Airbook, M1, 16GB, uses:
```
openjdk version "19.0.2" 2023-01-17
OpenJDK Runtime Environment Zulu19.32+15-CA (build 19.0.2+7)
OpenJDK 64-Bit Server VM Zulu19.32+15-CA (build 19.0.2+7, mixed mode, sharing)
```
>which includes JavaFX.

Gradle
------
>To upgrade Gradle, edit the **version number** in this file:
* gradle/wrapper/gradle-wrapper.properties
>**Note:** Also validate Preference > ... > Build Tools > Gradle for the correct **current** Gradle version.

Build
-----
>Use Intellij gradle to clean and build. Or use gradle:
* gradle clean build

Test
----
>Use Intellij gradle to clean, build, run and view tests. Or use gradle:
* gradle clean build test
     * view results courtesy of com.adarshr.test-logger plugin
     * optionally open ***build/reports/tests/test/index.html*** in a browser

Run
---
>To run the default app ( **console.App** ):
1. gradle run
>To selectively run any app:
1. select
2. right click
3. select run '*Kt'

Logs
----
1. build/kotlin.log
2. build/kotlin.test.log

Resources
---------
1. [Kotlin Docs](https://kotlinlang.org/docs/home.html)
2. [Kotlin Quick Guide](https://github.com/Mr-Skully/kotlin-quick-guide)
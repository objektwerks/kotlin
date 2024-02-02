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

Arrow
-----
>Arrow has been a moving target since its inception. Consequently, not ***all*** features work as documented!

Apps
----
>The following apps are available:
1. console.App
2. http.ChuckNorrisClient
3. http.NowServer
4. javafx.ChuckNorrisFxApp
5. swing.ChuckNorrisApp
>The default app is: console.App See the Run section below.

Gradle
------
>To upgrade Gradle, edit the **version number** in this file:
* gradle/wrapper/gradle-wrapper.properties

>To correlate Gradle versions, select:
* Settings > Build, Execution, Deployment > Build Tools > Gradle

Build
-----
>Use Intellij gradle to clean and build project.

>Alternatively use commandline gradle:
1. gradle clean build

>To run the K2 compiler, use commandline gradlew:
1. ./gradlew clean build -Pkotlin.experimental.tryK2=true

Test
----
>Use Intellij gradle to clean, build, run and view tests.

>Alternatively use commandline gradle:
1. gradle clean build test
     * view results courtesy of com.adarshr.test-logger plugin
     * optionally open ***build/reports/tests/test/index.html*** in a browser

Run
---
>To run the default app ( **console.App** ):
1. gradle run

>To selectively run any app within Intellij:
1. select app from **src** dir
2. right click app
3. select run ( named 'app' )

Logs
----
>See:
1. build/kotlin.log
2. build/kotlin.test.log

Resources
---------
1. [Kotlin Docs](https://kotlinlang.org/docs/home.html)
2. [Kotlin Quick Guide](https://github.com/Mr-Skully/kotlin-quick-guide)
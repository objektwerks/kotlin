Kotlin
------
>Kotlin feature tests, to include:
1. Arrow - arrow-kt.io
2. Hoplite - github.com/sksamuel/hoplite
3. Ktor - ktor.io
4. Exposed - github.com/JetBrains/Exposed **
5. Json - kotlinx-serialization-json
6. Test - kotlin-test / kotlin-test-junit

Gradle
------
>To upgrade Gradle, edit the **version number** in this file:
1. gradle/wrapper/gradle-wrapper.properties

Build
-----
1. Use Intellij gradle to clean and build.
2. gradle clean build

Test
----
1. Use Intellij gradle to clean, build, run and view tests.
2. gradle clean build test
     * view results courtesy of com.adarshr.test-logger plugin
     * optionally open ***build/reports/tests/test/index.html*** in a browser

Design
------
>Kotlin focuses on **3** major areas:
1. nullability
2. exceptions
3. Java compatibility
>In the FP space, 1 and 2 are ***foreign*** concepts. Nonetheless, akin to Scala, Kotlin can be used as an OO-FP hybrid.

Collections
-----------
>Kotlin collections are ***not*** like Scala collections. Instead, they mimic Java collections.

Coroutines
----------
>Kotlin coroutines are **complex** compared to using a Scala Future.

Arrow
-----
>Arrow is the **goto** FP library in the Kotlin space. In a limited way, it mirrors the
>Typelevel Cats library. Arrow is in constant flux, though, and should be used judiciously.
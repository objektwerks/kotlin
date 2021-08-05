Kotlin
------
>Kotlin feature tests, to also include:
1. Arrow : arrow-kt.io
2. Hoplite : github.com/sksamuel/hoplite
3. Ktor : ktor.io
4. Exposed : github.com/JetBrains/Exposed
5. Json : kotlinx-serialization-json
6. Test : kotlin-test / kotlin-test-junit

Build
-----
>You might have to use IntelliJ Gradle Tasks.
1. gradle clean build

Test
----
>You might have to use IntelliJ Gradle Tasks.
1. gradle clean build test

Design
------
>Kotlin focuses on **2** concepts:
1. nullability
2. throwing exceptions
>In the FP space, both concepts are foreign.

Collections
-----------
>Kotlin collections are **not** Scala collections. They appear to mimic Java collections.

Coroutines
----------
>Kotlin coroutines are **complex** compared to a Scala Future.

Arrow
-----
>Arrow is the **goto** FP library in the Kotlin space. In a limited way, it mirrors the
>Typelevel Cats library. Arrow is in constant flux, though, and should be used judiciously.
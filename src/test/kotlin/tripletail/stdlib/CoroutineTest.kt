package tripletail.stdlib

import kotlinx.coroutines.*

import org.junit.Test

class CoroutineTest {
    @Test fun runBlocking(): Unit = runBlocking {
        GlobalScope.launch {
            delay(1000L)
            println("World!")
        }
        println("Hello,")
        delay(2000L)
    }
}
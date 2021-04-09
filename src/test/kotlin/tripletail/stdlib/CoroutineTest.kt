package tripletail.stdlib

import kotlinx.coroutines.*

import org.junit.Test

class CoroutineTest {
    @Test fun coroutine() {
        fun sum(x: Int, y: Int): Int = runBlocking { x + y }
        assert( sum(3, 3) == 6 )
    }
}
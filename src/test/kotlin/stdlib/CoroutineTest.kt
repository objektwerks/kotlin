package stdlib

import kotlinx.coroutines.*

import org.junit.Test

class CoroutineTest {
    fun sum(x: Int, y: Int): Int = runBlocking { x + y }
    suspend fun multiply(x: Int, y: Int): Int = withContext(Dispatchers.Default) { x * y }
    suspend fun deferred(): List<Deferred<Int>> = (1..10).map { n -> coroutineScope { async { n + 1 } } }
    suspend fun sumOf(deferred: List<Deferred<Int>>): Int = deferred.sumOf { it.await() }

    @Test fun coroutine() {
        assert( sum(3, 3) == 6 )
        assert( runBlocking { multiply(3, 3) } == 9 )
        assert( runBlocking { sumOf( deferred() ) } == 65 )
    }
}
package tripletail.stdlib

import kotlinx.coroutines.*

import org.junit.Test

class CoroutineTest {
    @Test fun coroutine() {
        fun sum(x: Int, y: Int): Int = runBlocking { x + y }
        assert( sum(3, 3) == 6 )

        suspend fun multiply(x: Int, y: Int): Int = withContext(Dispatchers.Default) { x * y }
        assert( runBlocking { multiply(3, 3) } == 9 )

        val deferred = (1..10).map { n ->
            GlobalScope.async { n }
        }
        suspend fun sumOf(deferred: List<Deferred<Int>>): Int = deferred.sumOf { it.await() }
        assert( runBlocking { sumOf(deferred) } == 10 )
    }
}
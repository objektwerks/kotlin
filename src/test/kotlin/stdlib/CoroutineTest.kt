package stdlib

import kotlinx.coroutines.*

import org.junit.Test

class CoroutineTest {
    private fun sum(x: Int, y: Int): Int = runBlocking { x + y }

    private suspend fun multiply(x: Int, y: Int): Int = withContext( Dispatchers.Default ) { x * y }

    private suspend fun sumOf(deferred: List<Deferred<Int>>): Int = deferred.sumOf { it.await() }

    private suspend fun deferred(): List<Deferred<Int>> =
        (1..10).map { n ->
            coroutineScope {
                async { n + 1 }
            }
        }

    @Test fun coroutine() {
        assert( sum(3, 3) == 6 )
        assert( runBlocking { multiply(3, 3) } == 9 )
        assert( runBlocking { sumOf( deferred() ) } == 65 )
    }
}
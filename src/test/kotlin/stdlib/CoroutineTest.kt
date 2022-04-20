package stdlib

import kotlinx.coroutines.*

import org.junit.Test

@Suppress("SameParameterValue")
class CoroutineTest {
    private fun runBlockingSum(x: Int, y: Int): Int = runBlocking { x + y }

    private suspend fun withContextSum(x: Int, y: Int): Int = withContext( Dispatchers.Default ) { x + y }

    private suspend fun deferredSumOf(deferred: List<Deferred<Int>>): Int = deferred.sumOf { it.await() }

    private suspend fun deferredAsyncSource(): List<Deferred<Int>> =
        (1..10).map { n ->
            coroutineScope {
                async { n + 1 }
            }
        }

    @Test fun coroutine() {
        assert( runBlockingSum(1, 2) == 3 )
        assert( runBlocking { withContextSum(1, 2) } == 3 )
        assert( runBlocking { deferredSumOf( deferredAsyncSource() ) } == 65 )
    }
}
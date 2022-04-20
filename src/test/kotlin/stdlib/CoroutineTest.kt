package stdlib

import kotlinx.coroutines.*

import org.junit.Test

class CoroutineTest {
    @Test fun runBlockingSum() {
        fun runBlockingSum(x: Int, y: Int): Int = runBlocking { x + y }
        assert( runBlockingSum(1, 2) == 3 )
    }

    @Test fun withContextSum() {
        suspend fun withContextSum(x: Int, y: Int): Int = withContext( Dispatchers.Default ) { x + y }
        assert( runBlocking { withContextSum(1, 2) } == 3 )
    }

    @Test fun deferredSumOf() {
        suspend fun deferredSumOf(deferred: List<Deferred<Int>>): Int = deferred.sumOf { it.await() }
        suspend fun deferredAsyncSource(): List<Deferred<Int>> =
            (1..10).map { n ->
                coroutineScope {
                    async { n + 1 }
                }
            }
        assert( runBlocking { deferredSumOf( deferredAsyncSource() ) } == 65 )
    }
}
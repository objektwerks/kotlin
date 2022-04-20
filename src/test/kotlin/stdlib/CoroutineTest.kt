package stdlib

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*

import org.junit.Test

class CoroutineTest {
    @Test fun runBlockingLaunch() {
        runBlocking {
            var sum = 0
            launch {
                delay(1000)
                sum += 1
                assert(sum == 3)
            }
            sum += 2
        }
    }

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

    @Test fun flow() {
        fun source(): Flow<Int> = flow {
            for (i in 1..3) {
                delay(100)
                emit(i)
            }
        }
        runBlocking<Unit> {
            var sink = 0
            launch {
                for (k in 1..3) {
                    delay(100)
                }
            }
            source().collect { value -> sink += value }
            assert( sink == 6 )
        }
    }

    @Test fun channel() {
        runBlocking {
            var sink = 0
            val channel = Channel<Int>()
            launch {
                for (i in 1..3) channel.send(i)
            }
            repeat(3) { sink += channel.receive() }
            assert( sink == 6 )
        }
    }
}
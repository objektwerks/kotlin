package stdlib

import kotlinx.coroutines.*
import kotlin.math.absoluteValue
import kotlin.random.Random
import kotlin.system.measureTimeMillis

import org.junit.Test

class CoroutineTest {
    @Test fun runBlockingLaunchSum() {
        suspend fun launchSum() = coroutineScope {
            var sum = 0
            launch {
                delay(1000)
                sum += 1
                assert( sum == 3 )
            }
            sum += 2
        }

        runBlocking {
            launchSum()
        }
    }

    @Test fun runBlockingLaunchJob() {
        runBlocking {
            var sum = 0
            val job = launch {
                delay(1000)
                sum += 1
            }
            sum += 2
            job.join()
            assert( sum == 3 )
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

    @Test fun asyncAwait() {
        fun randomInt(): Int = Random(10).nextInt().absoluteValue

        val elapsedTime =
            runBlocking {
                measureTimeMillis {
                    val x = async { randomInt() }
                    val y = async { randomInt() }
                    assert( x.await() + y.await() > 0 )
                }
            }
        assert( elapsedTime > 0 )
    }
}
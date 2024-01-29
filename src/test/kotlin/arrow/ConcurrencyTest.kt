package arrow

import arrow.fx.coroutines.parZip

import kotlin.test.Test

import kotlinx.coroutines.runBlocking

class ConcurrencyTest {
    private suspend fun parSum(x: Int, y: Int): Int =
        parZip(
            { x },
            { y }
        ) { a, b -> a + b }

    @Test fun concurrency() {
        assert(
            runBlocking {
                parSum(1, 2) == 3
            }
        )
    }
}
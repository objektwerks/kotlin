package arrow

import arrow.fx.coroutines.parZip

import kotlin.test.Test

import kotlinx.coroutines.runBlocking

class ConcurrencyTest {
    private suspend fun parDoubleAndSum(x: Int, y: Int): Int =
        parZip(
            { x * x },
            { y * y }
        ) { xx, yy -> xx + yy }

    @Test fun concurrency() {
        assert(
            runBlocking {
                parDoubleAndSum(1, 2) == 5
            }
        )
    }
}
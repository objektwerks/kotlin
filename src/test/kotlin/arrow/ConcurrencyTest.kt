package arrow

import arrow.fx.coroutines.parMap
import arrow.fx.coroutines.parZip

import kotlin.test.Test

import kotlinx.coroutines.runBlocking

class ConcurrencyTest {
    private suspend fun parMapDoubleAndSum(xs: List<Int>): List<Int> = xs.parMap { it * it  }

    private suspend fun parZipDoubleAndSum(x: Int, y: Int): Int =
        parZip(
            { x * x },
            { y * y }
        ) { xx, yy -> xx + yy }

    @Test fun concurrency() {
        assert(
            runBlocking {
                parMapDoubleAndSum(listOf(1, 2)) == listOf(1, 4)
            }
        )

        assert(
            runBlocking {
                parZipDoubleAndSum(1, 2) == 5
            }
        )
    }
}
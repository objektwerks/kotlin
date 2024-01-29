package arrow

import arrow.core.merge
import arrow.fx.coroutines.parMap
import arrow.fx.coroutines.parZip
import arrow.fx.coroutines.raceN

import concurrency.FileLineCountTask

import kotlin.test.Test

import kotlinx.coroutines.runBlocking

class ConcurrencyTest {
    private suspend fun parMapDoubleAndSum(xs: List<Int>): List<Int> = xs.parMap { it * it  }

    private suspend fun parZipDoubleAndSum(x: Int, y: Int): Int =
        parZip(
            { x * x },
            { y * y }
        ) { xx, yy -> xx + yy }

    private suspend fun race(): Int =
        raceN(
            { FileLineCountTask("./data/data.a.csv").call() },
            { FileLineCountTask("./data/data.b.csv").call() }
        ).merge()

    @Test fun concurrency() {
        runBlocking {
            assert( parMapDoubleAndSum(listOf(1, 2)) == listOf(1, 4) )
        }

        runBlocking {
            assert( parZipDoubleAndSum(1, 2) == 5 )
        }

        runBlocking {
            val count = race()
            assert( count == 270_397 || count == 270_562 )
        }
    }
}
package stdlib

import java.util.Random

import org.junit.Test

class ControlFlowTest {
    @Test fun ifElse() {
        val result = if (Random(10).nextInt() > 5) 6 else 4
        assert( result > 3 )
    }

    @Test fun forIn() {
        for (n in listOf(1, 2, 3)) { assert(n > 0) }
    }

    @Test fun forEach() {
        listOf(1, 2, 3).forEach { assert( it > 0 ) }
    }

    @Test fun range() {
        for (n in 1..3) { assert(n > 0) }
    }

    @Test fun loop() {
        var a = 3
        while (a > 0) {
            a--
        }
        assert( a == 0 )

        var b = 0
        do {
            b++
        } while (b != 3)
        assert( b == 3 )
    }

    @Test fun repeat() {
        var sum = 0
        repeat(3) {
            sum += 1
        }
        assert( sum == 3 )
    }
}
package stdlib

import java.util.*

import org.junit.Test

class ControlFlowTest {
    @Test fun foreach() {
        for (n in listOf(1, 2, 3)) { assert(n > 0) }
        for (n in 1..3) { assert(n > 0) }
    }

    @Test fun ifelse() {
        val result = if (Random(10).nextInt() > 5) 6 else 4
        assert( result > 3 )
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

    @Test fun match() {
        assert(
            when (val n = Random(10).nextInt()) {
                0 -> 5
                else -> n
            } != 0
        )

        assert(
            when (val n = Random(10).nextInt()) {
                0, 1, 2, 3 -> 4
                in 4..7 -> 8
                else -> n
            } != 0
        )

        assert(
            when (val any: Any = Random(10).nextInt().toString()) {
                is String -> true
                else -> any.hashCode() == 0
            }
        )
    }
}
package stdlib

import java.util.*

import org.junit.Test
import kotlin.math.absoluteValue
import kotlin.test.fail

class ControlFlowTest {
    @Test fun ifelse() {
        val result = if (Random(10).nextInt() > 5) 6 else 4
        assert( result > 3 )
    }

    @Test fun foreach() {
        for (n in listOf(1, 2, 3)) { assert(n > 0) }
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

    @Test fun match() {
        when (val n = Random(10).nextInt().absoluteValue) {
            0 -> fail("value equals zero!")
            else -> assert( n > 10 )
        }

        val result = when (val n = Random(10).nextInt().absoluteValue) {
            0, 1, 2, 3 -> fail("value equals 0, 1, 2 or 3!")
            in 4..7 -> fail("value equals 4, 5, 6, or 7!")
            else -> n
        }
        assert( result > 10 )
    }
}
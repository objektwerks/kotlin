package tripletail.stdlib

import java.util.*

import org.junit.Test

class ControlFlowTest {
    @Test fun controlFlow() {
        assert( (if (Random(10).nextInt() > 5) 6 else 4) > 3 )

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
                is Int -> false
                else -> any.hashCode() == 0
            }
        )

        for (n in listOf(1, 2, 3)) { assert(n > 0) }
        for (n in 1..3) { assert(n > 0) }

        var condition = 3
        while (condition > 0) {
            condition--
        }
        assert( condition == 0 )

        condition = 3
        do {
            condition--
        } while (condition > 0)
        assert( condition == 0 )
    }
}